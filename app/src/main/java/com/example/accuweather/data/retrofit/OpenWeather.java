package com.example.accuweather.data.retrofit;

import com.example.accuweather.data.model.openweathermap.Forecast;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OpenWeather {
    @GET("forecast")
    Observable<Forecast> getForecast(@QueryMap Map<String, String> params);
}
