package com.thutyh.geology_helper11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2017/5/4.
 */

public class GeoDbOpenHelper extends SQLiteOpenHelper {

    public GeoDbOpenHelper(Context context, String name , SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //TODO Auto-generated method stub
    }
}