package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class AddingMeasurementActivity extends AppCompatActivity {


    EditText szyja;
    EditText klatkaPiersiowa;
    EditText bicepsLewy;
    EditText bicepsLewyNapiety;
    EditText bicepsPrawy;
    EditText bicepsPrawyNapiety;
    EditText przedramieLewe;
    EditText przedramiePrawe;
    EditText talia;
    EditText brzuch;
    EditText biodra;
    EditText udoLewe;
    EditText udoPrawe;
    EditText lydkaLewa;
    EditText lydkaPrawa;
    EditText tkankaTluszczowa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_measurement);

        szyja = findViewById(R.id.szyjaEdit);
        klatkaPiersiowa = findViewById(R.id.klatkaEdit);


    }



    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, AddingMeasurementActivity.class);
        //some put extras if needed
        return intent;
    }
}
