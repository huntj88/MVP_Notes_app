package com.example.james.mvp.app;

import com.example.james.mvp.utils.DatabaseHelper;
import com.example.james.mvp.utils.Note;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by James on 3/13/2017.
 */

public class NotePresenter implements MainMVP.PresenterOps, MainMVP.RequiredPresenterOps {


    // Layer View reference
    private WeakReference<MainMVP.RequiredViewOps> mView;
    // Layer Model reference
    private MainMVP.ModelOps mModel;

    public NotePresenter(MainMVP.RequiredViewOps mView, DatabaseHelper dbHelper) {
        this.mView = new WeakReference<>(mView);
        this.mModel = new NoteModel(this, dbHelper);
    }

    @Override
    public void addNote(Note note) {
        mModel.insertNote(note);
    }

    @Override
    public void deleteNote(Note note) {

    }

    @Override
    public void editNote(Note note) {

    }

    @Override
    public ArrayList<Note> getAllNotes() {
        return mModel.getAllNotes();
    }

    @Override
    public void onNoteInserted(Note note) {

    }

    @Override
    public void onNoteRemoved(Note note) {

    }

    @Override
    public void onNoteEdited(Note none) {

    }

    @Override
    public void onError(String errorMsg) {

    }
}
