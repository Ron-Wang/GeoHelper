package com.thutyh.geology_helper11;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JointActivity extends AppCompatActivity implements SensorEventListener {

    //Ron-Wang 2017-5-6 倾向修正需分类
    private SensorManager sensorManager;
    private Sensor acc_sensor;
    private Sensor mag_sensor;
    private Sensor pre_senser;
    //获得手机内的加速度传感器传回的数据
    float accValues[] = new float[3];
    //获得手机内的磁场传感器传回的数据
    float magValues[] = new float[3];
    //旋转矩阵，用来保存磁场和加速度传感器传回的数据
    float preValues[] = new float[1];
    float r[] = new float[9];
    //模拟方向传感器取得的数据，弧度制
    float values[] = new float[3];

    //定义界面上各个组件
    private TextView dia = null;
    private TextView dip = null;
    private TextView pre =null,
            loc1 = null,
            loc2 = null,
            loc3 = null;
    private Button mbtn1,
            mbtn2,
            mbtn3,
            mbtn4,
            mbtn5,
            mbtn6;
    private TextView mgeolist;

    //GPS
    private final String TAG = "GpsExample";

    //SQLite数据库相关变量
    private static final String DB_FILE = "geo.db",
            DB_TABLE = "geo";
    private SQLiteDatabase mGeoDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joint);

        //数据表初始化
        GeoDbOpenHelper geoDbOpenHelper = new GeoDbOpenHelper(getApplicationContext(), DB_FILE, null, 1);
        mGeoDb = geoDbOpenHelper.getWritableDatabase();
        //检查数据表是否存在。若不存在，则建立一个
        Cursor cursor = mGeoDb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + DB_TABLE + "'", null);
        if (cursor != null) {
            if (cursor.getCount() == 0)
                mGeoDb.execSQL("CREATE TABLE " + DB_TABLE + " (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "dia TEXT," + "dip TEXT," + "mark TEXT," + "time TEXT);");
            cursor.close();
        }

        //界面TextView的获取
        dia = (TextView) findViewById(R.id.TxtDia);
        dip = (TextView) findViewById(R.id.TxtDip);
        pre = (TextView) findViewById(R.id.TxtPre);
        loc1 = (TextView) findViewById(R.id.TxtLoc1);
        loc2 = (TextView) findViewById(R.id.TxtLoc2);
        loc3 = (TextView) findViewById(R.id.TxtLoc3);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mag_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pre_senser = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        //给传感器注册监听：
        sensorManager.registerListener(this, acc_sensor, SensorManager.SENSOR_DELAY_NORMAL);//FASTEST,GAME,NORMAL,UI,时间
        sensorManager.registerListener(this, mag_sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pre_senser, SensorManager.SENSOR_DELAY_NORMAL);

        //界面Button的获取
        mbtn1 = (Button) findViewById(R.id.BtnSave);
        mbtn2 = (Button) findViewById(R.id.BtnOrder);
        mbtn3 = (Button) findViewById(R.id.BtnClear);
        mbtn4 = (Button) findViewById(R.id.BtnMark);
        mbtn5 = (Button) findViewById(R.id.BtnDel);
        mbtn6 = (Button) findViewById(R.id.BtnExport);
        mgeolist = (TextView) findViewById(R.id.TxtList);

        //设置按钮触发事件
        mbtn1.setOnClickListener(btn1OnClick);
        mbtn2.setOnClickListener(btn2OnClick);
        mbtn3.setOnClickListener(btn3OnClick);
        mbtn4.setOnClickListener(btnMarkOnClick);
        mbtn5.setOnClickListener(btnDelOnClick);
        mbtn6.setOnClickListener(btnExportOnClick);

        //GPS定位数据
        LocationManager GpsManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = GpsManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        printGpsLocation(location);
        if( location == null ) {
            loc1 .setText("海拔：__");
            loc2 .setText("纬度：__");
            loc3 .setText("经度：__");
        }
        GpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, new GpsLocationListener());
    }

    //传感器状态改变时，进行回调
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accValues = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magValues = event.values;
        }else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            preValues = event.values;
        }

        SensorManager.getRotationMatrix(r, null, accValues, magValues);
        SensorManager.getOrientation(r, values);

        float roundDia, roundDip;
        double floatPre,values0;
        roundDip = (float) Math.acos(1 /Math.sqrt(Math.tan(values[1]) *Math.tan(values[1]) +Math.tan(values[2]) *Math.tan(values[2]) +1));
        if(values[0] > 0)
            values0 = values[0];
        else
            values0 = 2 *Math.PI +values[0];
        if(values[2] < 0)
            roundDia = (float) (values0 -Math.acos(Math.tan(values[1]) /Math.tan(roundDip)));
        else
            roundDia = (float) (values0 +Math.acos(Math.tan(values[1]) /Math.tan(roundDip)));
        if(roundDia < 0)
            while (roundDia < 0)
                roundDia += 2 *Math.PI;
        else if(roundDia > 2 *Math.PI)
            while (roundDia > 2 *Math.PI)
                roundDia -= 2 *Math.PI;
        //values[0] 手机的方位，正北为0°，顺时针180°为正，逆时针180°为负
        //values[1] 手机上下倾斜程度
        //values[2] 手机左右倾斜程度
        //把数据由弧度转化成角度
        roundDip = (float) Math.toDegrees(roundDip);
        roundDia = (float) Math.toDegrees(roundDia);
        floatPre = (int)(100 *preValues[0]) /100.0;
        dia.setText(String.valueOf(Math.round(roundDia)));
        dip.setText(String.valueOf(Math.round(roundDip)));
        pre.setText("气压：" + floatPre + " hPa");
    }

    //GPS print
    public void printGpsLocation(Location location)
    {
        int w1,w2,w3,j1,j2,j3;
        double h;
        if( location != null )
        {
            h = (int) (100 *location.getAltitude()) /100.0;
            w1 = (int) location.getLatitude();
            w2 = (int) (60 *(location.getLatitude() -w1 ));
            w3 = (int) (60 *(60 *(location.getLatitude() -w1 ) -w2));
            j1 = (int) location.getLongitude();
            j2 = (int) (60 *(location.getLongitude() -j1 ));
            j3 = (int) (60 *(60 *(location.getLongitude() -j1 ) -j2));
            loc1.setText("海拔：" + h + " m");
            loc2.setText("纬度：" + w1 + "°" + w2 + "′" + w3 + "″");
            loc3.setText("经度：" + j1 + "°" + j2 + "′" + j3 + "″");
        }
    }
    public class GpsLocationListener implements LocationListener
    {
        public void onLocationChanged(Location location) {
            printGpsLocation(location);
        }
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "ProviderDisabled : " + provider);
        }
        public void onProviderEnabled(String provider) {
            Log.d(TAG, "ProviderEnabled : " + provider);
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "StatusChanged : " + provider + status);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    //将倾向、倾角以及当前时间普通存入数据表中
    private View.OnClickListener btn1OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Auto_generated method stub
            ContentValues newRow = new ContentValues();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
            Date curDate = new Date(System.currentTimeMillis());//获取手机的当前时间
            String str = formatter.format(curDate);
            String mark1 = "0";
            newRow.put("dia", dia.getText().toString());
            newRow.put("dip", dip.getText().toString());
            newRow.put("mark", mark1.toString());
            newRow.put("time", str.toString());
            mGeoDb.insert(DB_TABLE, null, newRow);
            Cursor c = mGeoDb.query(true, DB_TABLE, new String[]{"dia", "dip","mark","time"}, null,null,null, null, null, null);
            if (c == null)
                return;
            if (c.getCount() == 0) {
                mgeolist.setText("");
                Toast.makeText(JointActivity.this, "没有数据！！！", Toast.LENGTH_LONG).show();
            } else {
                c.moveToLast();
                mgeolist.setText("倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
                while (c.moveToPrevious())
                    mgeolist.append("\n" + "倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
            }
        }
    };

    //将倾向、倾角以及当前时间星标存入数据表中
    private View.OnClickListener btnMarkOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Auto_generated method stub
            ContentValues newRow = new ContentValues();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
            Date curDate = new Date(System.currentTimeMillis());//获取手机的当前时间
            String str = formatter.format(curDate);
            String mark2 = "1";
            newRow.put("dia", dia.getText().toString());
            newRow.put("dip", dip.getText().toString());
            newRow.put("mark", mark2.toString());
            newRow.put("time", str.toString());
            mGeoDb.insert(DB_TABLE, null, newRow);
            Cursor c = mGeoDb.query(true, DB_TABLE, new String[]{"dia", "dip","mark","time"}, null,null,null, null, null, null);
            if (c == null)
                return;
            if (c.getCount() == 0) {
                mgeolist.setText("");
                Toast.makeText(JointActivity.this, "没有数据！！！", Toast.LENGTH_LONG).show();
            } else {
                c.moveToLast();
                mgeolist.setText("倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
                while (c.moveToPrevious())
                    mgeolist.append("\n" + "倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
            }
        }
    };

    //顺序列表查看当前数据表
    private View.OnClickListener btn2OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Auto_generated method stub
            Cursor c = mGeoDb.query(true, DB_TABLE, new String[]{"dia", "dip", "mark","time"},null, null, null, null, null, null);
            if (c == null)
                return;
            if (c.getCount() == 0) {
                mgeolist.setText("");
                Toast.makeText(JointActivity.this, "没有数据！！！", Toast.LENGTH_LONG).show();
            } else {
                c.moveToFirst();
                mgeolist.setText("倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
                while (c.moveToNext())
                    mgeolist.append("\n" + "倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
            }
        }
    };

    //删除数据表
    private View.OnClickListener btn3OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Auto_generated method stub
            mGeoDb.delete(DB_TABLE, "1", null);
            mgeolist.setText("");
        }
    };

    //删除最后一条数据
    private View.OnClickListener btnDelOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Auto_generated method stub
            Cursor c = mGeoDb.query(true, DB_TABLE, new String[]{"dia", "dip","mark", "time"}, null,null, null, null, null, null);
            c.moveToLast();
            mGeoDb.delete(DB_TABLE, "time = ?", new String[]{c.getString(3)});
            c = mGeoDb.query(true, DB_TABLE, new String[]{"dia", "dip","mark","time"}, null,null, null, null, null, null);
            if (c == null)
                return;
            if (c.getCount() == 0) {
                mgeolist.setText("");
                Toast.makeText(JointActivity.this, "没有数据！！！", Toast.LENGTH_LONG).show();
            } else {
                c.moveToLast();
                mgeolist.setText("倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
                while (c.moveToPrevious())
                    mgeolist.append("\n" + "倾向：" + c.getString(0) + "  倾角：" + c.getString(1) + "  标记：" + c.getString(2));
            }

        }
    };
    //导出数据文件TXT到/sdcard/Joint/
    private View.OnClickListener btnExportOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Auto_generated method stub
            String filePath = "/sdcard/Joint/";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd~HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());//获取手机的当前时间
            String str = formatter.format(curDate);
            String fileName = str + ".txt";
            writeSqlToTxt(filePath,fileName);
        }
        public  void writeSqlToTxt(String filePath,String fileName){
            String strFilePath = filePath+fileName;
            int i = 1;
            Cursor c = mGeoDb.query(true, DB_TABLE, new String[]{"dia", "dip","mark", "time"}, null,null, null, null, null, null);
            String strContent = "# No Dia Dip Mark " + pre.getText().toString() + loc1.getText().toString() + loc2.getText().toString() + loc3.getText().toString() + "\r\n";
            if (c == null)
                return;
            if (c.getCount() == 0) {
                mgeolist.setText("");
                Toast.makeText(JointActivity.this, "没有数据！！！", Toast.LENGTH_LONG).show();
            } else {
                makeFilePath(filePath, fileName);
                c.moveToFirst();
                strContent =  strContent + String.valueOf(i) + " " + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + "\r\n";
                i++;
                while (c.moveToNext()) {
                    strContent = strContent + String.valueOf(i) + " " + c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + "\r\n";
                    i++;
                }
                try {
                    File file = new File(strFilePath);
                    if (!file.exists()) {
                        Log.d("TestFile", "Create the file:" + strFilePath);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    raf.seek(file.length());
                    raf.write(strContent.getBytes());
                    raf.close();
                } catch (Exception e) {
                    Log.e("TestFile", "Error on write File:" + e);
                }
                Toast.makeText(JointActivity.this, "成功导出数据，目录为/sdcard/Joint/Time.txt!", Toast.LENGTH_LONG).show();
            }
        }
        public File makeFilePath(String filePath, String fileName) {
            File file = null;
            makeRootDirectory(filePath);
            try {
                file = new File(filePath + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return file;
        }
        public void makeRootDirectory(String filePath) {
            File file = null;
            try {
                file = new File(filePath);
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (Exception e) {
                Log.i("error:", e + "");
            }
        }
    };
}
