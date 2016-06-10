package com.wusui.weatherreporter.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wusui.weatherreporter.model.City;
import com.wusui.weatherreporter.model.County;
import com.wusui.weatherreporter.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/6/9.
 */

public class WeatherDB {
    public static final String DB_NAME = "weather_reporter";
    public static final int VERSION = 1;
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

    public void saveProvince(Province province){
        if (province != null){
            db.execSQL("INSERT INTO Province(province_name) VALUES(?)",new Object[]{province.getProvinceName()});
            db.execSQL("INSERT INTO Province(province_code) VALUES(?)",new Object[]{province.getProvinceCode()});
        }
    }

    public List<Province>loadProvince(){
        List<Province>list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from province",null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return list;

    }

    public void saveCity(City city){
        if (city != null){
            db.execSQL("INSERT INTO City(city_name) VALUES(?)",new Object[]{city.getCityName()});
            db.execSQL("INSERT INTO City(city_code) VALUES(?)",new Object[]{city.getCityCode()});
            db.execSQL("INSERT INTO City(province_id) VALUES(?)",new Object[]{city.getProvinceId()}) ;
        }
    }

    public List<City>loadCities(int provinceId){
        List<City>list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select province_id from City",null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void saveCounty(County county){
        if (county != null){
            db.execSQL("INSERT INTO County(county_name) VALUES(?)",new Object[]{county.getCountyName()});
            db.execSQL("INSERT INTO County(county_code) VALUES(?)",new Object[]{county.getCountyCode()});
            db.execSQL("INSERT INTO County(city_id) VALUES(?)",new Object[]{county.getCityId()});
        }
    }

    public List<County> loadCounties(int cityId){
        List<County> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select city_id from County",null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
