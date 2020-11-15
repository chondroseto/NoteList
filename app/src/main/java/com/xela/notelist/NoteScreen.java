package com.xela.notelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xela.notelist.adapter.NoteAdapter;
import com.xela.notelist.database.Room_db;
import com.xela.notelist.model.Note;

import java.util.List;

public class NoteScreen extends AppCompatActivity {

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Note> noteList;
    RecyclerView.Adapter adapter;
    RecyclerView rv;
    FrameLayout fragmentui;

    FloatingActionButton fly_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_screen);

        rv = findViewById(R.id.recview);
        fly_btn = findViewById(R.id.flybtn);
        fragmentui = findViewById(R.id.fragment_container);

        getNote();

        fly_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentui.setVisibility(View.VISIBLE);
                fly_btn.setVisibility(View.GONE);
                loadFragment(new FragmentAdd());
            }
        });

    }


    public void getNote() {
        class GetNote extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {

                Room_db appDB = Room_db.getInstance(NoteScreen.this);
                noteList = appDB.noteDao().getAll();
                return noteList;
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                adapter = new NoteAdapter(NoteScreen.this,noteList);
                rv.setLayoutManager(new LinearLayoutManager(NoteScreen.this));
                rv.setAdapter(adapter);
            }
        }

        GetNote gn = new GetNote();
        gn.execute();
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("null").commit();
            return true;
        }
        return false;
    }
}