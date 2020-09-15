package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Cardio;
import com.example.incredible_app_for_fit_people.database.Training;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddingCardioActivity extends AppCompatActivity {

    private EditText time;
    private EditText activity;
    private Button addActivity;
    private long dataBaseID;
    private boolean isEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_cardio);

        initEditTexts();
        addListeners();

        ///Checking if it's editing or adding cardio
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            dataBaseID = extras.getLong("id");
            fetchFromDB();
            isEditing = true;
        } else {
            isEditing = false;
        }



    }

    private void addListeners() {

        addActivity = findViewById(R.id.adding_aktywnosc);
        addActivity.setOnClickListener( view -> {

            String timeS = time.getText().toString();
            String activityS = activity.getText().toString();
            if (!isEditing) {

                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                Cardio cardio = new Cardio(date, activityS, timeS);
                cardio.save();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {

                Cardio cardio = Cardio.load(Cardio.class, dataBaseID);
                cardio.setTime(timeS);
                cardio.save();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void initEditTexts() {

        time = findViewById(R.id.czasEdit);
        activity = findViewById(R.id.aktywnoscEdit);
    }

    private void fetchFromDB(){

        Cardio cardio = Cardio.load(Cardio.class, dataBaseID);
        activity.setText(cardio.getTitle());
        time.setText(cardio.getTime());
    }
}