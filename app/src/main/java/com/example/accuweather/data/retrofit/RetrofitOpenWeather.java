package com.example.accuweather.data.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitOpenWeather {

    private static RetrofitOpenWeather retrofitOpenWeather;
    private static Retrofit retrofit;
    private static final String BASE_URL_OPENWEATHER = "https://api.openweathermap.org/data/2.5/";

    private RetrofitOpenWeather() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL_OPENWEATHER)
                .build();
    }

    public static RetrofitOpenWeather getInstance() {
        if (retrofitOpenWeather == null) {
            retrofitOpenWeather = new RetrofitOpenWeather();
        }
        return retrofitOpenWeather;
    }

    public OpenWeather getApiService() {
        return retrofit.create(OpenWeather.class);
    }
}