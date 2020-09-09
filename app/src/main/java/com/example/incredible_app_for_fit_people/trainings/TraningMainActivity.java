package com.example.incredible_app_for_fit_people.trainings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.activeandroid.content.ContentProvider;
import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Cardio;
import com.example.incredible_app_for_fit_people.database.Exercise;
import com.example.incredible_app_for_fit_people.database.Series;
import com.example.incredible_app_for_fit_people.database.Training;
import com.example.incredible_app_for_fit_people.measurements.MeasurementsMainActivity;
import com.example.incredible_app_for_fit_people.settings.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class TraningMainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CARDIO = 0;
    private static final int WEIGTH_TRANING = 1;
    private static final int REQUEST_CODE_ADDING = 0;
    private ListView lv;
    SimpleCursorAdapter dbAdapter;
    private int list_id;
    TextView mEmptyList;
    private AnimationDrawable jinglesAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_main);

        ///Variable which tells is it cardio or common traning
        list_id = WEIGTH_TRANING;

        lv = findViewById(R.id.exercise_list);
        mEmptyList = findViewById(R.id.empty_list);
        mEmptyList.setClickable(true);
        lv.setEmptyView(mEmptyList); //Wyswietla informacje o pustej liscie
        lv.setClickable(true);

        startLoader();
        setListListener();
        initEasterEgg();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.trenings);
        this.setTitle("Siłowy");
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
                if(list_id == WEIGTH_TRANING){

                    Intent intent = new Intent(getApplicationContext(), AddingTrainingActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_ADDING);
                }
                if(list_id == CARDIO){

                    Intent intent = new Intent(getApplicationContext(), AddingCardioActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_ADDING);
                }

                return true;

            case R.id.item_2:

                if (list_id == WEIGTH_TRANING) {

                    list_id = CARDIO;
                    this.setTitle("Cardio");
                    getSupportLoaderManager().restartLoader(list_id, null, this);


                } else {

                    list_id = WEIGTH_TRANING;
                    this.setTitle("Siłowy");
                    getSupportLoaderManager().restartLoader(list_id,null,this);

                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    void setListListener() {

        ///NEED TO ADD CARDIO ACTIVITY AND IF CASE FOR HANDLING 
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(list_id == CARDIO){


                }
                if(list_id == WEIGTH_TRANING){
                    Intent intent = new Intent(getApplicationContext(), EditingTrainingActivity.class);
                    intent.putExtra("id", id);  ///wysyłamy id (mogą pojawić się błędy w przyszłości jak dodamy możliwość usuwania obiektów) (chociaż wcale nie muszą :)
                    startActivityForResult(intent, 0);
                }
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

        if(list_id == CARDIO){

            for(long l : zaznaczone){

                Cardio cardio = Cardio.load(Cardio.class, l);
                cardio.delete();
            }
        }
        if(list_id == WEIGTH_TRANING){

            for(long l : zaznaczone) {

                Training training = Training.load(Training.class, l);
                List<Exercise> exercises = training.exercises();
                //We need to delete each children in database
                for (int j = 0; j < exercises.size(); j++) {

                    List<Series> series = exercises.get(j).sets();
                    for (int g = 0; g < series.size(); g++) {
                        series.get(g).delete();
                    }
                    exercises.get(j).delete();
                }
                training.delete();
            }
        }

    }


    private void startLoader() {

        getSupportLoaderManager().initLoader(list_id, null, this);

        String[] mapFrom = new String[]{"Data", "Typ"};
        int[] mapTo = new int[]{R.id.date, R.id.type};

        dbAdapter = new SimpleCursorAdapter(this, R.layout.exercise_list_table, null, mapFrom, mapTo, 0);

        lv.setAdapter(dbAdapter);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle cursor) {

        if (arg0 == WEIGTH_TRANING) {

            return new CursorLoader(this,
                    ContentProvider.createUri(Training.class, null),
                    null, null, null, null
            );
        }
        if (arg0 == CARDIO) {

            return new CursorLoader(this,
                    ContentProvider.createUri(Cardio.class, null),
                    null, null, null, null
            );
        }

        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Cursor cursor) {

        ((SimpleCursorAdapter)lv.getAdapter()).swapCursor(cursor);

    }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        ((SimpleCursorAdapter)lv.getAdapter()).swapCursor(null);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.trenings:
                            return true;
                        case R.id.measurements:
                            Intent intent2 = new Intent(getApplicationContext(), MeasurementsMainActivity.class);
                            startActivityForResult(intent2, REQUEST_CODE_ADDING);
                            overridePendingTransition(0,0);
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

    static public void addRemoveLinearLayout(View mView, LinearLayout layout){

        mView.setClickable(true);
        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                layout.removeView(view);
                return true;
            }

        });
    }

    static public void addRemoveTableLayout(View mView, TableLayout layout){

        mView.setClickable(true);
        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                layout.removeView(view);
                return true;
            }

        });
    }
}