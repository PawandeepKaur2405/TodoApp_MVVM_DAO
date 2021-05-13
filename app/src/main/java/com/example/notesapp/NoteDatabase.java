package com.example.notesapp;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class} , version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase
{
    public abstract NoteDao noteDao();

    private static volatile NoteDatabase INSTANCE;
    private static final int NO_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NO_OF_THREADS);

    //singleton of database
    static NoteDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (NoteDatabase.class)   //no other thread could access it at single time(thread safe)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class , "notes_database")
                            .build();
                }
            }
        }

        return INSTANCE;

    }
}
