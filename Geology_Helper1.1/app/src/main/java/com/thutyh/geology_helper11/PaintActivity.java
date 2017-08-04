package com.thutyh.geology_helper11;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class PaintActivity extends AppCompatActivity {

    final Context context = this;
    private Button btnPaint1,btnPaint2,btnPaint3,btnPaint4;
    private ImageView paint1,paint2;
    float dia1,dip1,dia2 ,dip2,dia3,dip3;
    public static final int PPoint = 1,PRose = 2,PCloud = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        paint1 = (ImageView) findViewById(R.id.ImView);
        paint2 = (ImageView) findViewById(R.id.ImView2);
        btnPaint1 = (Button) findViewById(R.id.btnPaint1);
        btnPaint2 = (Button) findViewById(R.id.btnPaint2);
        btnPaint3 = (Button) findViewById(R.id.btnPaint3);
        btnPaint4 = (Button) findViewById(R.id.btnPaint4);

        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        ViewGroup.LayoutParams l1,l2,l3,l4;
        l1 = btnPaint1.getLayoutParams();
        l2 = btnPaint2.getLayoutParams();
        l3 = btnPaint3.getLayoutParams();
        l4 = btnPaint4.getLayoutParams();
        l1.width = l2.width = l3.width = l4.width = d.getWidth() /2;

        btnPaint1.setOnClickListener(btnPaint1Onclick);
        btnPaint2.setOnClickListener(btnPaint2Onclick);
        btnPaint3.setOnClickListener(btnPaint3Onclick);
        btnPaint4.setOnClickListener(btnPaint4Onclick);
    }
