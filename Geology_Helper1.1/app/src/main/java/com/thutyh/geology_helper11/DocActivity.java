package com.thutyh.geology_helper11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocActivity extends AppCompatActivity {

    private Button btnkw,btnyjy,btncjy,btnbzy,btnques,btnquwei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

        btnkw = (Button)findViewById(R.id.btnkw);
        btnyjy = (Button)findViewById(R.id.btnyjy);
        btncjy = (Button)findViewById(R.id.btncjy);
        btnbzy = (Button)findViewById(R.id.btnbzy);
        btnques = (Button)findViewById(R.id.btnq);
        btnquwei = (Button)findViewById(R.id.btnqw);

        btnkw.setOnClickListener(btnkwOnClick);
        btnyjy.setOnClickListener(btnyjyOnClick);
        btncjy.setOnClickListener(btncjyOnClick);
        btnbzy.setOnClickListener(btnbzyOnClick);
        btnques.setOnClickListener(btnquesOnClick);
        btnquwei.setOnClickListener(btnquweiOnClick);

    }
    public View.OnClickListener btnkwOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(DocActivity.this,KWActivity.class);
            startActivity(intent);
        }
    };
    public View.OnClickListener btnyjyOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(DocActivity.this,YJYActivity.class);
            startActivity(intent);
        }
    };
    public View.OnClickListener btncjyOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(DocActivity.this,CJYActivity.class);
            startActivity(intent);
        }
    };
    public View.OnClickListener btnbzyOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(DocActivity.this,BZYActivity.class);
            startActivity(intent);
        }
    };
    public View.OnClickListener btnquesOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(DocActivity.this,QuesActivity.class);
            startActivity(intent);
        }
    };
    public View.OnClickListener btnquweiOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(DocActivity.this,QuweiActivity.class);
            startActivity(intent);
        }
    };
}
