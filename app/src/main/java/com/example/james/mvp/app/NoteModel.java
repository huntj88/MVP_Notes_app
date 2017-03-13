package com.example.james.mvp.app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.james.mvp.utils.DatabaseHelper;
import com.example.james.mvp.utils.Note;

import java.util.ArrayList;

/**
 * Created by James on 3/13/2017.
 */

public class NoteModel implements MainMVP.ModelOps {

    private MainMVP.RequiredPresenterOps mPresenter;
    private DatabaseHelper dbHelper;

    public NoteModel(MainMVP.RequiredPresenterOps mPresenter, DatabaseHelper dbHelper) {
        this.mPresenter = mPresenter;
        this.dbHelper = dbHelper;
    }

    @Override
    public void insertNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "INSERT INTO "+DatabaseHelper.DATABASE_TABLE+" ("+DatabaseHelper.KEY_TITLE+", "+DatabaseHelper.KEY_TEXT+","+DatabaseHelper.KEY_UNIX+") VALUES ('"+note.getTitle()+"', '"+note.getNoteText()+"','"+note.getUnixTimeMade()+"')";
        SQLiteStatement statement = db.compileStatement(sql);
        long rowId = statement.executeInsert();
        Log.d("inserted","yay");
    }

    @Override
    public void removeNote(Note note) {

    }

    @Override
    public void editNote(Note note) {

    }

    @Override
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columnsToReturn = { DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_TEXT, DatabaseHelper.KEY_UNIX };
        Cursor dbCursor = db.query(DatabaseHelper.DATABASE_TABLE, null, null, null, null, null, null);

        if (dbCursor != null && dbCursor.moveToFirst()) {
            //get columns
            int titleColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_TITLE);
            int textColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_TEXT);
            int unixColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_UNIX);
            int idColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_ROWID);
            //add row to list
            do {
                long thisId = dbCursor.getLong(idColumn);
                String thisTitle = dbCursor.getString(titleColumn);
                String thisText = dbCursor.getString(textColumn);
                String thisUnix = dbCursor.getString(unixColumn);
                notes.add(new Note(thisText,thisTitle,Long.getLong(thisUnix)));
            }
            while (dbCursor.moveToNext());
            dbCursor.close();
        }

        return notes;
    }

}
