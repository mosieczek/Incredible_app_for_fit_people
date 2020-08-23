package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Exercise;
import com.example.incredible_app_for_fit_people.database.Training;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddingTrainingActivity extends AppCompatActivity {


    LinearLayout llParent;
    LayoutInflater layoutInflater;
    List<View> myView;
    Button add_traning_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_training);

        llParent = findViewById(R.id.exercise_ll);

        myView = new ArrayList<>();

        initListeners();
    }


    ///////////TOOLBAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { ///Dodajemy podstawowe menu do toolbara
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_1:

                layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.single_exercise, null, false);
                myView.add(view);
                llParent.addView(view);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initListeners(){

        add_traning_btn = findViewById(R.id.add_traning_btn);
        add_traning_btn.setOnClickListener( view -> {

            String date =  new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            Training traning = new Training(date, "silowy");
            traning.save();



            for(int i=0; i < llParent.getChildCount(); i++) {

                EditText cwiczenie = llParent.getChildAt(i).findViewById(R.id.cwiczenieEdit);
                EditText ciezar = llParent.getChildAt(i).findViewById(R.id.ciezarEdit);
                EditText serie = llParent.getChildAt(i).findViewById(R.id.serieEdit);
                EditText powtorzenia = llParent.getChildAt(i).findViewById(R.id.powtorzeniaEdit);

                Exercise item = new Exercise(traning, cwiczenie.getText().toString(), ciezar.getText().toString(), serie.getText().toString(), powtorzenia.getText().toString());
                item.save();

            }



            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });


    }
}

