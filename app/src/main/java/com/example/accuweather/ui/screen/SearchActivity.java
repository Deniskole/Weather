package com.example.accuweather.ui.screen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.accuweather.R;
import com.example.accuweather.Utils;
import com.example.accuweather.ui.screen.presenter.SearchActivityPresenter;

public class SearchActivity extends AppCompatActivity {

    private RelativeLayout main_layout;
    private LinearLayout search_layout;
    private AutoCompleteTextView autoCompleteTextView;
    private AlertDialog.Builder alertDialog;

    private String title = "Save location changes?";
    private String button1String = "Yes";
    private String button2String = "No";

    private SearchActivityPresenter searchActivityPresenter;

    private String[] cities;
    private String[] citiesId;

    private String city;
    private String cityId;


    public void setCitiesId(String[] citiesId) {
        this.citiesId = citiesId;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        main_layout = findViewById(R.id.relativeLayout);
        search_layout = findViewById(R.id.linearLayout);
        main_layout.setVisibility(View.GONE);
        search_layout.setVisibility(View.VISIBLE);

        autoCompleteTextView = findViewById(R.id.AutoCompleteTextView);
        searchActivityPresenter = new SearchActivityPresenter(this);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    searchActivityPresenter.loadData(autoCompleteTextView.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Listener selected city
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get selected city
                city = cities[position];
                //Get selected cityId
                cityId = citiesId[position];
                //Alert Dialog
                alertDialog = new AlertDialog.Builder(SearchActivity.this);
                alertDialog.setTitle(title);
                alertDialog.setMessage(city);
                alertDialog.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Utils.saveCurPosition(SearchActivity.this, city, cityId);
                        Toast.makeText(getApplicationContext(), city + " Saved...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ForecastActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(getApplicationContext(), "Cancel...", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setCancelable(true);
                //Show alert dialog
                alertDialog.show();
            }
        });
    }


    //Set cities on autocomlete list
    public void showAutoComleteList(String[] cities) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    //Button back
    public void backImageView(View view) {
        Intent intent = new Intent(this, ForecastActivity.class);
        startActivity(intent);
        finish();
    }

}
