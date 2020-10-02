package com.xela.notelist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xela.notelist.R;
import com.xela.notelist.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private ArrayList<Note> dataRecycler;

    public NoteAdapter(ArrayList<Note> dataRecycler) {
        this.dataRecycler = dataRecycler;
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
        holder.note_btn.setText(dataRecycler.get(position).getNote_name());
    }

    @Override
    public int getItemCount() {
        return dataRecycler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button note_btn;

        public MyViewHolder(View itemView){
            super(itemView);
            note_btn = itemView.findViewById(R.id.note_btn);
        }
    }
}
