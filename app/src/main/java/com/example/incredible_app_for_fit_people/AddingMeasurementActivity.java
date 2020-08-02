package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_measurement);

        szyja = findViewById(R.id.szyjaEdit);
        klatkaPiersiowa = findViewById(R.id.klatkaEdit);

        saveButton = findViewById(R.id.button);

        addListeners();
    }

    void addListeners(){

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Measurement measurement = new Measurement(szyja.getText().toString(),
                        klatkaPiersiowa.getText().toString());

                measurement.save();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }});
        }



    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, AddingMeasurementActivity.class);
        //some put extras if needed
        return intent;
    }
}
