package com.example.uts_10119270;

import java.util.ArrayList;
import java.util.Date;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class Note {
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA = "noteEdit";

    private int id;
    private String judul, catatan;
    private Date make, deleted;

    public Note(int id, String judul, String catatan, Date make, Date deleted) {
        this.id = id;
        this.judul = judul;
        this.catatan = catatan;
        this.make = make;
        this.deleted = deleted;
    }

    public Note(int id, String judul, String catatan, Date make) {
        this.id = id;
        this.judul = judul;
        this.catatan = catatan;
        this.make=make;
        deleted=null;
    }

    public static Note getNoteForId(int passedNoteId) {
        for (Note note : noteArrayList) {
            if (note.getId() == passedNoteId)
                return note;
        }
        return null;
    }

    public static ArrayList<Note> nonDeletedNotes() {
        ArrayList<Note> nonDeleted = new ArrayList<>();
        for (Note note:noteArrayList) {
            if (note.getDeleted() == null)
                nonDeleted.add(note);
        }
        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Date getMake() {
        return make;
    }

    public void setMake(Date make) {
        this.make = make;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
