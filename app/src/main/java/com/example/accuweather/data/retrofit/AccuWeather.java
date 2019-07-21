package com.example.accuweather.data.retrofit;

import com.example.accuweather.data.model.accuweather.autocomplete.AccuWeatherAutocomplete;
import com.example.accuweather.data.model.accuweather.forecast.AccuWeatherForecast;
import com.example.accuweather.data.model.accuweather.location.AccuWeatherGeoposition;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface AccuWeather {

    @GET
    Observable<AccuWeatherForecast> getAccuWeatherForecast(@Url String url, @QueryMap Map<String, String> params);

    @GET
    Observable<AccuWeatherGeoposition> getLocationKeyForCoordinates(@Url String url, @QueryMap Map<String, String> params);

    @GET
    Observable<List<AccuWeatherAutocomplete>> getCities(@Url String url, @QueryMap Map<String, String> params);
}
