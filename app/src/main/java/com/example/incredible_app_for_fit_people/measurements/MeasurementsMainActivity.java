package com.example.incredible_app_for_fit_people.measurements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
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

import com.activeandroid.content.ContentProvider;
import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.adapters.FormatCursorAdapter;
import com.example.incredible_app_for_fit_people.database.Measurement;
import com.example.incredible_app_for_fit_people.settings.SettingsActivity;
import com.example.incredible_app_for_fit_people.trainings.TraningMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MeasurementsMainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    ListView lv;
    SimpleCursorAdapter dbAdapter;

    private static final int REQUEST_CODE_ADDING = 0;
    private static final int REQUEST_CODE_EDITING = 1;

    private TextView mEmptyList;
    private AnimationDrawable jinglesAnimation;

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements_main);

        lv = findViewById(R.id.lista);  ///Tworze obiekt list View i dodaje odpowiednie listenery
        startLoader();

        mEmptyList = findViewById(R.id.empty_list);
        lv.setEmptyView(mEmptyList); //Wyswietla informacje o pustej liscie
        lv.setClickable(true);

        setListListener();
        initEasterEgg();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.measurements);

    }

    private void initEasterEgg () {
        mEmptyList.setOnClickListener(v -> {
            if (jinglesAnimation == null) {
                jinglesAnimation = (AnimationDrawable) mEmptyList.getCompoundDrawables()[1];
                mEmptyList.post(() -> {
                    if (jinglesAnimation != null) {
                        jinglesAnimation.start();
                    }
                });
            } else {
                stopJingles();
            }
        });
    }

    private void stopJingles () {
        if (jinglesAnimation != null) {
            jinglesAnimation.stop();
            jinglesAnimation = null;
            mEmptyList.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.jingle_animation, 0, 0);

        }
    }

    void setListListener(){

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = EditingMeasurementActivity
                        .newIntent(MeasurementsMainActivity.this);
                intent.putExtra("id", id);
                ///wysyłamy id (mogą pojawić się błędy w przyszłości
                // jak dodamy możliwość usuwania obiektów) (chociaż wcale nie muszą :)
                startActivityForResult(intent, REQUEST_CODE_EDITING);

            }
        });

        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                MenuInflater inflater = actionMode.getMenuInflater();
                ///Po wejsciu w tryb multiselect zmieniamy menu w toolbar
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
            public void onItemCheckedStateChanged(ActionMode actionMode,
                                                  int i, long l, boolean b) {

            }
        });
    }

    private void deleteSelected() { ///USUNICIE WIELU ELEMENTOW
        long[] zaznaczone = lv.getCheckedItemIds(); ///Pobieramy liste id zaznaczonych elementow

        for (int i = 0; i < zaznaczone.length; i++) {

            Measurement item = Measurement.load(Measurement.class, zaznaczone[i]);
            item.delete();

        }
        counter = 0;
        startActivity(getIntent());
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
                Intent intent = AddingMeasurementChoiseActivity
                        .newIntent(MeasurementsMainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_ADDING);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    ///Reaction on the return from activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDITING) {
            if (resultCode == RESULT_OK) {

                counter = 0;
            }
        }

        if (requestCode == REQUEST_CODE_ADDING) {
            if (resultCode == RESULT_OK) {

                startActivity(getIntent());
                counter = 0;
            }
        }

    }

    private void startLoader(){

        getSupportLoaderManager().initLoader(0, null, this);

        String[] mapFrom = new String[]{"_id", "Data","Waga", "TankaTluszczowa", "Difference"};
        int[] mapTo = new int[]{R.id.id, R.id.date,R.id.weight, R.id.fat, R.id.WeigthDiffText};

        dbAdapter = new SimpleCursorAdapter(this, R.layout.list_table, null ,mapFrom,mapTo, 0);

        lv.setAdapter(dbAdapter);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle cursor) {

        return new CursorLoader(this,
                ContentProvider.createUri(Measurement.class, null),
                null, null, null, "Data DESC"
        );

    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Cursor cursor) {
        //IDK but cursor needs to be reloaded a few time to properly find new measurement
        if(counter < 3){

            calculateDiference();
        }
        ((SimpleCursorAdapter)lv.getAdapter()).swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

        ((SimpleCursorAdapter)lv.getAdapter()).swapCursor(null);
    }

    public void calculateDiference(){

        Log.d("count", String.valueOf(lv.getCount() ));
        if(lv.getCount() >= 2 ) {
            for (int i = 0; i < lv.getCount() - 1; i++) {
                View v = lv.getChildAt(i);
                View v2 = lv.getChildAt(i + 1);

                TextView id = v2.findViewById(R.id.id);
                TextView weigth = v.findViewById(R.id.weight);
                TextView weigth2 = v2.findViewById(R.id.weight);

                Measurement measurement =
                        Measurement.load(Measurement.class, Long.valueOf(id.getText().toString()));

                if(measurement == null){
                    break;
                }

                Float f = Float.valueOf(weigth.getText().toString());
                Float f2 = Float.valueOf(weigth2.getText().toString());

                Float result =  f2 - f;
                measurement.setDifference(result.toString());
                measurement.save();

                counter++;

            }
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.trenings:
                            Intent intent = new Intent(getApplicationContext(), TraningMainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.measurements:
                            return true;
                        case R.id.setting:
                            Intent intent3 = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent3);
                            overridePendingTransition(0,0);
                            return true;
                    }

                    return true;
                }
            };

}
