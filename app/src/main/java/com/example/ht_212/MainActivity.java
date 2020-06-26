package com.example.ht_212;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonOK;
    Spinner spinnerCountries;
    Spinner spinnerCities;
    Spinner spinnerHouseNumbers;
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        buttonOK = findViewById(R.id.buttonOK);
        spinnerCountries = findViewById(R.id.spinnerCountries);
        spinnerCities = findViewById(R.id.spinnerCities);
        spinnerHouseNumbers = findViewById(R.id.spinnerHouseNumbers);
        /*spinnerCountries.setPrompt("Выберите страну");
        spinnerCities.setPrompt("Выберите город");
        spinnerHouseNumbers.setPrompt("Выберите номер дома");*/
        initNumberSpinner();
        initCountriesSpinner();
        Log.i(TAG, "init done");
    }

    public void initNumberSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i = 1; i < 51; i++) {
            houseNumbers[i - 1] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, houseNumbers);
        spinnerHouseNumbers.setAdapter(adapter);
        Log.i(TAG, "initNumberSpinner done");
    }

    public void initCountriesSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountries.setAdapter(adapter);
        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initCitiesSpinner(countries[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Log.i(TAG, "initCountriesSpinner done");
    }

    public void initCitiesSpinner(String country) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "Россия":
                adapter = ArrayAdapter.createFromResource(this, R.array.russia, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter = ArrayAdapter.createFromResource(this, R.array.ukraine, android.R.layout.simple_spinner_item);
                break;
            case "Беларусь":
                adapter = ArrayAdapter.createFromResource(this, R.array.belarus, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) spinnerCities.setAdapter(adapter);
        Log.i(TAG, "initCitiesSpinner done");
    }

    public void setButtonOK(View view) {
        Log.i(TAG, "button clicked");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(spinnerCountries.getSelectedItem().toString()).append(", ").append(spinnerCities.getSelectedItem().toString()).append(", ").append(spinnerHouseNumbers.getSelectedItem().toString());
        Toast.makeText(this, stringBuilder, Toast.LENGTH_LONG).show();
        Log.i(TAG, "setButtonOK done");
    }
}