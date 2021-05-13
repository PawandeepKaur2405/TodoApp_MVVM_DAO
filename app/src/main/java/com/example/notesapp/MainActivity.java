package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  INotesRVAdapter{

    private NoteViewModel mNoteViewModel;
    private RecyclerView mRecyclerView;
    private NotesAdapter mNotesAdapter;
    private EditText notesText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesText = findViewById(R.id.notesText);
        mRecyclerView = findViewById(R.id.notesRecyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mNotesAdapter = new NotesAdapter(this ,this );
        mRecyclerView.setAdapter(mNotesAdapter);

        //to create object of noteViewModel and attach it to our activity/fragment.
        mNoteViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);

        //to keep updating data changes
        mNoteViewModel.getAllNotes().observe(this,
                new Observer<List<Notes>>() {
                    @Override
                    public void onChanged(List<Notes> notes) {
                        mNotesAdapter.updateNotes(notes);
                    }
                });

    }

    @Override
    public void notesItemClicked(Notes notes)
    {
        mNoteViewModel.delete(notes);
        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
    }

    public void saveMyNote(View view)
    {
        String myText = notesText.getText().toString();

        if(!myText.isEmpty())
        {
            mNoteViewModel.insert(new Notes(myText));
        }

        notesText.setText("");

        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();

    }
}