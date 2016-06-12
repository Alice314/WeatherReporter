package com.wusui.weatherreporter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fg on 2016/6/9.
 */

public class WeatherOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_CITY = "Create table City ("+
            "id integer primary key autoincrement,"+
            "city text,"+
            "city_id text,"+
            "city_lat real"+
            "city_lon real"+
            "province text)";

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
