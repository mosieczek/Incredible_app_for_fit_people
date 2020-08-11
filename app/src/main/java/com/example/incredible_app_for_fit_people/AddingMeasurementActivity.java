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

                Boolean jestKobieta = true;
                //funkcja licząca i wpisujaca tluszcz
                Double talia = Double.valueOf( editTextArrayList.get(8).getText().toString() );
                Double waga = Double.valueOf( editTextArrayList.get(15).getText().toString() );

                Double a = 4.15d * talia;
                Double b = a / 2.54d;
                Double c = 0.082d * waga * 2.2d;
                Double d = 0d;
                Double e = waga * 2.2;
                if( jestKobieta ){

                    d = b - c - 76.76d;
                } else {

                    d = b - c - 98.42d;
                }

                Double wynik = d/e * 100d;

                fatEditText.setText( wynik.toString() );
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

        editTextArrayList.add((EditText) findViewById(R.id.szyjaEdit));                 //0
        editTextArrayList.add((EditText) findViewById(R.id.klatkaEdit));                //1
        editTextArrayList.add((EditText) findViewById(R.id.bicepsLewyEdit));            //2
        editTextArrayList.add((EditText) findViewById(R.id.bicepsLewyNapietyEdit));     //3
        editTextArrayList.add((EditText) findViewById(R.id.bicepsPrawyEdit));           //4
        editTextArrayList.add((EditText) findViewById(R.id.bicepsPrawyNapietyEdit));    //5
        editTextArrayList.add((EditText) findViewById(R.id.przedramieLeweEdit));        //6
        editTextArrayList.add((EditText) findViewById(R.id.przedramiePraweEdit));       //7
        editTextArrayList.add((EditText) findViewById(R.id.taliaEdit));                 //8
        editTextArrayList.add((EditText) findViewById(R.id.brzuchEdit));                //9
        editTextArrayList.add((EditText) findViewById(R.id.biodraEdit));                //10
        editTextArrayList.add((EditText) findViewById(R.id.udoLeweEdit));               //11
        editTextArrayList.add((EditText) findViewById(R.id.udoPraweEdit));              //12
        editTextArrayList.add((EditText) findViewById(R.id.lydkaLewaEdit));             //13
        editTextArrayList.add((EditText) findViewById(R.id.lydkaPrawaEdit));            //14
        editTextArrayList.add((EditText) findViewById(R.id.wagaEdit));                  //15

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
