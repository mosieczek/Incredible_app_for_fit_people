package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Exercise;
import com.example.incredible_app_for_fit_people.database.Series;
import com.example.incredible_app_for_fit_people.database.Training;

import java.util.ArrayList;
import java.util.List;

import static com.example.incredible_app_for_fit_people.trainings.TraningMainActivity.addRemoveLinearLayout;
import static com.example.incredible_app_for_fit_people.trainings.TraningMainActivity.addRemoveTableLayout;
import static java.lang.String.valueOf;

public class EditingTrainingActivity extends AppCompatActivity {

    Long data_id;

    LayoutInflater layoutInflater;
    List<View> myView;
    LinearLayout llParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_training);

        llParent = findViewById(R.id.exercise_ll);
        addValuesFromDB();
    }



    private void addValuesFromDB(){

        Intent intent = getIntent();
        data_id = intent.getLongExtra("id", 4);

        ///Pobieramy cwiczenai
        Training traning = Training.load(Training.class, data_id);
        List<Exercise> exercises = traning.exercises();

        for( int i = 0; i < exercises.size(); i++){

            //Dodajemy kolejne cwiczenie do listy
            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.exercise_layout, null, false);

            addRemoveLinearLayout(view, llParent);

            EditText cwiczenieEdit = view.findViewById(R.id.cwiczenieEdit);
            cwiczenieEdit.setText(exercises.get(i).getCwiczenie());

            TableLayout tlParent = view.findViewById(R.id.table_layout);

            List<Series> series = exercises.get(i).sets();

            for( int j = 0; j < series.size(); j++){

                layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View serieView = layoutInflater.inflate(R.layout.single_set, null, false);
                addRemoveTableLayout(serieView, tlParent);

                EditText seria = serieView.findViewById(R.id.serieEdit);
                EditText ciezar = serieView.findViewById(R.id.ciezarEdit);
                EditText powtorzenia = serieView.findViewById(R.id.powtorzeniaEdit);

                seria.setText( series.get(j).getSerie() );
                ciezar.setText( series.get(j).getObciazenie() );
                powtorzenia.setText( series.get(j).getPowtorzenia() );


                tlParent.addView(serieView);
            }

            llParent.addView(view);

        }
    }


}