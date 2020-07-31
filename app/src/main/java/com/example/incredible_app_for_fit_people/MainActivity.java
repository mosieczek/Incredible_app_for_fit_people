package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;
    TextView dbTextView;
    EditText dbEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbTextView = (TextView) findViewById(R.id.db_text_view);
//        dbEditText = (EditText) findViewById(R.id.db_edit_text);

    }

}
