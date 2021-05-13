package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes
{
    @ColumnInfo(name = "notes_text")
     String text;

    @PrimaryKey(autoGenerate = true)
     int id = 0;

    public Notes(@NonNull String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public int getId()
    {
        return id;
    }

}
