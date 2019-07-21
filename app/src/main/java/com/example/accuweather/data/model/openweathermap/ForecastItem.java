package com.example.accuweather.data.model.openweathermap;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastItem {
    @Expose
    private Long dt;
    @Expose
    private Main main;
    @SerializedName("weather")
    private List<Weather> weatherList;
    @Expose
    private Clouds clouds;
    @Expose
    private Wind wind;
    @Expose
    private Sys sys;

    @SerializedName("dt_txt")
    private String dtTxt;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeatherArray() {
        return weatherList;
    }

    public void setWeatherArray(List<Weather> weatherArray) {
        weatherList = weatherArray;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}