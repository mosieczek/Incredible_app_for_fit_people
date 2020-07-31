package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AddingMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_measurement);
    }

    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, AddingMeasurementActivity.class);
        //some put extras if needed
        return intent;
    }
}
