package com.example.james.mvp.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.james.mvp.R;
import com.example.james.mvp.utils.Note;

/**
 * Created by James on 3/13/2017.
 */

public class NoteCell extends RecyclerView.ViewHolder {

    private Note note;
    private TextView title;

    public NoteCell(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.note_title);
    }

    public void setNote(Note note)
    {
        this.note = note;
        title.setText(note.getNoteText());
    }
}
