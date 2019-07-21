package com.example.accuweather.data.model.openweathermap;

import com.google.gson.annotations.Expose;

public class Wind {

    @Expose
    private Float speed;
    @Expose
    private Float deg;

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getDeg() {
        return deg;
    }

    public void setDeg(Float deg) {
        this.deg = deg;
    }

}
