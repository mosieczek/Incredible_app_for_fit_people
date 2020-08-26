package com.example.incredible_app_for_fit_people.trainings;

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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.activeandroid.content.ContentProvider;
import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Cardio;
import com.example.incredible_app_for_fit_people.database.Training;

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

        setListListener();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { ///Dodajemy podstawowe menu do toolbara
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.trainings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_1:
                Intent intent = new Intent(getApplicationContext(), AddingTrainingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADDING);
                return true;

            case R.id.item_2:
                Intent intent2 = new Intent(getApplicationContext(), AddingCardioActivity.class);
                startActivityForResult(intent2, REQUEST_CODE_ADDING);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    void setListListener(){

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), EditingTrainingActivity.class);
                intent.putExtra("id", id);  ///wysyłamy id (mogą pojawić się błędy w przyszłości jak dodamy możliwość usuwania obiektów) (chociaż wcale nie muszą :)
                startActivityForResult(intent, 0);

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

            Training item = Training.load(Training.class, zaznaczone[i]);
            item.delete();
        }
    }


    private void startLoader(){

        getSupportLoaderManager().initLoader(0, null, this);


        String[] mapFrom = new String[]{"Data","Typ"};
        int[] mapTo = new int[]{R.id.date,R.id.type};
        dbAdapter = new SimpleCursorAdapter(this, R.layout.exercise_list_table, null ,mapFrom,mapTo, 0);

        getSupportLoaderManager().initLoader(1, null, this);
        dbAdapter = new SimpleCursorAdapter(this, R.layout.exercise_list_table, null ,mapFrom,mapTo, 0);

        lv.setAdapter(dbAdapter);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle cursor) {

        if(arg0 == 1){

            return new CursorLoader(this,
                    ContentProvider.createUri(Training.class, null),
                    null, null, null, null
            );
        } else {

            return new CursorLoader(this,
                    ContentProvider.createUri(Cardio.class, null),
                    null, null, null, null
            );
        }

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