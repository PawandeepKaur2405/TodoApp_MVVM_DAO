package com.example.notesapp;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private final LiveData<List<Notes>> mAllNotes;
    private NotesRepository mNotesRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mNotesRepository = new NotesRepository(application);
        mAllNotes = mNotesRepository.getAllNotes();
    }

    void insert(Notes notes)
    {
        mNotesRepository.insert(notes);
    }

    void delete(Notes notes)
    {
        mNotesRepository.delete(notes);
    }

    LiveData<List<Notes>> getAllNotes()
    {
        return mAllNotes;
    }
}
