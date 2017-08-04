package com.thutyh.geology_helper11;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BZYActivity extends AppCompatActivity {
    final Context context = this;
    private ListView listviewkw;
    private TextView txtkw;
    private String[] names = {"片麻岩","云母片岩","滑石片岩", "绿泥石片岩","千枚岩","板岩","大理岩","石英岩"};
    private String[] shows = {"粗粒结构，片麻状构造，主要矿物成分为斜长石和石英，少量黑云母"
            ,"粗粒结构，片状构造，主要矿物成分为云母和石英"
            ,"粗粒结构，片状构造，主要矿物成分为滑石"
            ,"细粒结构，片状构造，主要矿物成分为绿泥石"
            ,"细粒结构，千枚状构造，主要矿物成分为绢云母和泥质"
            ,"泥质结构，板状构造，主要矿物成分为泥质"
            ,"细粒结构，块状构造，主要矿物成分为方解石"
            ,"细粒结构，块状构造，主要矿物成分为石英"};
    private int[] images = {R.drawable.s211pianmayan,R.drawable.s212yunmupianyan,R.drawable.s213huashipianyan
            ,R.drawable.s221lvnishipianyan,R.drawable.s222qianmeiyan,R.drawable.s223banyan
            ,R.drawable.s231daliyan,R.drawable.s232shiyingyan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kw);

        listviewkw = (ListView)findViewById(R.id.listviewkw);
        txtkw = (TextView)findViewById(R.id.KWtxt);

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for(int i = 0;i < 8;i ++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, new String[] { "image", "name" }, new int[] { R.id.icon1, R.id.text1 });
        txtkw.setText("变质岩");
        listviewkw.setAdapter(simpleAdapter);
        listviewkw.setOnItemClickListener(listViewOnItemClick);
    }
    private AdapterView.OnItemClickListener listViewOnItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder
                    .setTitle(names[position])
                    .setIcon(images[position])
                    .setMessage(shows[position])
                    .setCancelable(false)
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    };
}