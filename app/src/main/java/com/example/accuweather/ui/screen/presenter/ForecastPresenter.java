package com.example.accuweather.ui.screen.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.accuweather.Utils;
import com.example.accuweather.data.model.accuweather.forecast.AccuWeatherForecast;
import com.example.accuweather.data.model.accuweather.location.AccuWeatherGeoposition;
import com.example.accuweather.data.model.openweathermap.Forecast;
import com.example.accuweather.data.retrofit.AccuWeather;
import com.example.accuweather.data.retrofit.OpenWeather;
import com.example.accuweather.data.retrofit.RetrofitAccuWeather;
import com.example.accuweather.data.retrofit.RetrofitOpenWeather;
import com.example.accuweather.ui.screen.ForecastActivity;
import com.example.accuweather.ui.screen.ForecastListView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.accuweather.Utils.ACCUWEATHER_API_KEY;

public class ForecastPresenter {
    private ForecastListView view;

    public ForecastPresenter(ForecastListView view) {
        this.view = view;
    }

    public void loadData() {
        getOpenweatherForecast(Utils.getCurCity((Context) view));
        getAccuweatherForecast(Utils.getCurCityId((Context) view));
    }


    @SuppressLint("CheckResult")
    public void getAccuWeatherCityId(float latitude, float longitude, final ForecastActivity context) {
        RetrofitAccuWeather retrofitAccuWeather = RetrofitAccuWeather.getInstance();
        AccuWeather accuWeather = retrofitAccuWeather.getApiService();
        Map<String, String> accuweatherParams = new HashMap<>();
        accuweatherParams.put("q", latitude + "," + longitude);
        accuweatherParams.put("apikey", ACCUWEATHER_API_KEY);
        accuWeather.getLocationKeyForCoordinates("locations/v1/cities/geoposition/search", accuweatherParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultObserver<AccuWeatherGeoposition>() {
                    @Override
                    public void onNext(AccuWeatherGeoposition accuWeatherLocation) {
                        Utils.saveCurPosition(context, accuWeatherLocation.getEnglishName(), accuWeatherLocation.getKey());
                        Intent intent = new Intent(context, ForecastActivity.class);
                        context.startActivity(intent);
                        Toast.makeText(context, accuWeatherLocation.getEnglishName() + " Saved...", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @SuppressLint("CheckResult")
    public void getOpenweatherForecast(String cityId) {
        RetrofitOpenWeather retrofitOpenWeather = RetrofitOpenWeather.getInstance();
        OpenWeather openWeather = retrofitOpenWeather.getApiService();
        Map<String, String> params = new HashMap<>();
        params.put("appid", Utils.OPENWEATHER_API_KEY);
        params.put("q", String.valueOf(cityId));
        params.put("units", "metric");
        openWeather.getForecast(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultObserver<Forecast>() {
                    @Override
                    public void onNext(Forecast forecast) {
                        view.showHoursForecast(forecast.getFiveDaysWeatherArray());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @SuppressLint("CheckResult")
    public void getAccuweatherForecast(String cityId) {
        RetrofitAccuWeather retrofitAccuWeather = RetrofitAccuWeather.getInstance();
        AccuWeather accuWeather = retrofitAccuWeather.getApiService();

        Map<String, String> accuweatherParams = new HashMap<>();
        accuweatherParams.put("metric", "true");
        accuweatherParams.put("apikey", ACCUWEATHER_API_KEY);

        accuWeather.getAccuWeatherForecast("forecasts/v1/daily/5day/" + cityId, accuweatherParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultObserver<AccuWeatherForecast>() {
                    @Override
                    public void onNext(AccuWeatherForecast accuWeatherForecast) {
                        view.showDaysForecast(accuWeatherForecast.getDailyForecasts());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
