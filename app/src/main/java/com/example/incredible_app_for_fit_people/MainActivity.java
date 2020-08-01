package com.example.incredible_app_for_fit_people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;
    TextView dbTextView;
    EditText dbEditText;
    ListView lv;

    private static final int REQUEST_CODE_ADDING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbTextView = (TextView) findViewById(R.id.db_text_view);
//        dbEditText = (EditText) findViewById(R.id.db_edit_text);

        lv = findViewById(R.id.lista);  ///Tworze obiekt list View i dodaje odpowiednie listenery
        lv.setEmptyView(findViewById(R.id.emptyListInformation)); //Wyswietla informacje o pustej liscie
        lv.setClickable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { ///Dodajemy podstawowe menu do toolbara
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   ///Menu sklada sie z dwóch elementów
        //Jednym jest dodanie produktu za posrednictwem Skanu QR a drugie przez podanie dnaych przez uzytkownika
        //W przypadku skanu qr dane mozna pozniej oczywiscie edytowac

        switch (item.getItemId()) {
            case R.id.item_1:
                Intent intent = AddingMeasurementActivity.newIntent(MainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_ADDING);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { ///Reaction on the return from activity
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADDING) { ///SKAN QR

            if (resultCode == RESULT_OK) {

                //rób rzeczy


            }
        }

    }

}
