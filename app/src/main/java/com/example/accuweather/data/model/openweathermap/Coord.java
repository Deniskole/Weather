package com.example.accuweather.data.model.openweathermap;

import com.google.gson.annotations.Expose;

public class Coord {

    @Expose
    private Float lon;
    @Expose
    private Float lat;

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

}
