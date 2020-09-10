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
import android.widget.TextView;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EditingMeasurementActivity extends AppCompatActivity {

    ArrayList<EditText> editTextArrayList;
    EditText fatEditText;
    Button saveButton;
    Button calculateButton;
    Measurement measurement;
    Long data_id;
    TextView dataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_measurement);

        editTextArrayList = new ArrayList<>();


        initEditTexts();
        addValuesFromDB();

        setListeners();

    }



    public static Intent newIntent(Context packageContext){

        Intent intent = new Intent(packageContext, EditingMeasurementActivity.class);
        //some put extras if needed
        return intent;
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

        calculateButton.setEnabled(true);

    }

    private void addValuesFromDB(){

        Intent intent = getIntent();
        data_id = intent.getLongExtra("id", 4);

        measurement = Measurement.load(Measurement.class, data_id);

        editTextArrayList.get(0).setText( measurement.getSzyja() );
        editTextArrayList.get(1).setText( measurement.getKlatkaPiersiowa() );
        editTextArrayList.get(2).setText( measurement.getBicepsLewy() );
        editTextArrayList.get(3).setText( measurement.getBicepsLewyNapiety() );
        editTextArrayList.get(4).setText( measurement.getBicepsPrawy() );
        editTextArrayList.get(5).setText( measurement.getBicepsPrawyNapiety() );
        editTextArrayList.get(6).setText( measurement.getPrzedramieLewe() );
        editTextArrayList.get(7).setText( measurement.getPrzedramiePrawe() );
        editTextArrayList.get(8).setText( measurement.getTalia() );
        editTextArrayList.get(9).setText( measurement.getBrzuch() );
        editTextArrayList.get(10).setText( measurement.getBiodra() );
        editTextArrayList.get(11).setText( measurement.getUdoLewe() );
        editTextArrayList.get(12).setText( measurement.getUdoPrawe() );
        editTextArrayList.get(13).setText( measurement.getLydkaLewa() );
        editTextArrayList.get(14).setText( measurement.getLydkaPrawa() );
        editTextArrayList.get(15).setText( measurement.getWaga() );

        fatEditText.setText( measurement.getTkankaTluszczowa() );
    }

    private void setListeners(){

        calculateButton.setClickable(false);
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


        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Przepisanie listy EditText na String
                List<String> result = editTextArrayList.stream()
                        .map( x -> x.getText().toString())
                        .collect(Collectors.toList());

                //String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                measurement.updateValues( result, fatEditText.getText().toString() );
                measurement.save();


                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }});

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

}
