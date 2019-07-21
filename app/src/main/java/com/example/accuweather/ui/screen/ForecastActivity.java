package com.example.accuweather.ui.screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accuweather.R;
import com.example.accuweather.Utils;
import com.example.accuweather.data.model.accuweather.forecast.DailyForecast;
import com.example.accuweather.data.model.openweathermap.ForecastItem;
import com.example.accuweather.service.LocationUser;
import com.example.accuweather.ui.adapter.DayPrognosisAdapter;
import com.example.accuweather.ui.adapter.HourPrognosisAdapter;
import com.example.accuweather.ui.screen.presenter.ForecastPresenter;

import java.util.List;

import static com.example.accuweather.Utils.DEFAULT_CITY;

public class ForecastActivity extends AppCompatActivity implements ForecastListView {

    private TextView textViewTitle;
    private TextView textViewDate;
    private ImageView imageViewIcon;
    private TextView textViewTemperature;
    private TextView textViewHumidity;
    private TextView textViewWindSpeed;
    private ImageView imageViewWindDeg;

    private RecyclerView recyclerViewDays;
    private RecyclerView recyclerViewHours;

    private HourPrognosisAdapter hourPrognosisAdapter;
    private DayPrognosisAdapter dayPrognosisAdapter;

    private List<ForecastItem> weatherHourList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ForecastPresenter forecastPresenter = new ForecastPresenter(this);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDate = findViewById(R.id.textViewDate);
        imageViewIcon = findViewById(R.id.imageViewIcon);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewHumidity = findViewById(R.id.textViewHumidity);
        textViewWindSpeed = findViewById(R.id.textViewWindSpeed);
        imageViewWindDeg = findViewById(R.id.imageViewWindDeg);

        recyclerViewHours = findViewById(R.id.hours_recycler);
        recyclerViewHours.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewDays = findViewById(R.id.five_days_recycler);
        recyclerViewDays.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        hourPrognosisAdapter = new HourPrognosisAdapter();
        dayPrognosisAdapter = new DayPrognosisAdapter(this);


        forecastPresenter.loadData();

    }

    @Override
    public void showDaysForecast(final List<DailyForecast> weatherDayList) {
        setMainDisplay(weatherDayList, 0);
        dayPrognosisAdapter.setList(weatherDayList);
        recyclerViewDays.setAdapter(dayPrognosisAdapter);
        dayPrognosisAdapter.setOnDayClickListener(new DayPrognosisAdapter.OnDayClickListener() {
            @Override
            public void onDayClick(int position) {
                dayPrognosisAdapter.setItemSelected(position);
                dayPrognosisAdapter.notifyDataSetChanged();
                setMainDisplay(weatherDayList, position);
                showHoursForecast(weatherHourList);
            }
        });
    }

    @Override
    public void showHoursForecast(List<ForecastItem> weatherHourList) {
        this.weatherHourList = weatherHourList;
        //toolbar set City
        textViewTitle.setText(Utils.getCurCity(this));
        //toolbar set City
        //Get item selected position
        int position = dayPrognosisAdapter.getItemSelected();

        List<ForecastItem> list = Utils.createHourListByDay(weatherHourList, position);
        hourPrognosisAdapter.setList(list);
        recyclerViewHours.setAdapter(hourPrognosisAdapter);
        hourPrognosisAdapter.notifyDataSetChanged();

        //main display
        textViewHumidity.setText(list.get(4).getMain().getHumidity() + "%   ");
        textViewWindSpeed.setText(list.get(4).getWind().getSpeed().toString() + " m/s");
        int deg = Math.round(list.get(4).getWind().getDeg());
        imageViewWindDeg.setImageResource(Utils.getIconWind(deg));
        //main display
    }

    //Set main screen forecast
    public void setMainDisplay(List<DailyForecast> weatherDayList, int position) {
        //main display
        DailyForecast listD = weatherDayList.get(position);
        textViewDate.setText(Utils.getStrDay(listD.getEpochDate()));
        imageViewIcon.setImageResource(Utils.getIconIdToDrawableRes(listD.getDay().getIcon()));
        textViewTemperature.setText(Utils.getTemperatureStr(listD.getTemperature().getMaximum().getValue(),listD.getTemperature().getMinimum().getValue()));
        //main display
    }

    //Button open search
    public void openSearch(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    //Button get current position forecast
    public void detectLocation(View view) {
        LocationUser locationUser = new LocationUser(this);
        locationUser.getLocation();
    }

}
