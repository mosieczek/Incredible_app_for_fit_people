package com.example.incredible_app_for_fit_people.trainings;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.incredible_app_for_fit_people.R;

public class Dialog extends AppCompatDialogFragment {

    private EditText cwiczenie;
    private EditText serie;
    private Boolean finishResult = false;
    private DialogListener listener;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        cwiczenie = view.findViewById(R.id.dialog_cwiczenie);
        serie = view.findViewById(R.id.dialog_serie);

        builder.setView(view).setTitle("Dodaj cwiczenie")
                .setNegativeButton("Anulowano", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String cw = cwiczenie.getText().toString();
                        Long sr = Long.valueOf( serie.getText().toString());

                        listener.applyValues(cw, sr);

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface DialogListener{

        void applyValues(String cwiczenie, Long serie);
    }
}
