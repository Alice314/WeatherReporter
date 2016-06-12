package com.wusui.weatherreporter.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wusui.weatherreporter.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/6/9.
 */

public class WeatherDB {
    private static final String DB_NAME = "weather_reporter";
    private static final int VERSION = 1;
    private static WeatherDB sWeatherDB;
    private SQLiteDatabase db;

    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context){
        if (sWeatherDB == null){
            sWeatherDB = new WeatherDB(context);
        }
        return sWeatherDB;
    }


    public void saveCity(City city){
        if (city != null){
            db.execSQL("INSERT INTO City(city) VALUES(?)",new Object[]{city.getCity()});
            db.execSQL("INSERT INTO City(city_id) VALUES(?)",new Object[]{city.getCityId()});
            db.execSQL("INSERT INTO City(city_lat) VALUES(?)",new Object[]{city.getLat()});
            db.execSQL("INSERT INTO City(city_lon) VALUES(?)",new Object[]{city.getLon()});
            db.execSQL("INSERT INTO City(province) VALUES(?)",new Object[]{city.getProvince()}) ;
        }
    }

    public List<City>loadCities(){
        List<City>list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select Province from City",null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setCityId(cursor.getString(cursor.getColumnIndex("city_id")));
                city.setCity(cursor.getString(cursor.getColumnIndex("city")));
                city.setLat(cursor.getDouble(cursor.getColumnIndex("city_lat")));
                city.setLon(cursor.getDouble(cursor.getColumnIndex("city_lon")));
                city.setProvince(cursor.getString(cursor.getColumnIndex("province")));
                list.add(city);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
