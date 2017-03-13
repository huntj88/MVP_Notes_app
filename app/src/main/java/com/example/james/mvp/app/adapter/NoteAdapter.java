package com.example.james.mvp.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.mvp.R;
import com.example.james.mvp.utils.Note;

import java.util.ArrayList;

/**
 * Created by James on 3/13/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteCell> {

    private ArrayList<Note> items;

    public NoteAdapter(ArrayList items)
    {
        this.items = items;
    }

    @Override
    public NoteCell onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.cell_note, parent, false);
        NoteCell temp = new NoteCell(root);
        return temp;

    }

    @Override
    public void onBindViewHolder(NoteCell holder, int position) {
        Note note = items.get(position);
        holder.setNote(note);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
