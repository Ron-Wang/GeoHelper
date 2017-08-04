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

public class YJYActivity extends AppCompatActivity {
    final Context context = this;
    private ListView listviewkw;
    private TextView txtkw;
    private String[] names = {"花岗岩","花岗斑岩","流纹岩", "正长岩","正长斑岩","粗面岩","闪长岩","闪长玢岩","安山岩","辉长岩","辉绿岩","玄武岩"};
    private String[] shows = {"显晶质结构，块状构造，主要矿物成分：正长石、石英。深成侵入岩"
            ,"斑状结构，块状构造，斑晶为正长石、石英，基质为隐晶质。浅成侵入岩"
            ,"斑状结构，流纹构造，斑晶为正长石、石英，基质为隐晶质。喷出岩"
            ,"显晶质结构，块状构造，主要矿物成分：正长石。深成侵入岩"
            ,"斑状结构，斑晶主要为正长石，基质为隐晶质，块状构造。浅成侵入岩"
            ,"斑状、粗面状、球粒状结构和块状、流纹状、气孔状构造，基质为隐晶质。喷出岩"
            ,"显晶质结构，矿物成分为斜长石和角闪石，块状构造。深成岩"
            ,"斑状结构，斑晶为斜长石和少量角闪石，基质为隐晶质。浅成侵入岩"
            ,"斑状结构，斑晶为斜长石，基质为隐晶质，块状构造。喷出岩"
            ,"显晶质结构，矿物成分为辉石和斜长石，块状构造。深成岩"
            ,"隐晶质结构，矿物成分为辉石和斜长石，块状构造。深成侵入岩"
            ,"隐晶质结构，矿物成分为辉石和斜长石，块状构造。喷出岩"};
    private int[] images = {R.drawable.s411huagangyan,R.drawable.s412huagangbanyan,R.drawable.s413liuwenyan
            ,R.drawable.s421zhengchangyan,R.drawable.s422zhengchangbanyan,R.drawable.s423cumianyan
            ,R.drawable.s431shanchangyan,R.drawable.s432shanchangbinyan,R.drawable.s433anshanyan
            ,R.drawable.s441huichangyan,R.drawable.s442huilvyan,R.drawable.s443xuanwuyan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kw);

        listviewkw = (ListView)findViewById(R.id.listviewkw);
        txtkw = (TextView)findViewById(R.id.KWtxt);

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for(int i = 0;i < 12;i ++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, new String[] { "image", "name" }, new int[] { R.id.icon1, R.id.text1 });
        txtkw.setText("岩浆岩");
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
