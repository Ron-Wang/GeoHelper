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

public class CJYActivity extends AppCompatActivity {
    final Context context = this;
    private ListView listviewkw;
    private TextView txtkw;
    private String[] names = {"砾岩","角砾岩","石英砂岩", "长石砂岩","岩屑砂岩","粉砂岩","粘土岩","页岩","石灰岩","白云岩"};
    private String[] shows = {"砾状结构，砾石可为石英等，填隙物可为砂质等"
            ,"角砾状结构，砾石棱角明显"
            ,"砂状结构，砂颗粒主要为石英，胶结物可为硅质"
            ,"砂状结构，砂颗粒主要为长石、石英等，胶结物可为硅质、泥质或铁质"
            ,"砂状结构，砂颗粒主要为硅质岩碎屑、石英和长石等，胶结物主要为硅质、泥质等"
            ,"（粉）砂状结构，砂颗粒主要为石英、长石和云母等，胶结物主要为泥质"
            ,"泥质结构，主要成分为粘土矿物，手标本一般为块状构造"
            ,"泥质结构，主要成分一般为粘土矿物，薄层理构造"
            ,"化学结构，主要矿物成分为方解石"
            ,"化学结构，主要矿物成分为白云石"};
    private int[] images = {R.drawable.s311liyan,R.drawable.s312jiaoliyan,R.drawable.s313shiyingshayan
            ,R.drawable.s321changshishayan,R.drawable.s322yanxieshayan,R.drawable.s323fenshayan
            ,R.drawable.s331niantuyan,R.drawable.s332yeyan,R.drawable.s333shihuiyan
            ,R.drawable.s341baiyunyan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kw);

        listviewkw = (ListView)findViewById(R.id.listviewkw);
        txtkw = (TextView)findViewById(R.id.KWtxt);

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for(int i = 0;i < 10;i ++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, new String[] { "image", "name" }, new int[] { R.id.icon1, R.id.text1 });
        txtkw.setText("沉积岩");
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
