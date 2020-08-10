package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AddingMeasurementActivity extends AppCompatActivity {

    ArrayList<EditText> editTextArrayList;
    EditText fatEditText;
    Button saveButton;
    Button calculateButton;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_measurement);

        editTextArrayList = new ArrayList<>();
        initEditTexts();
        addListeners();
    }

    void addListeners(){

        //Co sie dzieje po wcisnieciu save button
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Przepisanie listy EditText na String
                List<String> result = editTextArrayList.stream()
                        .map( x -> x.getText().toString())
                        .collect(Collectors.toList());

                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                Measurement measurement = new Measurement(result, currentDate, fatEditText.getText().toString());
                measurement.save();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }});

        ///Co sie dzieje po wcisnieciu oblicz
        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //funkcja licząca i wpisujaca tluszcz

                saveButton.setEnabled(true);
            }});

        ///Reakcja na zmiane tekstu
        TextWatcher textListener = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ///Jezeli wszystkie pola sa wpisane to przycisk zostanie wlaczony
                Boolean isFieldsEmpty = true;
                for(EditText et : editTextArrayList){

                    isFieldsEmpty &= !et.getText().toString().isEmpty();
                }

                calculateButton.setEnabled(isFieldsEmpty);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        ///Przypisanie watchera do wszstkich pól tekstowych
        for(EditText et : editTextArrayList){

            et.addTextChangedListener(textListener);
        }
    }

    private void initEditTexts(){

        editTextArrayList.add((EditText) findViewById(R.id.szyjaEdit));
        editTextArrayList.add((EditText) findViewById(R.id.klatkaEdit));
        editTextArrayList.add((EditText) findViewById(R.id.bicepsLewyEdit));
        editTextArrayList.add((EditText) findViewById(R.id.bicepsLewyNapietyEdit));
        editTextArrayList.add((EditText) findViewById(R.id.bicepsPrawyEdit));
        editTextArrayList.add((EditText) findViewById(R.id.bicepsPrawyNapietyEdit));
        editTextArrayList.add((EditText) findViewById(R.id.przedramieLeweEdit));
        editTextArrayList.add((EditText) findViewById(R.id.przedramiePraweEdit));
        editTextArrayList.add((EditText) findViewById(R.id.taliaEdit));
        editTextArrayList.add((EditText) findViewById(R.id.brzuchEdit));
        editTextArrayList.add((EditText) findViewById(R.id.biodraEdit));
        editTextArrayList.add((EditText) findViewById(R.id.udoLeweEdit));
        editTextArrayList.add((EditText) findViewById(R.id.udoPraweEdit));
        editTextArrayList.add((EditText) findViewById(R.id.lydkaLewaEdit));
        editTextArrayList.add((EditText) findViewById(R.id.lydkaPrawaEdit));

        fatEditText = findViewById(R.id.tkankaTluszczowaEdit);
        fatEditText.setEnabled(false);
        //editTextArrayList.add((EditText) findViewById(R.id.tkankaTluszczowaEdit));

        saveButton = findViewById(R.id.add);
        calculateButton = findViewById(R.id.calculate);

        calculateButton.setEnabled(false);
        saveButton.setEnabled(false);
    }

    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, AddingMeasurementActivity.class);
        //some put extras if needed
        return intent;
    }



}
