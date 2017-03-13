package com.example.james.mvp.app.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.james.mvp.R;
import com.example.james.mvp.app.ComposeActivity;
import com.example.james.mvp.utils.Note;

/**
 * Created by James on 3/13/2017.
 */

public class NoteCell extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Note note;
    private TextView title;
    private CardView cardView;

    public NoteCell(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.note_title);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        cardView.setOnClickListener(this);
    }

    public void setNote(Note note)
    {
        this.note = note;
        title.setText(note.getTitle());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),ComposeActivity.class);
        intent.putExtra("noteId",note.getId());
        v.getContext().startActivity(intent);
    }
}
