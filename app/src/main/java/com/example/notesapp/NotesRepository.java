package com.example.notesapp;

import android.app.Application;
import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NotesRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Notes>> mAllNotes;

    NotesRepository(Application context)
    {
       NoteDatabase db = NoteDatabase.getDatabase(context);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    LiveData<List<Notes>> getAllNotes()
    {
        return mAllNotes;
    }

    void insert(Notes note)
    {
        NoteDatabase.databaseWriteExecutor.execute( ()  -> {
            mNoteDao.insert(note);
        });
    }

    void delete(Notes note)
    {
        NoteDatabase.databaseWriteExecutor.execute( () -> {
            mNoteDao.delete(note);
        });
    }


}
