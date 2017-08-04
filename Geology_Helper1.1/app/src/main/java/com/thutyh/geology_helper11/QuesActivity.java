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

public class QuesActivity extends AppCompatActivity {
    final Context context = this;
    private ListView listviewkw;
    private TextView txtkw;
    private String[] names = {"节理、解理及层理的区别","岩石构造与结构的区别","三大岩类的演化关系"};
    private String[] shows = {"节理：岩石在构造力的作用下发生破裂,而且破裂面两侧的岩石没有发生明显位移的一种地质构造." +
            "如果两侧岩石发生明显位移了,就称为断层.节理和断层合称为断裂构造." + "\n" +
            "解理：某些结晶矿物受外力后,会始终沿着一定方向发生破裂（即使受力方向不同,破裂面方向仍然相同）,并形成光滑破裂面的现象." +
            "原因是晶体矿物内部格架中,某些方向化学键较薄弱,容易受破坏.当然有些矿物有解理,有些则没有." + "\n" +
            "层理：沉积岩层内部的成层性特征.是沉积物沉积时形成的.这些成层性可以是因沉积物粒度不同体现出来的,也可以是颜色、成分等不同而体现出来的,层比较稳定、明显." +
            "层理可以分为水平层理、斜层理、交错层理、波状层理等多种类型,不同类型反映了当时沉积时的介质（水、空气）动力条件."
            ,"岩石结构：岩石中的矿物的结晶程度,颗粒大小,和形状以及彼此间的组合方式叫结构.(例如岩浆岩有：等粒结构,玻璃质结构等)\n " +
            "岩石构造：岩石中矿物集合体之间或集合体和岩石其他组成部分之间的排列方式以及填充方式叫构造.（岩浆岩的块状构造,沉积岩的层状构造等）"
            ,"三大类岩石具有不同的形成条件和环境,而岩石形成所需的环境条件又会随着地质作用的进行不断地发生变化." +
            "沉积岩和岩浆岩可以通过变质作用形成变质岩.在地表常温、常压条件下,岩浆岩和变质岩又可以通过母岩的风、剥蚀和一系列的沉积作用而形成沉积岩." +
            "变质岩和沉积岩当进入地下深处后,在高温高压条件下又会发生熔融形成岩浆,经结晶作用而变成岩浆岩." +
            "因此,在地球的岩石圈内,三大岩类处于不断演化过程之中."};
    private int[] images = {R.drawable.s000,R.drawable.s000,R.drawable.s000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kw);

        listviewkw = (ListView)findViewById(R.id.listviewkw);
        txtkw = (TextView)findViewById(R.id.KWtxt);

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for(int i = 0;i < 3;i ++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, new String[] { "image", "name" }, new int[] { R.id.icon1, R.id.text1 });
        txtkw.setText("问题集锦");
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