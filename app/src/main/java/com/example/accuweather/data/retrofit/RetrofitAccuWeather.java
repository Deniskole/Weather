package com.example.accuweather.data.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAccuWeather {

    private static RetrofitAccuWeather retrofitAccuWeather;
    private static Retrofit retrofit;
    private static final String BASE_URL_ACCUWEATHER = "http://dataservice.accuweather.com/";

    private RetrofitAccuWeather() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL_ACCUWEATHER)
                .build();
    }

    public static RetrofitAccuWeather getInstance() {
        if (retrofitAccuWeather == null) {
            retrofitAccuWeather = new RetrofitAccuWeather();
        }
        return retrofitAccuWeather;
    }

    public AccuWeather getApiService() {
        return retrofit.create(AccuWeather.class);
    }
}
