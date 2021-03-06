package com.example.stchang.coolweather.util;

import android.text.TextUtils;

import com.example.stchang.coolweather.db.City;
import com.example.stchang.coolweather.db.Country;
import com.example.stchang.coolweather.db.Province;
import com.example.stchang.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Stchang on 2017/3/15.
 */

public class Utility {
    /*
 * 解析和处理服务器返回得市级数据
 * */
    public static boolean handleCountryResponse(String response, int cityid) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCountries = new JSONArray(response);
                for (int i = 0; i < allCountries.length(); i++) {
                    JSONObject countriesObj = allCountries.getJSONObject(i);
                    Country country = new Country();
                    country.setCountryName(countriesObj.getString("name"));
                    country.setWeatherId(countriesObj.getString("weather_id"));
                    country.setCityId(cityid);

                    // 通过save方法保存到数据库中
                    country.save();

                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
   * 解析和处理服务器返回得市级数据
   * */
    public static boolean handleCityResponse(String response, int provinceid) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject citiesObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(citiesObj.getString("name"));
                    city.setCityCode(citiesObj.getInt("id"));
                    city.setProvinceId(provinceid);

                    // 通过save方法保存到数据库中
                    city.save();

                }
                return true;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    * 解析和处理服务器返回得省级数据
    * */
    public static boolean handleProvincesResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    // 通过save方法保存到数据库中
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    * 将返回Json数据解析成Weather实体类
    * */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
