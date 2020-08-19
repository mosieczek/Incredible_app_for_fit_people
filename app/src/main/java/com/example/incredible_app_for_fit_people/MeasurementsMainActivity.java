package com.example.incredible_app_for_fit_people;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.content.ContentProvider;

public class MeasurementsMainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    ListView lv;
    SimpleCursorAdapter dbAdapter;

    private static final int REQUEST_CODE_ADDING = 0;
    private static final int REQUEST_CODE_EDITING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements_main);
        ActiveAndroid.initialize(this);


        lv = findViewById(R.id.lista);  ///Tworze obiekt list View i dodaje odpowiednie listenery
        startLoader();

        lv.setEmptyView(findViewById(R.id.emptyListInformation)); //Wyswietla informacje o pustej liscie
        lv.setClickable(true);

        setListListener();
    }

    void setListListener(){

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = EditingMeasurementActivity.newIntent(MeasurementsMainActivity.this);
                intent.putExtra("id", id);  ///wysyłamy id (mogą pojawić się błędy w przyszłości jak dodamy możliwość usuwania obiektów) (chociaż wcale nie muszą :)
                startActivityForResult(intent, REQUEST_CODE_EDITING);

            }
        });

        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                MenuInflater inflater = actionMode.getMenuInflater(); ///Po wejsciu w tryb multiselect zmieniamy menu w toolbar
                inflater.inflate(R.menu.delete_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                ///Dodajemy odpowiednie akcje na wcisniecie przyicsku
                if (menuItem.getItemId() == R.id.multiple_delete) {
                    deleteSelected();
                    return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }
        });
    }

    private void deleteSelected() { ///USUNICIE WIELU ELEMENTOW
        long[] zaznaczone = lv.getCheckedItemIds(); ///Pobieramy liste id zaznaczonych elementow

        for (int i = 0; i < zaznaczone.length; i++) {

            Measurement item = Measurement.load(Measurement.class, zaznaczone[i]);
            item.delete();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { ///Dodajemy podstawowe menu do toolbara
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_1:
                Intent intent = AddingMeasurementChoiseActivity.newIntent(MeasurementsMainActivity.this);
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

        String[] mapFrom = new String[]{"Data","Waga", "TankaTluszczowa"};
        int[] mapTo = new int[]{R.id.date,R.id.weight, R.id.fat};
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
