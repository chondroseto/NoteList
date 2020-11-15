package com.xela.notelist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.xela.notelist.crudroom.CRUD_Room;
import com.xela.notelist.model.Note;

@Database(entities = Note.class, exportSchema = false, version = 1)
public abstract class Room_db extends RoomDatabase{
    private static final String DB_NAME = "note_db";
    private static Room_db instance;

    public static synchronized Room_db getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),Room_db.class,DB_NAME).build();
        }
        return instance;
    }

    public abstract CRUD_Room noteDao();
}