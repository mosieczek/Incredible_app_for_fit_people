package com.example.incredible_app_for_fit_people;

import android.os.Bundle;
import android.preference.ListPreference;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;



public class SettingsActivity extends AppCompatActivity {

    private ListPreference mListPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

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
}