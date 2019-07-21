package com.example.accuweather.data.model.openweathermap;

import com.google.gson.annotations.Expose;

public class Clouds {

    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
