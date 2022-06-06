package com.example.uts_10119270;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class NoteAdapter extends ArrayAdapter {
    public NoteAdapter(Context context, List<Note> notes){
        super(context,0,notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = (Note) getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_cell,parent,false);

        TextView judul = convertView.findViewById(R.id.judulCell);
        TextView isi = convertView.findViewById(R.id.isiCell);

        judul.setText(note.getJudul());
        isi.setText(note.getCatatan());

        return convertView;
    }
}
