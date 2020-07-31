package com.example.incredible_app_for_fit_people;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MeasurementsDB";
    public static final String TABLE_MEASUREMENTS = "measurements";
    public static final String COLUMN_ID = "_id";
    public static final String COL1 = "data";
    public static final String COL2 = "szyja";
    public static final String COL3 = "klatkaPiersiowa";
    public static final String COL4 = "bicepsLewy";
    public static final String COL5 = "bicepsLewyNapiety";
    public static final String COL6 = "bicepsPrawy";
    public static final String COL7 = "bicepsPrawyNapiety";
    public static final String COL8 = "przedramieLewe";
    public static final String COL9 = "przedramiePrawe";
    public static final String COL10 = "talia";
    public static final String COL11 = "brzuch";
    public static final String COL12 = "biodra";
    public static final String COL13 = "udoLewe";
    public static final String COL14 = "udoPrawe";
    public static final String COL15 = "lydkaLewa";
    public static final String COL16 = "lydkaPrawa";
    public static final String COL17 = "tkankaTluszczowa";





    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_MEASUREMENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1 + " DATE NOT NULL, "
                + COL2 + " FLOAT, "
                + COL3 + " FLOAT, "
                + COL4 + " FLOAT, "
                + COL5 + " FLOAT, "
                + COL6 + " FLOAT, "
                + COL7 + " FLOAT, "
                + COL8 + " FLOAT, "
                + COL9 + " FLOAT, "
                + COL10 + " FLOAT, "
                + COL11 + " FLOAT, "
                + COL12 + " FLOAT, "
                + COL13 + " FLOAT, "
                + COL14 + " FLOAT, "
                + COL15 + " FLOAT, "
                + COL16 + " FLOAT, "
                + COL17 + " FLOAT, );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASUREMENTS);
        onCreate(db);
    }




}
