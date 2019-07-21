package com.example.accuweather.data.model.openweathermap;

import com.google.gson.annotations.Expose;

public class Sys {

    @Expose
    private Float message;
    @Expose
    private String country;
    @Expose
    private Integer sunrise;
    @Expose
    private Integer sunset;

    public Float getMessage() {
        return message;
    }

    public void setMessage(Float message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

}
