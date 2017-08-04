package com.thutyh.geology_helper11;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    //定义真机的Sensor管理器
    private SensorManager mSensorManager;
    // 定义模拟器的Sensor管理器
    // private SensorManagerSimulator mSensorManager;

    TextView all;
    TextView etOrientation;
    TextView etMagnetic;
    TextView etTemerature;
    TextView etLight;
    TextView etPressure;
    TextView etAccelerometer;
    TextView etGyroscope;
    TextView etProximity;
    TextView etGravity;
    TextView etLinear_acc;
    TextView etRotation;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        // 获取界面上的EditText组件
        all = (TextView) findViewById(R.id.all);
        etOrientation = (TextView) findViewById (R.id.etOrientation);
        etMagnetic = (TextView) findViewById(R.id.etMagnetic);
        etTemerature = (TextView) findViewById(R.id.etTemerature);
        etLight = (TextView) findViewById(R.id.etLight);
        etPressure = (TextView) findViewById(R.id.etPressure);
        etAccelerometer = (TextView) findViewById(R.id.etAccelerometer);
        etGyroscope = (TextView) findViewById(R.id.etGyroscope);
        etProximity = (TextView) findViewById(R.id.etProximity);
        etGravity = (TextView) findViewById(R.id.etGravity);
        etLinear_acc = (TextView) findViewById(R.id.etLinear_acc);
        etRotation = (TextView) findViewById(R.id.etRotation);
        // 获取真机的传感器管理服务
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // 获取传感器模拟器的传感器管理服务
        // mSensorManager = SensorManagerSimulator.getSystemService(this,SENSOR_SERVICE);
        // 连接传感器模拟器
        //mSensorManager.connectSimulator();

        //从传感器管理器中获得全部的传感器列表
        List<Sensor> allSensors =  mSensorManager.getSensorList(Sensor.TYPE_ALL);
        all.setText("经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n");

        //显示每个传感器的具体信息
        for (Sensor s : allSensors) {

            String tempString = "\n" + "  设备名称：" + s.getName() + "\n" + "  设备版本：" + s.getVersion() + "\n" + "  供应商："
                    + s.getVendor() + "\n";

            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    all.setText(all.getText().toString() + s.getType() + " 加速度传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    all.setText(all.getText().toString() + s.getType() + " 温度传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    all.setText(all.getText().toString() + s.getType() + " 线加速度传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    all.setText(all.getText().toString() + s.getType() + " 旋转矢量传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_GRAVITY:
                    all.setText(all.getText().toString() + s.getType() + " 重力传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    all.setText(all.getText().toString() + s.getType() + " 陀螺仪传感器gyroscope" + tempString + "\n");
                    break;
                case Sensor.TYPE_GAME_ROTATION_VECTOR:
                    all.setText(all.getText().toString() + s.getType() + " 游戏旋转矢量传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                    all.setText(all.getText().toString() + s.getType() + " 地磁旋转矢量传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                    all.setText(all.getText().toString() + s.getType() + " 未校准陀螺仪传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_HEART_RATE:
                    all.setText(all.getText().toString() + s.getType() + " 心率传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                    all.setText(all.getText().toString() + s.getType() + " 未校准磁场传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    all.setText(all.getText().toString() + s.getType() + " 步进计算器传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_STEP_DETECTOR:
                    all.setText(all.getText().toString() + s.getType() + " 步数传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_SIGNIFICANT_MOTION:
                    all.setText(all.getText().toString() + s.getType() + " 特殊行动传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                    all.setText(all.getText().toString() + s.getType() + " 湿度传感器accelerometer" + tempString + "\n");
                    break;
                case Sensor.TYPE_LIGHT:
                    all.setText(all.getText().toString() + s.getType() + " 环境光线传感器light" + tempString + "\n");
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    all.setText(all.getText().toString() + s.getType() + " 电磁场传感器magnetic field" + tempString + "\n");
                    break;
                case Sensor.TYPE_ORIENTATION:
                    all.setText(all.getText().toString() + s.getType() + " 方向传感器orientation" + tempString + "\n");
                    break;
                case Sensor.TYPE_PRESSURE:
                    all.setText(all.getText().toString() + s.getType() + " 压力传感器pressure" + tempString + "\n");
                    break;
                case Sensor.TYPE_PROXIMITY:
                    all.setText(all.getText().toString() + s.getType() + " 距离传感器proximity" + tempString + "\n");
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    all.setText(all.getText().toString() + s.getType() + " 温度传感器temperature" + tempString + "\n");
                    break;
                default:
                    all.setText(all.getText().toString() + s.getType() + " 未知传感器" + tempString + "\n");
                    break;
            }
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // 为系统的方向传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的磁场传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的温度传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的光传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的压力传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的加速度传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的陀螺仪传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的接近传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的重力传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的线性加速度传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的旋转矢量传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop()
    {
        // 程序退出时取消注册传感器监听器
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onPause()
    {
        // 程序暂停时取消注册传感器监听器
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    // 以下是实现SensorEventListener接口必须实现的方法

    @Override

    // 当传感器精度改变时回调该方法。
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float[] values = event.values;
        //真机上获取触发event的传感器类型
        int sensorType = event.sensor.getType();
        // 模拟器上获取触发event的传感器类型
        // int sensorType = event.type;
        StringBuilder sb = null;
        // 判断是哪个传感器发生改变
        switch (sensorType)
        {
            // 方向传感器
            case Sensor.TYPE_ORIENTATION://过期了，不建议使用
                sb = new StringBuilder();
                sb.append("方向传感器orientation：\n");
                sb.append("绕Z轴转过的角度：" + values[0] + "\n");
                sb.append("绕X轴转过的角度：" + values[1] + "\n");
                sb.append("绕Y轴转过的角度：" + values[2]);
                etOrientation.setText(sb.toString());
                break;
            // 磁场传感器
            case Sensor.TYPE_MAGNETIC_FIELD:
                sb = new StringBuilder();
                sb.append("电磁场传感器magnetic field：\n");
                sb.append("X方向上的磁通量：" + values[0] + "\n");
                sb.append("Y方向上的磁通量：" + values[1] + "\n");
                sb.append("Z方向上的磁通量：" + values[2]);
                etMagnetic.setText(sb.toString());
                break;
            // 温度传感器
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("温度传感器temperature：\n");
                sb.append("当前温度为：" + values[0]);
                etTemerature.setText(sb.toString());
                break;
            // 光传感器
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("环境光线传感器light：\n");
                sb.append("当前光的强度为：" + values[0]);
                etLight.setText(sb.toString());
                break;
            // 压力传感器
            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("压力传感器pressure：\n");
                sb.append("当前压力为：" + values[0]);
                etPressure.setText(sb.toString());
                break;
            case Sensor.TYPE_ACCELEROMETER:
                sb = new StringBuilder();
                sb.append("加速度传感器accelerometer：\n");
                sb.append("X方向加速度分量：" + values[0] + "\n");
                sb.append("Y方向加速度分量：" + values[1] + "\n");
                sb.append("Z方向加速度分量：" + values[2] + "\n");
                sb.append("合加速度为：" + Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2]*values[2]));
                etAccelerometer.setText(sb.toString());
                break;
            case Sensor.TYPE_GYROSCOPE:
                sb = new StringBuilder();
                sb.append("陀螺仪传感器gyroscope：\n");
                sb.append("绕X轴转动的角加速度：" + values[0] + "\n");
                sb.append("绕Y轴转动的角加速度：" + values[1] + "\n");
                sb.append("绕Z轴转动的角加速度：" + values[2]);
                etGyroscope.setText(sb.toString());
                break;
            case Sensor.TYPE_PROXIMITY:
                sb = new StringBuilder();
                sb.append("距离传感器proximity：\n");
                sb.append("1为未接近，0为接近：" + values[0]);
                etProximity.setText(sb.toString());
                break;
            case Sensor.TYPE_GRAVITY:
                sb = new StringBuilder();
                sb.append("X方向上的重力加速度分量：" + values[0] + "\n");
                sb.append("Y方向上的重力加速度分量：" + values[1] + "\n");
                sb.append("Z方向上的重力加速度分量：" + values[2]);
                etGravity.setText(sb.toString());
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                sb = new StringBuilder();
                sb.append("X方向加速度减去重力加速度的分量：" + values[0] + "\n");
                sb.append("Y方向加速度减去重力加速度的分量：" + values[1] + "\n");
                sb.append("Z方向加速度减去重力加速度的分量：" + values[2]);
                etLinear_acc.setText(sb.toString());
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                sb = new StringBuilder();
                sb.append("X方向上的角度：" + values[0] + "\n");
                sb.append("Y方向上的角度：" + values[1] + "\n");
                sb.append("Z方向上的角度：" + values[2]);
                etRotation.setText(sb.toString());
                break;
        }
    }

}
