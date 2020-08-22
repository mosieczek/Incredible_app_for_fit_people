package com.example.incredible_app_for_fit_people.trainings;

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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.activeandroid.content.ContentProvider;
import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Measurement;
import com.example.incredible_app_for_fit_people.database.Traning;

public class TraningMainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static final int REQUEST_CODE_ADDING = 0;
    private ListView lv;
    SimpleCursorAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_main);

        lv = findViewById(R.id.exercise_list);

        lv.setEmptyView(findViewById(R.id.emptyListInformation)); //Wyswietla informacje o pustej liscie

        lv.setClickable(true);

        startLoader();


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
                Intent intent = new Intent(getApplicationContext(), AddingTrainingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADDING);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void startLoader(){

        getSupportLoaderManager().initLoader(0, null, this);

        String[] mapFrom = new String[]{"Data","Typ"};
        int[] mapTo = new int[]{R.id.date,R.id.type};
        dbAdapter = new SimpleCursorAdapter(this, R.layout.exercise_list_table, null ,mapFrom,mapTo, 0);

        lv.setAdapter(dbAdapter);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle cursor) {
        return new CursorLoader(this,
                ContentProvider.createUri(Traning.class, null),
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