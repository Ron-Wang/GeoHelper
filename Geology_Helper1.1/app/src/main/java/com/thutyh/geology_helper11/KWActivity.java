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

public class KWActivity extends AppCompatActivity {
    final Context context = this;
    private ListView listviewkw;
    private TextView txtkw;
    private String[] names = {"滑石","高岭石","绿泥石","黑云母","白云母","方解石","白云石","角闪石","辉石","正长石","斜长石","石英"};
    private String[] shows = {"白、灰、淡黄、淡绿色，薄片状、致密块状，硬度1，蜡状光泽，摸之有滑感"
            ,"白、因含杂质可呈浅黄、浅褐色，土状，硬度1-1.5"
            ,"浅绿至深绿色，片状，硬度2-2.5，珍珠或玻璃光泽"
            ,"褐、黑色，片状，硬度2.5-3，一组极完全解理，珍珠或玻璃光泽，薄片透明，有弹性"
            ,"无色、银白色，片状，硬度2.5-3，一组极完全解理，珍珠或玻璃光泽，薄片透明，有弹性"
            ,"白色，菱面体，粒状，硬度3，三组完全解理，玻璃光泽，遇稀盐酸剧烈起泡"
            ,"灰白、淡红色，菱面体，粒状，硬度3.5-4，三组完全解理，玻璃光泽，粉末可与稀盐酸反应起泡"
            ,"黑、黑绿色，针柱状，硬度5-6，两组解理，玻璃光泽"
            ,"黑色，短柱状粒状，硬度5-6，两组解理，玻璃光泽"
            ,"多为肉红色，板条状，粒状，硬度6，两组解理，玻璃光泽"
            ,"灰白色，板条状，粒状，硬度6，两组解理，玻璃光泽"
            ,"无色、烟灰色，岩石中常为粒状、水晶为六方柱状，硬度7，无解理，玻璃或油脂光泽"};
    private int[] images = {R.drawable.s111huashi,R.drawable.s112gaolingshi,R.drawable.s113lvnishi
            ,R.drawable.s121heiyunmu,R.drawable.s122baiyunmu,R.drawable.s123fangjieshi
            ,R.drawable.s131baiyunshi,R.drawable.s132jiaoshanshi,R.drawable.s133huishi
            ,R.drawable.s141zhengchangshi,R.drawable.s142xiechangshi,R.drawable.s143shiying};

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
        txtkw.setText("矿物");
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
