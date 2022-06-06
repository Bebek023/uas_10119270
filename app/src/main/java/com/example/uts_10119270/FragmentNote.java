package com.example.uts_10119270;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class FragmentNote extends Fragment {
    private ListView noteListView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_note, container, false);
        Button addNote = (Button) rootView.findViewById(R.id.newNote);
        addNote.setOnClickListener(mButtonClickListener);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initWidgets();
        loadFromDBtoMem();
        setNoteAdapter();
        setOnClickListener();
    }

    private void initWidgets() {
        noteListView= getView().findViewById(R.id.noteListView);
    }

    private void loadFromDBtoMem() {
        SQLiteManager sqLiteManager = SQLiteManager.DBInstance(getActivity());
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter() {
        ////................................This one vvvvv ///////
        NoteAdapter noteAdapter = new NoteAdapter(getContext(),Note.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }

    private void setOnClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Note selectedNote = (Note) noteListView.getItemAtPosition(pos);
                Intent editNoteIntent = new Intent(getActivity().getApplicationContext(), NoteDetailActivity.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA,selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

    private View.OnClickListener mButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent newNoteIntent = new Intent(getActivity(), NoteDetailActivity.class);
            startActivity(newNoteIntent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        setNoteAdapter();
    }

    //    public void newNote (View view) {
//        Intent newNoteIntent = new Intent(getActivity(), NoteDetailActivity.class);
//        startActivity(newNoteIntent);
//    }
}
