package com.xela.notelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.xela.notelist.adapter.NoteAdapter;
import com.xela.notelist.adapter.RecyclerTouchHelper;
import com.xela.notelist.crudroom.CRUD_Room;
import com.xela.notelist.database.Room_db;
import com.xela.notelist.model.Note;

import java.util.List;

public class NoteScreen extends AppCompatActivity implements RecyclerTouchHelper.RecyclerItemTouchHelperListener {

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    Note notes;
    NoteAdapter na;
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

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv);

        fly_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentui.setVisibility(View.VISIBLE);
                fly_btn.setVisibility(View.GONE);
                loadFragment(new FragmentAdd());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(fragmentui.getVisibility()==View.VISIBLE){
            fragmentui.setVisibility(View.GONE);
            fly_btn.setVisibility(View.VISIBLE);
        }
    }

    public void setuprvadapter(){
        adapter = new NoteAdapter(NoteScreen.this,noteList);
        rv.setLayoutManager(new LinearLayoutManager(NoteScreen.this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(adapter);
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
                //super.onPostExecute(notes);
                setuprvadapter();
            }
        }

        GetNote gn = new GetNote();
        gn.execute();
    }

    public void delNote() {
        class DelNote extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {

                Room_db appDB = Room_db.getInstance(NoteScreen.this);
                appDB.noteDao().delete(notes);
                return null;
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                //super.onPostExecute(notes);
                getNote();
            }
        }

        DelNote dn = new DelNote();
        dn.execute();
    }

    public void delAllNote() {
        class DelAllNote extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {

                Room_db appDB = Room_db.getInstance(NoteScreen.this);
                appDB.noteDao().deleteall();
                return null;
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                //super.onPostExecute(notes);
                getNote();
            }
        }

        DelAllNote dn = new DelAllNote();
        dn.execute();
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("null").commit();
            return true;
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof NoteAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            int id = noteList.get(viewHolder.getAdapterPosition()).getId();
            String title = noteList.get(viewHolder.getAdapterPosition()).getTitle();
            notes= new Note(id,title);
            delNote();
            // backup of removed item for undo purpose
            //Note deletedItem = noteList.get(viewHolder.getAdapterPosition());
            //int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view

            //na.removeItem(viewHolder.getAdapterPosition());

        }
    }



}