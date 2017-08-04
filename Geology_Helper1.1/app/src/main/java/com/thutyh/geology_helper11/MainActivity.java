package com.thutyh.geology_helper11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnJoint,btnPaint,btnUrl,btnDoc,btnCal,btnCall;//按钮变量
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJoint = (Button) findViewById(R.id.BtnJoint);//获取界面按钮
        btnPaint = (Button) findViewById(R.id.BtnPaint);
        btnUrl = (Button) findViewById(R.id.BtnUrl);
        btnDoc = (Button) findViewById(R.id.BtnKnow);
        btnCal = (Button) findViewById(R.id.BtnCal);
        btnCall = (Button) findViewById(R.id.BtnCall);
        imageview = (ImageView)findViewById(R.id.imageview1);



        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        ViewGroup.LayoutParams l1,l2,l3,l4,l5,l6,l0;
        l1 = btnJoint.getLayoutParams();
        l2 = btnPaint.getLayoutParams();
        l3 = btnUrl.getLayoutParams();
        l4 = btnDoc.getLayoutParams();
        l5 = btnCal.getLayoutParams();
        l6 = btnCall.getLayoutParams();
        l0 = imageview.getLayoutParams();
        l1.width = l2.width = l3.width = l4.width = l5.width = l6.width = d.getWidth() /2;
        l1.height = l2.height = l3.height = l4.height = l5.height = l6.height = d.getWidth() /3;
        l0.width = d.getWidth();
        l0.height = d.getWidth() *25 /120;


        btnJoint.setOnClickListener(btnJointOnclick);
        btnPaint.setOnClickListener(btnPaintOnclick);
        btnCall.setOnClickListener(btnCallOnclick);
        btnUrl.setOnClickListener(btnUrlOnclick);
        btnCal.setOnClickListener(btnCalOnclick);
        btnDoc.setOnClickListener(btnDocOnclick);
    }
    private View.OnClickListener btnJointOnclick = new View.OnClickListener(){
      @Override
        public void onClick(View v){
          Intent intent = new Intent(MainActivity.this,JointActivity.class);
          startActivity(intent);
      }
    };
    private View.OnClickListener btnPaintOnclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this,PaintActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener btnCallOnclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this,CallActivity.class);
            startActivity(intent);
        }

    };
    private View.OnClickListener btnUrlOnclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this,UrlActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener btnCalOnclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this,DateCalActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener btnDocOnclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this,DocActivity.class);
            startActivity(intent);
        }
    };

}
