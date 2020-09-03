package com.example.incredible_app_for_fit_people.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.measurements.MeasurementsMainActivity;
import com.example.incredible_app_for_fit_people.trainings.TraningMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SettingsActivity extends AppCompatActivity {

    private ListPreference mListPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.setting);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getFragmentManager().beginTransaction().add(R.id.setting_layout, new SettingsFragment() ).commit();
    }

//    public static class SettingsFragment extends PreferenceFragmentCompat {
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//            setPreferencesFromResource(R.xml.root_preferences, rootKey);
//        }
//    }

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
                            startActivity(intent2);
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.setting:
                            return true;
                    }

                    return true;
                }
            };
}