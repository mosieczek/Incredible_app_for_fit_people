package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.measurements.AddingMeasurementChoiseActivity;
import com.example.incredible_app_for_fit_people.measurements.MeasurementsMainActivity;

public class AddingTrainingActivity extends AppCompatActivity {


    LinearLayout llParent;
    LayoutInflater layoutInflater;
    View myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_training);

        llParent = findViewById(R.id.exercise_ll);
    }


    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, AddingTrainingActivity.class);
        //some put extras if needed
        return intent;
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
                myView = layoutInflater.inflate(R.layout.single_exercise, null, false);
                llParent.addView(myView);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

