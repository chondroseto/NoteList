package com.xela.notelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.firebase.firestore.FirebaseFirestore;
import com.xela.notelist.adapter.NoteAdapter;
import com.xela.notelist.model.Note;

import java.util.ArrayList;

public class NoteScreen extends AppCompatActivity {

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Note> data_recycler;
    RecyclerView.Adapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_screen);

        rv = (RecyclerView) findViewById(R.id.recview);

        dataRecycler();
        adapter = new NoteAdapter(data_recycler);
        rv.setLayoutManager(new LinearLayoutManager(NoteScreen.this));
        rv.setAdapter(adapter);
    }

    public void dataRecycler(){
        data_recycler = new ArrayList<>();
        data_recycler.add(new Note("1","mangapark.net"));
        data_recycler.add(new Note("2","google.com"));
        data_recycler.add(new Note("3","bing.com"));
    }


}