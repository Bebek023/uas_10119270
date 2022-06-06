package com.example.uts_10119270;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class NoteDetailActivity extends AppCompatActivity {
    private EditText titleEditText, descEditText;
    private Button deleteButton;
    private Note selectedNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.fragment_note_detail);
        initWidgets();
        checkForEditNote();
    }

    private void initWidgets() {
        titleEditText = findViewById(R.id.judulNote);
        descEditText = findViewById(R.id.isiNote);
        deleteButton = findViewById(R.id.deleteNoteButton);
    }

    private void checkForEditNote() {
        Intent prevIntent = getIntent();
        int passedNoteId = prevIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
        selectedNote = Note.getNoteForId(passedNoteId);

        if (selectedNote != null) {
            titleEditText.setText(selectedNote.getJudul());
            descEditText.setText(selectedNote.getCatatan());
        }
        else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveNote(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.DBInstance(this);

        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());
        Date make = Calendar.getInstance().getTime();

        if (selectedNote == null) {
            int id = Note.noteArrayList.size();
            Note newNote = new Note(id, title, desc, make);
            Note.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDB(newNote);
        }
        else {
            selectedNote.setJudul(title);
            selectedNote.setCatatan(desc);
            sqLiteManager.updateNoteInDB(selectedNote);
        }
        finish();
    }

    public void deleteNote(View view) {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.DBInstance(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }
}
