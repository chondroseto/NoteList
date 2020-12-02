package com.xela.notelist.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xela.notelist.List_Screen;
import com.xela.notelist.NoteScreen;
import com.xela.notelist.R;
import com.xela.notelist.database.Room_db;
import com.xela.notelist.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context mCtx;
    private List<Note> dataRecycler;

    public NoteAdapter(Context mCtx,List<Note> noteList) {
        this.mCtx  = mCtx;
        this.dataRecycler = noteList;
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = LayoutInflater.from(mCtx).inflate(R.layout.note,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteAdapter.MyViewHolder holder, final int position) {

        holder.note_btn.setText(dataRecycler.get(position).getTitle());
        holder.note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), List_Screen.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataRecycler.size();
    }

    //public void setDataRecycler(List<Note> dataRecycler){
    //    this.dataRecycler = dataRecycler;
    //    notifyDataSetChanged();
    //}

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        EditText note_btn;
        public RelativeLayout bg,fg;

        public MyViewHolder(View itemView){
            super(itemView);
            note_btn = itemView.findViewById(R.id.note_btn);
            bg = itemView.findViewById(R.id.background);
            fg = itemView.findViewById(R.id.foreground);

        }

        /*
        @Override
        public void onClick(View view) {
            Note d = dataRecycler.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, List_Screen.class);
            mCtx.startActivity(intent);
        }

         */
    }

    public void removeItem(int position) {
        dataRecycler.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Note notes, int position) {
        dataRecycler.add(position, notes);
        // notify item added by position
        notifyItemInserted(position);
    }


}
