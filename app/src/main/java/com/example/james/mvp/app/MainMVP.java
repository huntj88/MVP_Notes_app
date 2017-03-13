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
     * View mandatory methods. Available to Presenter
     *      Presenter -> View
     */
    interface RequiredViewOps {
        void showToast(String msg);
        void showAlert(String msg);
        // any other ops
    }

    /**
     * Operations offered from Presenter to View
     *      View -> Presenter
     */
    interface PresenterOps{
        void addNote(Note note);
        void deleteNote(Note note);
        void editNote(Note note);
        ArrayList<Note> getAllNotes();
        // any other ops to be called from View
    }

    /**
     * Operations offered from Presenter to Model
     *      Model -> Presenter
     */
    interface RequiredPresenterOps {
        void onNoteInserted(Note note);
        void onNoteRemoved(Note note);
        void onNoteEdited(Note none);
        void onError(String errorMsg);
        // Any other returning operation Model -> Presenter
    }

    /**
     * Model operations offered to Presenter
     *      Presenter -> Model
     */
    interface ModelOps {
        void insertNote(Note note);
        void removeNote(Note note);
        void editNote(Note note);
        ArrayList<Note> getAllNotes();
        // Any other data operation
    }
}
