package com.example.accuweather.ui.screen.presenter;

import android.annotation.SuppressLint;

import com.example.accuweather.data.model.accuweather.autocomplete.AccuWeatherAutocomplete;
import com.example.accuweather.data.retrofit.AccuWeather;
import com.example.accuweather.data.retrofit.RetrofitAccuWeather;
import com.example.accuweather.ui.screen.SearchActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.accuweather.Utils.ACCUWEATHER_API_KEY;


public class SearchActivityPresenter {

    private SearchActivity searchActivity;

    public SearchActivityPresenter(SearchActivity mainActivity) {
        this.searchActivity = mainActivity;
    }

    public void loadData(String location) {
        getAccuWeatherAutoComplete(location);
    }


    @SuppressLint("CheckResult")
    public void getAccuWeatherAutoComplete(String query) {
        RetrofitAccuWeather retrofitAccuWeather = RetrofitAccuWeather.getInstance();
        AccuWeather accuWeather = retrofitAccuWeather.getApiService();
        Map<String, String> accuweatherParams = new HashMap<>();
        accuweatherParams.put("q", query);
        accuweatherParams.put("apikey", ACCUWEATHER_API_KEY);
        accuWeather.getCities("locations/v1/cities/autocomplete", accuweatherParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultObserver<List<AccuWeatherAutocomplete>>() {
                    @SuppressLint("StringFormatMatches")
                    @Override
                    public void onNext(List<AccuWeatherAutocomplete> accuWeatherAutocompletes) {
                        List<AccuWeatherAutocomplete> list = accuWeatherAutocompletes;
                        String[] cities = new String[list.size()];
                        String[] citiesId = new String[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            cities[i] = list.get(i).getLocalizedName();
                            citiesId[i] = list.get(i).getKey();
                        }
                        searchActivity.setCities(cities);
                        searchActivity.setCitiesId(citiesId);
                        searchActivity.showAutoComleteList(cities);
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
