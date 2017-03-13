package com.example.james.mvp.app;

import com.example.james.mvp.utils.Note;

import java.util.ArrayList;

/**
 * Created by James on 3/13/2017.
 */

/*
 * Aggregates all communication operations between MVP pattern layer:
 * Model, View and Presenter
 */
public interface MainMVP {

    /**
     *      Presenter -> View
     */
    interface RequiredViewOps {
        void showToast(String msg);
        void showAlert(String msg);
        // any other ops
    }

    /**
     *      View -> Presenter
     */
    interface PresenterOps{
        Note saveNote(Note note);
        void deleteNote(Note note);
        ArrayList<Note> getAllNotes();
        Note getNoteById(long id);
        // any other ops to be called from View
    }

    /**
     *      Model -> Presenter
     */
    interface RequiredPresenterOps {
        void onNoteSaved(Note note);
        void onNoteRemoved(Note note);
        void onError(String errorMsg);
        // Any other returning operation Model -> Presenter
    }

    /**
     *      Presenter -> Model
     */
    interface ModelOps {
        Note saveNote(Note note);
        void removeNote(Note note);
        ArrayList<Note> getAllNotes();
        Note getNoteById(long id);
        // Any other data operation
    }
}
