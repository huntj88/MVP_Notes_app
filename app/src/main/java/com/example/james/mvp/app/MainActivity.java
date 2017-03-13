package com.example.james.mvp.app;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.james.mvp.R;
import com.example.james.mvp.app.adapter.NoteAdapter;
import com.example.james.mvp.utils.DatabaseHelper;
import com.example.james.mvp.utils.Note;
import com.example.james.mvp.app.adapter.NoteDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainMVP.RequiredViewOps, View.OnClickListener{

    private MainMVP.PresenterOps mPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton addNoteFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNoteFAB = (FloatingActionButton) findViewById(R.id.new_note_fab);
        addNoteFAB.setOnClickListener(this);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mPresenter = new NotePresenter(this, dbHelper);
        //mPresenter.addNote(new Note("test"));




        mRecyclerView = (RecyclerView) findViewById(R.id.note_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new NoteDecoration(50,2));


    }

    @Override
    public void onResume()
    {
        super.onResume();
        ArrayList<Note> notes = mPresenter.getAllNotes();
        mAdapter = new NoteAdapter(notes);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.new_note_fab:
                //mPresenter.addNote(new Note("test two"));
                Intent intent = new Intent(this,ComposeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
