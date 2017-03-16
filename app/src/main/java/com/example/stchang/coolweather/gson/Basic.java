package com.example.stchang.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stchang on 2017/3/16.
 */

public class Basic {
    @SerializedName("city")
    public String cityName ;

    @SerializedName("id")
    public String weatherid;

    public Update update ;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }

}
