package com.example.james.mvp.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.james.mvp.R;
import com.example.james.mvp.utils.DatabaseHelper;
import com.example.james.mvp.utils.Note;

public class ComposeActivity extends AppCompatActivity implements MainMVP.RequiredViewOps{


    private MainMVP.PresenterOps mPresenter;
    private Note note;
    private Toolbar toolbar;
    private EditText noteTitle;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        long noteId = getIntent().getLongExtra("noteId",-1);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        noteTitle = (EditText) findViewById(R.id.note_title);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mPresenter = new NotePresenter(this, dbHelper);

        if(noteId != -1)
        {
            note = mPresenter.getNoteById(noteId);
            multiAutoCompleteTextView.setText(note.getNoteText());
            noteTitle.setText(note.getTitle());
        }
        else
        {
            note = new Note(noteId,"");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compose_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                // User chose the "Settings" item, show the app settings UI...
                Log.d("save","test");
                note = mPresenter.saveNote(note.editNote(multiAutoCompleteTextView.getText().toString(),noteTitle.getText().toString()));
                finish();
                return true;

            case R.id.delete:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                mPresenter.deleteNote(note);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showAlert(String msg) {

    }
}
