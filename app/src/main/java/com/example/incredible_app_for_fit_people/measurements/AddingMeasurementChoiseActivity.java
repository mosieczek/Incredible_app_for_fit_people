package com.example.incredible_app_for_fit_people.measurements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Measurement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AddingMeasurementChoiseActivity extends AppCompatActivity {

    TextView dataTextView;

    EditText wagaEdit;
    EditText fatEditText;

    Button addMeasurementsButton;
    Button addWeight;
    Button saveButton;
    Button calculateButton;
    LinearLayout mainLL;
    ScrollView mMainSv;
    ArrayList<EditText> editTextArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_measurement_choise);

        addData();
        //initEditTexts();
        addListeners();


    }

    private void addData(){

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        dataTextView = findViewById(R.id.dataView);
        dataTextView.setText( currentDate );
    }

    private void addListeners(){

        addMeasurementsButton = findViewById(R.id.kolejnePomiary);
        addWeight = findViewById(R.id.addWeight);


        addMeasurementsButton.setOnClickListener( view -> {

            mainLL = findViewById(R.id.full_sample_main);
            mainLL.setVisibility(View.VISIBLE);
            addMeasurementsButton.setVisibility(View.GONE);
            addWeight.setVisibility(View.GONE);


            mMainSv = findViewById(R.id.main_scroll_v);
            mMainSv.setBackgroundResource(R.drawable.chlop);
            mMainSv.setAlpha(0.5F);
            mainLL.setAlpha(1);

            initEditTexts();
            addFullListeners();

        });


        addWeight.setOnClickListener( view -> {

            wagaEdit = findViewById(R.id.wagaEdit);
            String weigth = wagaEdit.getText().toString();
            //String currentDate = dataTextView.getText().toString();
            Date date = new Date();

            String fat = "Not calculated";
            Measurement measurement = new Measurement(date.toString(), weigth, fat);
            measurement.save();

            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }


    private void addFullListeners(){

        saveButton = findViewById(R.id.add);
        calculateButton = findViewById(R.id.calculate);

        calculateButton.setEnabled(false);


        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Przepisanie listy EditText na String
                List<String> result = editTextArrayList.stream()
                        .map( x -> x.getText().toString())
                        .collect(Collectors.toList());

                Date date = new Date();

                Measurement measurement = new Measurement(result, date.toString(), fatEditText.getText().toString());
                measurement.save();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }});

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext() /* Activity context */);
                String name = sharedPreferences.getString("gender", "");

                Boolean jestKobieta = name.equals("1") ?  false : true;

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

            }});

        ///Reakcja na zmiane tekstu
        TextWatcher textListener = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ///Jezeli wszystkie pola sa wpisane to przycisk zostanie wlaczony
                  Boolean isFieldsEmpty = true;
//                for(EditText et : editTextArrayList){
//
//                    isFieldsEmpty &= !et.getText().toString().isEmpty();
//                }

                isFieldsEmpty &= !editTextArrayList.get(8).getText().toString().isEmpty();
                isFieldsEmpty &= !editTextArrayList.get(15).getText().toString().isEmpty();
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

        editTextArrayList = new ArrayList<>();

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

    }

    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, AddingMeasurementChoiseActivity.class);
        //some put extras if needed
        return intent;
    }



}
