package com.xela.notelist.model;

public class Note {

    private String id,note_name;

    public Note(String id, String note_name) {
        this.id = id;
        this.note_name = note_name;
    }

    public String getId() {
        return id;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }
}
