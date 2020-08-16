package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
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
    EditText wagaEdit;
    Button addMeasurementsButton;
    Button addWeight;
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
        addWeight = findViewById(R.id.addWeight);


        addMeasurementsButton.setOnClickListener( view -> {

            mainLL = findViewById(R.id.full_sample_main);
            mainLL.setVisibility(View.VISIBLE);
            addMeasurementsButton.setVisibility(View.GONE);
            addWeight.setVisibility(View.GONE);
        });


        addWeight.setOnClickListener( view -> {

            wagaEdit = findViewById(R.id.wagaEdit);
            String weigth = wagaEdit.getText().toString();
            String currentDate = dataTextView.getText().toString();

            Measurement measurement = new Measurement(weigth,currentDate);
            measurement.save();

            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
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
