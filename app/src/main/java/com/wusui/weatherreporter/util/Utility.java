package com.wusui.weatherreporter.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wusui.weatherreporter.db.WeatherDB;
import com.wusui.weatherreporter.model.City;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fg on 2016/6/10.
 */

public class Utility {
    private static List<String> urlList = new ArrayList<>();
    public static List<String> handleCitiesResponse(WeatherDB weatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            List<City> cityList = gson.fromJson(response,new TypeToken<List<City>>(){}.getType());
            Logger.d("解析成功");
            for (int i = 0;i < cityList.size();i++){
                Logger.json(cityList.get(i).getCityId());
                urlList.add(cityList.get(i).getCityId());
            }
        }
        return urlList;
    }

}

