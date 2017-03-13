package com.example.james.mvp.app;

import android.content.ContentValues;
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
    public Note saveNote(Note note) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(note.getId() == -1) {
            String sql = "INSERT INTO " + DatabaseHelper.DATABASE_TABLE + " (" + DatabaseHelper.KEY_TITLE + ", " + DatabaseHelper.KEY_TEXT + "," + DatabaseHelper.KEY_UNIX + ") VALUES ('" + note.getTitle() + "', '" + note.getNoteText() + "','" + note.getUnixTimeMade() + "')";
            SQLiteStatement statement = db.compileStatement(sql);
            long rowId = statement.executeInsert();
            Log.d("inserted", "yay");

            Note temp = new Note(rowId,note.getNoteText(),note.getTitle(),note.getUnixTimeMade());
            mPresenter.onNoteSaved(temp);
            return temp;
        } else {
            ContentValues newValues = new ContentValues();
            newValues.put(DatabaseHelper.KEY_TEXT, note.getNoteText());
            newValues.put(DatabaseHelper.KEY_TITLE, note.getTitle());
            newValues.put(DatabaseHelper.KEY_UNIX, note.getUnixTimeMade());

            String[] args = new String[]{note.getId()+""};
            db.update(DatabaseHelper.DATABASE_TABLE, newValues, DatabaseHelper.KEY_ROWID+"=?", args);
            mPresenter.onNoteSaved(note);
            return note;
        }
    }

    @Override
    public void removeNote(Note note) {
        if(note.getId()!=-1) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.KEY_ROWID + " = ?", new String[]{note.getId() + ""});
            mPresenter.onNoteRemoved(note);
        }
    }


    @Override
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String[] columnsToReturn = { DatabaseHelper.KEY_TITLE, DatabaseHelper.KEY_TEXT, DatabaseHelper.KEY_UNIX };
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
                notes.add(new Note(thisId,thisText,thisTitle,Long.getLong(thisUnix)));
            }
            while (dbCursor.moveToNext());
            dbCursor.close();
        }

        return notes;
    }

    @Override
    public Note getNoteById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] whereArgs = new String[] {
                ""+id
        };
        Cursor dbCursor = db.query(DatabaseHelper.DATABASE_TABLE, null, DatabaseHelper.KEY_ROWID+" = ?", whereArgs, null, null, null);

        if (dbCursor != null && dbCursor.moveToFirst()) {
            //get columns
            int titleColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_TITLE);
            int textColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_TEXT);
            int unixColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_UNIX);
            int idColumn = dbCursor.getColumnIndex(DatabaseHelper.KEY_ROWID);
            //add row to list

            long thisId = dbCursor.getLong(idColumn);
            String thisTitle = dbCursor.getString(titleColumn);
            String thisText = dbCursor.getString(textColumn);
            String thisUnix = dbCursor.getString(unixColumn);

            dbCursor.close();
            return new Note(thisId,thisText,thisTitle,Long.getLong(thisUnix));


        }

        return null;
    }

}
