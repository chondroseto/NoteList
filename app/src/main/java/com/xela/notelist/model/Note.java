package com.xela.notelist.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Entity annotation to specify the table's name
@Entity(tableName = "notes")
public class Note {
//PrimaryKey annotation to declare primary key with auto increment value
//ColumnInfo annotation to specify the column's name
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;


    public Note(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//@ColumnInfo(name = "body") var body: String = "")
}
