package com.example.incredible_app_for_fit_people;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstancesState) {


        super.onCreate(savedInstancesState);

        addPreferencesFromResource(R.xml.root_preferences);
    }
}
