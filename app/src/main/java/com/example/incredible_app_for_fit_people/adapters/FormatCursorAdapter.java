package com.example.incredible_app_for_fit_people.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatCursorAdapter extends SimpleCursorAdapter {

    protected int[] mFormats;

    public static final int FORMAT_TEXT = 0;
    public static final int FORMAT_CURRENCY = 1;
    public static final int FORMAT_DATE = 2;

    public FormatCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int[] formats, int flags) {
        super(context, layout, c, from, to, flags);
        mFormats = formats;
        ViewBinder viewBinder = new ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                int formatType = mFormats[columnIndex-1];
                switch (formatType) {
                    case FORMAT_CURRENCY:
                        NumberFormat nf = NumberFormat.getCurrencyInstance();
                        nf.setMaximumFractionDigits(2);
                        ((TextView) view).setText(nf.format(cursor.getDouble(columnIndex)));
                        return true;
                    case FORMAT_DATE:
                        DateFormat df = SimpleDateFormat.getDateTimeInstance();
                        ((TextView) view).setText(df.format(new Date(cursor.getLong(columnIndex))));
                        return true;
                }
                return false;
            }
        };
        setViewBinder(viewBinder);
    }
}