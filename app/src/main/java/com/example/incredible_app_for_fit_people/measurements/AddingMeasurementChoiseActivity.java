package com.example.incredible_app_for_fit_people.measurements;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    long dataBaseID;
    private boolean isEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_measurement_choise);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            dataBaseID = extras.getLong("id");
            isEditing = true;

            addData();
            initEditTexts();
            addFullListeners();
            fetchFromDB();
        } else {
            isEditing = false;

            addData();
            addListeners();
        }

    }

    private void fetchFromDB() {


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

            initEditTexts();
            addFullListeners();

        });


        addWeight.setOnClickListener( view -> {

            wagaEdit = findViewById(R.id.wagaEdit);
            String weigth = wagaEdit.getText().toString();

            String fat = "Not calculated";
            Measurement measurement = new Measurement(getAndFormatDate(), weigth, fat);
            measurement.save();

            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }


    private void addFullListeners(){

        if(isEditing){

            addMeasurementsButton = findViewById(R.id.kolejnePomiary);
            addWeight = findViewById(R.id.addWeight);
        }

        mainLL = findViewById(R.id.full_sample_main);
        mainLL.setVisibility(View.VISIBLE);
        addMeasurementsButton.setVisibility(View.GONE);
        addWeight.setVisibility(View.GONE);

        mMainSv = findViewById(R.id.main_scroll_v);

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

                Measurement measurement = new Measurement(result, getAndFormatDate(), fatEditText.getText().toString());
                measurement.save();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }});

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext() /* Activity context */);
                String name = sharedPreferences.getString("gender", "");

                boolean jestKobieta = name.equals("1") ?  false : true;

                //funkcja licząca i wpisujaca tluszcz
                double talia = Double.parseDouble(editTextArrayList.get(8).getText().toString());
                double waga = Double.parseDouble(editTextArrayList.get(15).getText().toString());

                double a = 4.15d * talia;
                double b = a / 2.54d;
                double c = 0.082d * waga * 2.2d;
                double d = 0d;
                double e = waga * 2.2;
                if( jestKobieta ){

                    d = b - c - 76.76d;
                } else {

                    d = b - c - 98.42d;
                }

                double wynik = d/e * 100d;

                fatEditText.setText(Double.toString(wynik));

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

    private String getAndFormatDate(){

        Date date = new Date();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

}
