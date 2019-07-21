package com.example.accuweather.ui.screen;

import com.example.accuweather.data.model.accuweather.forecast.DailyForecast;
import com.example.accuweather.data.model.openweathermap.ForecastItem;

import java.util.List;

public interface ForecastListView {

    //MVP
    void showDaysForecast(List<DailyForecast> weatherDayList);

    void showHoursForecast(List<ForecastItem> weatherHourList);

}
