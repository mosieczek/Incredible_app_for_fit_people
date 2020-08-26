package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Cardio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddingCardioActivity extends AppCompatActivity {

    EditText time;
    EditText activity;
    Button addActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_cardio);

        initEditTexts();
        addListeners();

    }

    private void addListeners() {

        addActivity = findViewById(R.id.adding_aktywnosc);
        addActivity.setOnClickListener( view -> {

            String timeS = time.getText().toString();
            String activityS = activity.getText().toString();
            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

            Cardio cardio = new Cardio(date, "kardio", Long.valueOf(timeS), activityS);
            cardio.save();

            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();

        });
    }

    private void initEditTexts() {

        time = findViewById(R.id.czasEdit);
        activity = findViewById(R.id.aktywnoscEdit);
    }




}