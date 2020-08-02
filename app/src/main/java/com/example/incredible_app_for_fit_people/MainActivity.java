package com.example.incredible_app_for_fit_people;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.content.ContentProvider;
import com.activeandroid.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    DBHandler dbHandler;
    TextView dbTextView;
    EditText dbEditText;
    ListView lv;
    SimpleCursorAdapter dbAdapter;

    private static final int REQUEST_CODE_ADDING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);

//        dbTextView = (TextView) findViewById(R.id.db_text_view);
//        dbEditText = (EditText) findViewById(R.id.db_edit_text);

        lv = findViewById(R.id.lista);  ///Tworze obiekt list View i dodaje odpowiednie listenery
        startLoader();

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


               // List<Measurement> measurements = new Select().from(Measurement.class).orderBy("Name ASC").execute();


            }
        }

    }

    private void startLoader(){

        getSupportLoaderManager().initLoader(0, null, this);

        String[] mapFrom = new String[]{"Szyja","Klatka"};
        int[] mapTo = new int[]{R.id.productName,R.id.productLink};
        dbAdapter = new SimpleCursorAdapter(this, R.layout.list_table, null ,mapFrom,mapTo, 0);

        lv.setAdapter(dbAdapter);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle cursor) {
        return new CursorLoader(this,
                ContentProvider.createUri(Measurement.class, null),
                null, null, null, null
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Cursor cursor) {
        ((SimpleCursorAdapter)lv.getAdapter()).swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        ((SimpleCursorAdapter)lv.getAdapter()).swapCursor(null);
    }


}
