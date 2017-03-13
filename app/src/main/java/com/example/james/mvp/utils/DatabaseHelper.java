package com.example.james.mvp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by James on 3/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String KEY_ROWID = "id";
    public static final String KEY_UNIX = "unixString";
    public static final String KEY_TEXT = "text";
    public static final String KEY_TITLE = "title";
    public static final String DATABASE_NAME= "notedb";
    public static final String DATABASE_TABLE = "notes";
    public static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
                + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TEXT + " TEXT, " + KEY_TITLE + " TEXT, " + KEY_UNIX + " TEXT  )";
        db.execSQL(CREATE_NOTES_TABLE);

        Log.d("database made", CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }


}
