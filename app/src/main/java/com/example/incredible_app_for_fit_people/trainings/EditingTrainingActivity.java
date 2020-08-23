package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Exercise;
import com.example.incredible_app_for_fit_people.database.Training;

import java.util.ArrayList;
import java.util.List;

public class EditingTrainingActivity extends AppCompatActivity {

    Long data_id;

    LayoutInflater layoutInflater;
    List<View> myView;
    LinearLayout llParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_training);

        myView = new ArrayList<>();
        llParent = findViewById(R.id.exercise_ll);
        addValuesFromDB();
    }



    private void addValuesFromDB(){

        Intent intent = getIntent();
        data_id = intent.getLongExtra("id", 4);

        Training traning = Training.load(Training.class, data_id);

        List<Exercise> exercises = traning.exercises();

        for(int i = 0; i < exercises.size(); i++){

            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.single_exercise, null, false);
            myView.add(view);
            llParent.addView(view);
        }

        for(int i=0; i < llParent.getChildCount(); i++) {

            EditText cwiczenie = llParent.getChildAt(i).findViewById(R.id.cwiczenieEdit);
            EditText ciezar = llParent.getChildAt(i).findViewById(R.id.ciezarEdit);
            EditText serie = llParent.getChildAt(i).findViewById(R.id.serieEdit);
            EditText powtorzenia = llParent.getChildAt(i).findViewById(R.id.powtorzeniaEdit);

            cwiczenie.setText(exercises.get(i).getCwiczenie());
            ciezar.setText(exercises.get(i).getObciazenie());
            serie.setText(exercises.get(i).getSerie());
            powtorzenia.setText(exercises.get(i).getPowtorzenia());
        }


    }
}