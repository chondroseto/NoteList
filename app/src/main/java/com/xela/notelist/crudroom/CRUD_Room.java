package com.xela.notelist.crudroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.xela.notelist.model.Note;

import java.util.List;

@Dao
public interface CRUD_Room {

    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Insert
    void insert(Note notes);

    @Update
    void update(Note notes);

    @Delete
    void delete(Note notes);

}
