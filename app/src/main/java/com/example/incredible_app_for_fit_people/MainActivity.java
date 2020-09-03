package com.example.incredible_app_for_fit_people;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.example.incredible_app_for_fit_people.measurements.MeasurementsMainActivity;
import com.example.incredible_app_for_fit_people.settings.SettingsActivity;
import com.example.incredible_app_for_fit_people.trainings.AddingCardioActivity;
import com.example.incredible_app_for_fit_people.trainings.AddingTrainingActivity;
import com.example.incredible_app_for_fit_people.trainings.TraningMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    TextView dbTextView;
    EditText dbEditText;
    ListView lv;
    SimpleCursorAdapter dbAdapter;
    Button pomiaryB, treningiB, ustawieniaB;


    private static final int REQUEST_CODE_ADDING = 0;
    private static final int REQUEST_CODE_EDITING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.trenings);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) { ///Dodajemy podstawowe menu do toolbara
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.trenings:
                Intent intent = new Intent(getApplicationContext(), TraningMainActivity.class);
                startActivity(intent);
                return true;

            case R.id.measurements:
                Intent intent2 = new Intent(getApplicationContext(), MeasurementsMainActivity.class);
                startActivityForResult(intent2, REQUEST_CODE_ADDING);
                return true;

            case R.id.setting:
                Intent intent3 = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent3);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.trenings:
                            Intent intent = new Intent(getApplicationContext(), TraningMainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.measurements:
                            Intent intent2 = new Intent(getApplicationContext(), MeasurementsMainActivity.class);
                            startActivityForResult(intent2, REQUEST_CODE_ADDING);
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.setting:
                            Intent intent3 = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent3);
                            overridePendingTransition(0,0);
                            return true;
                    }

                    return true;
                }
            };

}
