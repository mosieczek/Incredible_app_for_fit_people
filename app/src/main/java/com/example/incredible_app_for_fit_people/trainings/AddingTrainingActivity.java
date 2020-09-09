package com.example.incredible_app_for_fit_people.trainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.incredible_app_for_fit_people.R;
import com.example.incredible_app_for_fit_people.database.Exercise;
import com.example.incredible_app_for_fit_people.database.Series;
import com.example.incredible_app_for_fit_people.database.Training;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.incredible_app_for_fit_people.trainings.TraningMainActivity.addRemoveLinearLayout;
import static com.example.incredible_app_for_fit_people.trainings.TraningMainActivity.addRemoveTableLayout;

public class AddingTrainingActivity extends AppCompatActivity implements Dialog.DialogListener {


    LinearLayout llParent;
    LayoutInflater layoutInflater;
    List<View> myView;
    Button add_traning_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_training);

        llParent = findViewById(R.id.exercise_ll);

        myView = new ArrayList<>();

        initListeners();
    }


    ///////////TOOLBAR

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

                Dialog dialog = new Dialog();
                dialog.show(getSupportFragmentManager(), "dialog");

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initListeners(){

        add_traning_btn = findViewById(R.id.add_traning_btn);
        add_traning_btn.setOnClickListener( view -> {

            String date =  new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            Training traning = new Training(date, "silowy");
            traning.save();

            for(int i=0; i < llParent.getChildCount(); i++) {

                TableLayout tl = llParent.getChildAt(i).findViewById(R.id.table_layout);

                EditText cwiczenie = tl.findViewById(R.id.cwiczenieEdit);

                Exercise item = new Exercise(traning, cwiczenie.getText().toString() );
                item.save();

                for( int j = 0; j < tl.getChildCount() - 2; j++){ ///musimy odjąć cwiczenie i cwiczenieEdit

                    EditText ciezar = tl.getChildAt( j + 2).findViewById(R.id.ciezarEdit);
                    EditText serie = tl.getChildAt( j + 2).findViewById(R.id.serieEdit);
                    EditText powtorzenia = tl.getChildAt( j + 2).findViewById(R.id.powtorzeniaEdit);

                    Series set = new Series(item, ciezar.getText().toString(), serie.getText().toString(), powtorzenia.getText().toString() );
                    set.save();
                }
            }
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });


    }

    @Override
    public void applyValues(String cwiczenie, Long serie) {

        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.exercise_layout, null, false);

        addRemoveLinearLayout(view, llParent);

        EditText cwiczenieEdit = view.findViewById(R.id.cwiczenieEdit);
        cwiczenieEdit.setText(cwiczenie);

        TableLayout tlParent = view.findViewById(R.id.table_layout);

        for(Integer i = 1; i <= serie; i++){

            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View serieView = layoutInflater.inflate(R.layout.single_set, null, false);
            addRemoveTableLayout(serieView, tlParent);
            EditText seriaEdit = serieView.findViewById(R.id.serieEdit);
            seriaEdit.setText( i.toString() );

            //myView.add(serieView);
            tlParent.addView(serieView);
        }


        //myView.add(view);
        llParent.addView(view);
    }
}

