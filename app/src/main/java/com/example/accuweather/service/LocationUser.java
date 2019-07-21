package com.example.accuweather.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.accuweather.ui.screen.ForecastActivity;
import com.example.accuweather.ui.screen.presenter.ForecastPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class LocationUser {

    ForecastActivity context;

    private ForecastPresenter presenter;

    public LocationUser(ForecastActivity context) {
        this.context = context;
    }

    @SuppressLint("CheckResult")
    public void getLocation() {
        //RXPermissions  location
        RxPermissions rxPermissions = new RxPermissions(context);
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            // All requested permissions

                            getCurPosition();

                        } else {
                            // Permission is denied
                            Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show();

                        }
                    }
                });
        //RXPermissions location
    }


    public void getCurPosition() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        android.location.Location lastKnownLocationNetWork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (lastKnownLocationNetWork != null) {
            float latitude = (float) lastKnownLocationNetWork.getLatitude();
            float longitude = (float) lastKnownLocationNetWork.getLongitude();
            Toast.makeText(context, "Location detected!", Toast.LENGTH_LONG).show();
            presenter = new ForecastPresenter(context);
            presenter.getAccuWeatherCityId(latitude, longitude, context);
        } else {
            Toast.makeText(context, "Location Definition Error!", Toast.LENGTH_LONG).show();
        }
    }

}
