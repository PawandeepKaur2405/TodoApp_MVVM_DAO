package com.example.notesapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface NoteDao
{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Delete
    void delete(Notes notes);

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    LiveData<List<Notes>> getAllNotes();
        
}