/*赤平投影按钮
已经完成
*/
    private View.OnClickListener btnPaint1Onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.dialog_dia_dip, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptsView);

            final EditText text_dia1 = (EditText) promptsView.findViewById(R.id.text_dia1),
                    text_dip1 = (EditText) promptsView.findViewById(R.id.text_dip1),
                    text_dia2 = (EditText) promptsView.findViewById(R.id.text_dia2),
                    text_dip2 = (EditText) promptsView.findViewById(R.id.text_dip2),
                    text_dia3 = (EditText) promptsView.findViewById(R.id.text_dia3),
                    text_dip3 = (EditText) promptsView.findViewById(R.id.text_dip3);

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dia1 = Float.valueOf(text_dia1.getText().toString());
                                    dip1 = Float.valueOf(text_dip1.getText().toString());
                                    dia2 = Float.valueOf(text_dia2.getText().toString());
                                    dip2 = Float.valueOf(text_dip2.getText().toString());
                                    dia3 = Float.valueOf(text_dia3.getText().toString());
                                    dip3 = Float.valueOf(text_dip3.getText().toString());
                                    Bitmap bitmap = Bitmap.createBitmap(330, 330, android.graphics.Bitmap.Config.ARGB_8888),
                                            bitmap2 = Bitmap.createBitmap(330, 100, android.graphics.Bitmap.Config.ARGB_8888);
                                    Canvas canvas = new Canvas(bitmap),
                                            canvas2 = new Canvas(bitmap2);
                                    Drawable drawable = new BitmapDrawable(bitmap),
                                            drawable2 = new BitmapDrawable(bitmap2);
                                    paint1.setBackgroundDrawable(drawable);
                                    paint2.setBackgroundDrawable(drawable2);
                                    canvas.drawColor(Color.WHITE);
                                    canvas2.drawColor(Color.WHITE);
                                    Paint paint = new Paint();
                                    paint.setColor(Color.BLACK);
                                    paint.setAntiAlias(true);
                                    paint.setStyle(Paint.Style.STROKE);
                                    paint.setStrokeWidth(1);
                                    float r = 150,x0 = 165,y0 = 165,dr = 4,w = 10;
                                    canvas.drawCircle(x0, y0, r, paint);
                                    canvas.drawText("N", x0 - w/2, y0 - r - dr, paint);
                                    canvas.drawText("S", x0 - w/2, y0 + r + dr + w, paint);
                                    canvas.drawText("W", x0 - r - dr -w, y0 + w/2, paint);
                                    canvas.drawText("E", x0 + r + dr, y0 + w/2, paint);
                                    for(int i = 0;i < 36;i ++)
                                        canvas.drawLine((float)(x0 - r * Math.cos(Math.PI / 18 * i)),(float)(y0 - r * Math.sin(Math.PI/18 * i)),(float)(x0 - (r + dr) * Math.cos(Math.PI / 18 * i)),(float)(y0  - (r + dr) * Math.sin(Math.PI/18 * i)) , paint);
                                    paint.setColor(Color.BLUE);
                                    yuanhu(dip1,dia1,r,canvas,paint);
                                    paint.setColor(Color.GREEN);
                                    yuanhu(dip2,dia2,r,canvas,paint);
                                    paint.setColor(Color.RED);
                                    yuanhu(dip3,dia3,r,canvas,paint);
                                    paint.setColor(Color.BLACK);
                                    paint.setStyle(Paint.Style.FILL);
                                    canvas2.drawText("节理面1(BULE)的倾向："+String.valueOf(dia1)+"    倾角："+String.valueOf(dip1),20,20,paint);
                                    canvas2.drawText("节理面2(GREEN)的倾向："+String.valueOf(dia2)+"    倾角："+String.valueOf(dip2),20,40,paint);
                                    canvas2.drawText("节理面3(RED)的倾向："+String.valueOf(dia3)+"    倾角："+String.valueOf(dip3),20,60,paint);
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    };
    public void yuanhu(float alpha,float beta,float r,Canvas canvas,Paint paint)//赤平投影圆弧画法
    {
        double t,x,y,ox,oy;
        float x0 = 165,y0 =165;
        alpha = (float)(alpha *Math.PI/180);
        beta = (float)(beta *Math.PI/180);
        ox = r * Math.tan(alpha) * Math.sin(beta) + x0;
        oy = y0 - r * Math.tan(alpha) * Math.cos(beta) + r / Math.cos(alpha);
        for(t = 0;t <= 2 * Math.PI;t = t + 0.001) {
            x = r * Math.tan(alpha) * Math.sin(beta) + x0 + r / Math.cos(alpha) * Math.sin(t);
            y = y0 - r * Math.tan(alpha) * Math.cos(beta) + r / Math.cos(alpha) * Math.cos(t);
            if ((x - x0) * (x - x0) + (y - y0) * (y - y0) <= r * r) {
                canvas.drawLine((int) ox, (int) oy,(int) x, (int) y, paint);
            }
            ox = x;
            oy = y;
        }
    }
    /*节理极点图按钮
    已完成
    */
    private View.OnClickListener btnPaint2Onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PaintActivity.this,FileActivity.class);
            startActivityForResult(intent,PPoint);
        }
    };

    /*节理玫瑰花图按钮
    已完成
    */
    private View.OnClickListener btnPaint3Onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PaintActivity.this,FileActivity.class);
            startActivityForResult(intent,PRose);
        }
    };
    /*节理等密度图按钮
    已完成
    */
    private View.OnClickListener btnPaint4Onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PaintActivity.this,FileActivity.class);
            startActivityForResult(intent,PCloud);
        }
    };

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        if(PPoint == requestCode){
            Bundle bundle = null;
            if(data!=null&&(bundle=data.getExtras())!=null){
                TxtToPoint(bundle.getString("file"));
            }
        }
        else if(PRose == requestCode){
            Bundle bundle = null;
            if(data!=null&&(bundle=data.getExtras())!=null){
                TxtToRose(bundle.getString("file"));
            }
        }
        else if(PCloud == requestCode){
            Bundle bundle = null;
            if(data!=null&&(bundle=data.getExtras())!=null){
                TxtToCloud(bundle.getString("file"));
            }
        }
    }

    public void TxtToPoint(String filePath){
        float diap[] = null;
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int line = 1;
                String Txtcon = "";
                while((lineTxt = bufferedReader.readLine()) != null){
                    if(line != 1)
                        Txtcon = Txtcon + " " + lineTxt;
                    else
                        line = 0;
                }
                String[] s = Txtcon.split(" ");
                diap = new float[s.length -1];
                for(int i = 1;i < s.length;i ++){
                    diap[i -1] = Float.valueOf(s[i]);
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap bitmap = Bitmap.createBitmap(330, 330, android.graphics.Bitmap.Config.ARGB_8888),
                bitmap2 = Bitmap.createBitmap(330, 100, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap),
                canvas2 = new Canvas(bitmap2);
        Drawable drawable = new BitmapDrawable(bitmap),
                drawable2 = new BitmapDrawable(bitmap2);
        paint1.setBackgroundDrawable(drawable);
        paint2.setBackgroundDrawable(drawable2);
        canvas.drawColor(Color.WHITE);
        canvas2.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        float r = 150,x0 = 165,y0 = 165,w = 10;
        for(int i = 1;i < 10;i ++)
            canvas.drawCircle(x0, y0, r * i / 9, paint);
        for(int i = 0;i < 18;i ++)
            canvas.drawLine((float)(x0 - r * Math.cos(Math.PI / 18 * i)),(float)(y0 - r * Math.sin(Math.PI/18 * i)),(float)(x0 + r * Math.cos(Math.PI / 18 * i)),(float)(y0 + r * Math.sin(Math.PI/18 * i)) , paint);
        canvas.drawText("N", x0 - w/2, y0 - r, paint);
        canvas.drawText("S", x0 - w/2, y0 + r + w, paint);
        canvas.drawText("W", x0 - r - w, y0 + w/2, paint);
        canvas.drawText("E", x0 + r, y0 + w/2, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas2.drawText("节理统计数量为：" + String.valueOf(diap.length /4),20,20,paint);
        paint.setStrokeWidth(2);
        for(int i = 0;i < diap.length /4;i ++) {
            if(diap[4 * i + 3] ==0)
                paint.setColor(Color.BLUE);
            else
                paint.setColor(Color.RED);
            canvas.drawPoint((float) (x0 + Math.sqrt(2) * r * Math.sin(diap[4 * i + 2] / 180 * Math.PI / 2) * Math.sin(diap[4 * i + 1] / 180 * Math.PI)), (float) (y0 - Math.sqrt(2) * r * Math.sin(diap[4 * i + 2] / 180 * Math.PI / 2) * Math.cos(diap[4 * i + 1] / 180 * Math.PI)), paint);
        }
    }

    public void TxtToRose(String filePath){
        float diap[] = null;
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int line = 1;
                String Txtcon = "";
                while((lineTxt = bufferedReader.readLine()) != null){
                    if(line != 1)
                        Txtcon = Txtcon + " " + lineTxt;
                    else
                        line = 0;
                }
                String[] s = Txtcon.split(" ");
                diap = new float[s.length -1];
                for(int i = 1;i < s.length;i ++){
                    diap[i -1] = Float.valueOf(s[i]);
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap bitmap = Bitmap.createBitmap(330, 330, android.graphics.Bitmap.Config.ARGB_8888),
                bitmap2 = Bitmap.createBitmap(330, 100, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap),
                canvas2 = new Canvas(bitmap2);
        Drawable drawable = new BitmapDrawable(bitmap),
                drawable2 = new BitmapDrawable(bitmap2);
        paint1.setBackgroundDrawable(drawable);
        paint2.setBackgroundDrawable(drawable2);
        canvas.drawColor(Color.WHITE);
        canvas2.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        float r = 150,x0 = 165,y0 = 200,w = 10;

        for(int i = 1;i <= 10;i++) {
            double ox = x0 + r / 10 * i;
            double oy = y0;
            double x,y;
            for (double t = 0; t <= Math.PI; t = t + 0.0005) {
                x = x0 + r / 10 * i * Math.cos(t);
                y = y0 - r / 10 * i * Math.sin(t);
                canvas.drawLine((int) ox, (int) oy, (int) x, (int) y, paint);
                ox = x;
                oy = y;
            }
            canvas.drawText(String.valueOf(10 *i),x0 + r / 10 *i -w,y0 + 20,paint);
        }
        canvas.drawText("(%)",x0 + r -w,y0 + 40,paint);
        for(int i=0;i<=18;i++)
        {
            canvas.drawLine(x0, y0, (float)(x0 + r * Math.cos(Math.PI / 18 * i)),(float)(y0 - r * Math.sin(Math.PI/18 * i)) , paint);
        }
        canvas.drawText("N", x0 - w/2, y0 - r, paint);
        canvas.drawText("W", x0 - r - w, y0 + w/2, paint);
        canvas.drawText("E", x0 + r, y0 + w/2, paint);
        paint.setColor(Color.RED);
        float[] num = new float[18];
        float[] mean = new float[18];
        float maxnum = 0;
        for(int i= 0;i<18;i++){
            num[i] = 0;
            mean[i] = 0;
            for(int j = 0;j < diap.length /4;j ++){
                if(diap[4 *j +1] % 180 >= 10* i & diap[4 *j +1] % 180< 10* (i +1)) {
                    num[i] ++;
                    mean[i] += diap[4 *j +1] %180;
                }
            }
            if(num[i] != 0)
                mean[i] = mean[i] /num[i];
            if(maxnum < num[i])
                maxnum = num[i];
        }
        float[] xarr = new float[18],
                yarr = new float[18];
        for(int i = 0;i < 18;i ++){
            num[i] = num[i] /maxnum *r;
            xarr[i] = (float) (num[i] * Math.cos(-mean[i] /180 *Math.PI + Math.PI));
            yarr[i] = (float) (num[i] * Math.sin(-mean[i] /180 *Math.PI + Math.PI));
        }
        canvas.drawLine(x0, y0, x0 + xarr[0],y0 - yarr[0] , paint);
        for(int i = 0;i < 18;i ++){
            if(i ==17)
                canvas.drawLine(x0 + xarr[i],y0 - yarr[i] ,x0, y0, paint);
            else
                canvas.drawLine(x0 + xarr[i],y0 - yarr[i] ,x0 + xarr[i+1],y0 - yarr[i+1], paint);
        }
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas2.drawText("节理统计数量为：" + String.valueOf(diap.length /4),20,20,paint);
        canvas2.drawText("节理统计最大数为：" + String.valueOf(maxnum),20,40,paint);
    }

    public void TxtToCloud(String filePath){
        float diap[] = null;
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int line = 1;
                String Txtcon = "";
                while((lineTxt = bufferedReader.readLine()) != null){
                    if(line != 1)
                        Txtcon = Txtcon + " " + lineTxt;
                    else
                        line = 0;
                }
                String[] s = Txtcon.split(" ");
                diap = new float[s.length -1];
                for(int i = 1;i < s.length;i ++){
                    diap[i -1] = Float.valueOf(s[i]);
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap bitmap = Bitmap.createBitmap(330, 330, android.graphics.Bitmap.Config.ARGB_8888),
                bitmap2 = Bitmap.createBitmap(330, 100, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap),
                canvas2 = new Canvas(bitmap2);
        Drawable drawable = new BitmapDrawable(bitmap),
                drawable2 = new BitmapDrawable(bitmap2);
        paint1.setBackgroundDrawable(drawable);
        paint2.setBackgroundDrawable(drawable2);
        canvas.drawColor(Color.WHITE);
        canvas2.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        float r = 150,x0 = 165,y0 = 165,w = 10;
        int n = 150;
        int maxnum = 0;
        canvas.drawCircle(x0,y0,r,paint);
        paint.setStyle(Paint.Style.FILL);
        float[] xn = new float[diap.length /4],
                yn = new float[diap.length /4];
        int[] num = new int[(2 *n +1) *(2 *n +1)];
        for(int i = 0;i <diap.length /4;i ++){
            xn[i] = (float)(x0 + Math.sqrt(2) * r * Math.sin(diap[4 * i + 2] / 180 * Math.PI / 2) * Math.sin(diap[4 * i + 1] / 180 * Math.PI));
            yn[i] = (float)(y0 - Math.sqrt(2) * r * Math.sin(diap[4 * i + 2] / 180 * Math.PI / 2) * Math.cos(diap[4 * i + 1] / 180 * Math.PI));
        }
        for(int i = 0;i < 2 *n +1;i ++){
            for(int j = 0;j < 2 *n +1;j++){
                float x00,y00;
                x00 = i *r /n + x0 -r;
                y00 = j *r /n + y0 -r;
                if((x00 - x0) *(x00 - x0) +(y00 -y0)*(y00 -y0) > r *r)
                    num[(2 * n +1) *i + j] = 0;
                else if((x00 - x0) *(x00 - x0) +(y00 -y0)*(y00 -y0) < 0.81 *r *r){
                    num[(2 * n +1) *i + j] = 0;
                    for(int k = 0;k <diap.length /4;k ++)
                        if((xn[k] -x00)*(xn[k] -x00) + (yn[k] -y00)*(yn[k] -y00) <= 0.01 *r *r)
                            num[(2 * n +1) *i + j] ++;
                }
                else{
                    num[(2 * n +1) *i + j] = 0;
                    for(int k = 0;k <diap.length /4;k ++) {
                        if ((xn[k] - x00) * (xn[k] - x00) + (yn[k] - y00) * (yn[k] - y00) <= 0.01 *r *r)
                            num[(2 * n +1) *i + j]++;
                        float r1 = (float) Math.sqrt((x00 - x0) *(x00 - x0) +(y00 -y0)*(y00 -y0)),
                                r2 = 2 *r - r1;
                        x00 = x0 -(x00 - x0) *r2 /r1;
                        y00 = y0 -(y00 -y0) *r2 /r1;
                        if ((xn[k] - x00) * (xn[k] - x00) + (yn[k] - y00) * (yn[k] - y00) <= 0.01 *r *r)
                            num[(2 * n +1) *i + j]++;
                    }
                }
                if(maxnum < num[(2 * n +1) *i + j])
                    maxnum = num[(2 * n +1) *i + j];
            }
        }
        canvas.drawText("N", x0 - w/2, y0 - r, paint);
        canvas.drawText("S", x0 - w/2, y0 + r + w, paint);
        canvas.drawText("W", x0 - r - w, y0 + w/2, paint);
        canvas.drawText("E", x0 + r, y0 + w/2, paint);
        for(int i = 0;i < 2 *n +1;i ++){
            for(int j = 0;j < 2 *n +1;j++){
                if((i *r /n -r) *(i *r /n -r) +(j *r /n -r) *(j *r /n -r) <= r *r) {
                    if(1.0 *num[(2 * n +1) *i + j] /maxnum >0.5)
                        paint.setColor(Color.argb(255,50 *Math.round((float) (10.0 *num[(2 * n +1) *i + j] /maxnum -5)),50 *Math.round((float) (10 -10.0 *num[(2 * n +1) *i + j] /maxnum)),0));//R-G
                    else
                        paint.setColor(Color.argb(255,0,50 *Math.round((float) (10.0 *num[(2 * n +1) *i + j] /maxnum)),50 *Math.round((float) (5 -10.0 *num[(2 * n +1) *i + j] /maxnum))));//G-B
                    canvas.drawPoint(i * r / n + x0 - r, j * r / n + y0 - r, paint);
                }
            }
        }
        paint.setColor(Color.argb(255,0,0,250));
        canvas2.drawRect(15,0,30,20,paint);
        for(int i = 1;i < 10;i ++){
            if(i /10.0 >0.5)
                paint.setColor(Color.argb(255,50 *(i -5),50 *(10 -i),0));//R-G
            else
                paint.setColor(Color.argb(255,0,50 *i,50 *(5 -i)));//G-B
            canvas2.drawRect(30 *i,0,30 *(i +1),20,paint);
        }
        paint.setColor(Color.argb(255,250,0,0));
        canvas2.drawRect(300,0,315,20,paint);
        paint.setColor(Color.BLACK);
        for(int i = 0;i < 10;i ++){
            canvas2.drawText(String.valueOf(10 *i +5),30 *i +25,30,paint);
        }
        canvas2.drawText(String.valueOf(0),10,30,paint);
        canvas2.drawText(String.valueOf(100),310,30,paint);
        canvas2.drawText("（%）",305,45,paint);
        canvas2.drawText("单点节理最大数："+String.valueOf(maxnum),200,55,paint);
    }
}