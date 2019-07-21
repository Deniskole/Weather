package com.example.accuweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;

import com.example.accuweather.data.model.openweathermap.ForecastItem;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String OPENWEATHER_API_KEY = "343da57b69713e7543d1a7695b2d6e7d";
    public static final String ACCUWEATHER_API_KEY = "9cHaG7OkmjFzDAE67G0MAN0g4iQ6UsZi";

    public static final String DEFAULT_CITY_ID = "323903";
    public static final String DEFAULT_CITY = "Zaporizhzhia";


    //Get temperature format "13°/12"
    public static String getTemperatureStr(float max, float min) {
        String tMax = String.valueOf(Math.round(max));
        String tMin = String.valueOf(Math.round(min));
        return tMax + "°/" + tMin + "°";
    }


    public static String getCurCity(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("city", DEFAULT_CITY);
    }

    public static String getCurCityId(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("cityId", DEFAULT_CITY_ID);
    }


    public static void saveCurPosition(Context context, String city, String cityId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("city", city).apply();
        preferences.edit().putString("cityId", cityId).apply();
    }


    //OpenWeather Query have 40 objects it's 5 days info every 3 hours
    // but we need just 8 object for forecast
    public static List<ForecastItem> createHourListByDay(List<ForecastItem> weatherHourList, int position) {
        List<ForecastItem> hourList = new ArrayList<>();
        hourList.clear();
        switch (position) {
            case 0:
                for (int i = 0; i <= 7; i++) {
                    hourList.add(weatherHourList.get(i));
                }
                break;
            case 1:
                for (int i = 8; i <= 15; i++) {
                    hourList.add(weatherHourList.get(i));
                }
                break;
            case 2:
                for (int i = 16; i <= 23; i++) {
                    hourList.add(weatherHourList.get(i));
                }
                break;
            case 3:
                for (int i = 24; i <= 31; i++) {
                    hourList.add(weatherHourList.get(i));
                }
                break;
            case 4:
                for (int i = 32; i <= 39; i++) {
                    hourList.add(weatherHourList.get(i));
                }
                break;
        }
        return hourList;
    }

    //Date milliseconds return string format "EEE"
    public static String getJustStrDay(long dateInMilliseconds) {
        return DateFormat.format("EEE", dateInMilliseconds * 1000).toString();
    }

    //Date milliseconds return string format "HH"
    public static String getStrHour(long dateInMilliseconds) {
        return DateFormat.format("HH", dateInMilliseconds * 1000).toString();
    }

    //Date milliseconds return string format "EEE, dd"
    public static String getStrDay(long dateInMilliseconds) {
        return DateFormat.format("EEE, dd", dateInMilliseconds * 1000).toString();
    }

    //Query returns the number is the number of the required picture
    public static int getIconWind(int windDegrees) {
        if (windDegrees > 0 && windDegrees <= 23) return R.drawable.icon_wind_n;
        else if (windDegrees > 22 && windDegrees <= 68) return R.drawable.icon_wind_ne;
        else if (windDegrees > 68 && windDegrees <= 113) return R.drawable.icon_wind_e;
        else if (windDegrees > 113 && windDegrees <= 158) return R.drawable.icon_wind_se;
        else if (windDegrees > 158 && windDegrees <= 203) return R.drawable.icon_wind_s;
        else if (windDegrees > 203 && windDegrees <= 248) return R.drawable.icon_wind_ws;
        else if (windDegrees > 248 && windDegrees <= 293) return R.drawable.icon_wind_w;
        else if (windDegrees > 293 && windDegrees <= 338) return R.drawable.icon_wind_wn;
        return R.drawable.icon_wind_n;
    }

    //Query returns the number is the number of the required picture
    public static int getIconIdToDrawableRes(int icon) {
        if (icon < 2) return R.drawable.ic_white_day_bright;
        if (icon < 12) return R.drawable.ic_white_day_cloudy;
        if (icon < 15) return R.drawable.ic_white_day_rain;
        if (icon < 18) return R.drawable.ic_white_day_thunder;
        if (icon == 18) return R.drawable.ic_white_day_shower;
        return R.drawable.ic_white_day_cloudy;
    }

    //Query returns the number is the number of the required picture
    public static int getIconName(String iconId) {
        switch (iconId) {
            case "01d":
                return R.drawable.ic_white_day_bright;
            case "02d":
            case "03d":
            case "04d":
            case "50d":
                return R.drawable.ic_white_day_cloudy;
            case "09d":
            case "10d":
            case "13d":
                return R.drawable.ic_white_day_shower;
            case "11d":
                return R.drawable.ic_white_day_thunder;
            case "01n":
                return R.drawable.ic_white_night_bright;
            case "02n":
            case "03n":
            case "04n":
            case "50n":
                return R.drawable.ic_white_night_cloudy;
            case "09n":
            case "10n":
            case "13n":
                return R.drawable.ic_white_night_shower;
            case "11n":
                return R.drawable.ic_white_night_thunder;
            default:
                return R.drawable.ic_white_day_bright;
        }
    }
}