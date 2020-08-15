package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AddingMeasurementChoiseActivity extends AppCompatActivity {

    TextView dataTextView;
    Button addMeasurementsButton;
    LinearLayout mainLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_measurement_choise);

        addData();
        initEditTexts();
        addListeners();
    }

    private void addData(){

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        dataTextView = findViewById(R.id.dataView);
        dataTextView.setText( currentDate );
    }

    private void addListeners(){

        addMeasurementsButton = findViewById(R.id.kolejnePomiary);
        addMeasurementsButton.setOnClickListener( view -> {

            mainLL = findViewById(R.id.next);
            Button button = new Button(this);
            button.setText("elo");


            LinearLayout fullSample = findViewById(R.id.full_sample); ///tutaj jest jakiś błąd :/ :? 
            mainLL.addView( fullSample );

        });
    }

    private void initEditTexts(){


    }

    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, AddingMeasurementChoiseActivity.class);
        //some put extras if needed
        return intent;
    }



}
