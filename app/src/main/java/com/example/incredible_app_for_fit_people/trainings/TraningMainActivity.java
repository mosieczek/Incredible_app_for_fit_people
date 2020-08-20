package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.incredible_app_for_fit_people.R;

public class TraningMainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADDING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_main);


    }


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
                Intent intent = AddingTrainingActivity.newIntent(TraningMainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_ADDING);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}