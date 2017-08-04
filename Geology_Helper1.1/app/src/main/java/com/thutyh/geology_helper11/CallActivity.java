package com.thutyh.geology_helper11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CallActivity extends AppCompatActivity {

    private TextView txtCall;
    private Button btnCall1,btnCall2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        txtCall = (TextView)findViewById(R.id.TxtCall);
        txtCall.setText("        GeoHelper(地质助手)是一款用于野外测量结构面产状、数据存储导出、进行统计绘图以及节理模拟的Android软件。\n        本APP由清华大学水利系徐文杰老师指导、王荣鑫带领的小组进行开发测试，目前尚处于1.0版本，还有很多需要完善的地方。\n        下面是相关的联系方式：\n地址：\n北京市海淀区清华大学水利系岩土所\n电话：\n010-62782301\n邮箱：\n徐文杰：xwjljh@126.com\n王荣鑫：wangrongxin168@163.com");
        btnCall1 = (Button)findViewById(R.id.btnCall1);
        btnCall2 = (Button)findViewById(R.id.btnCall2);

        btnCall1.setOnClickListener(btnCall1Onclick);
        btnCall2.setOnClickListener(btnCall2Onclick);
    }
    private View.OnClickListener btnCall1Onclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(CallActivity.this,UrlXuActivity.class);
            startActivity(intent);
        }

    };
    private View.OnClickListener btnCall2Onclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(CallActivity.this,UrlRonActivity.class);
            startActivity(intent);
        }

    };
}
