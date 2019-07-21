package com.example.accuweather.data.model.openweathermap;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Forecast {

    @Expose
    private String cod;

    @Expose
    private double message;

    @Expose
    private int cnt;

    @SerializedName("list")
    private ArrayList<ForecastItem> forecastItemArray;

    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<ForecastItem> getFiveDaysWeatherArray() {
        return forecastItemArray;
    }

    public void setFiveDaysWeatherArray(ArrayList<ForecastItem> threeHourWeatherArray) {
        forecastItemArray = threeHourWeatherArray;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}