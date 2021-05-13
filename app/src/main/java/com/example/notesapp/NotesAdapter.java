package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesHolder> {

    private List<Notes> allNotes = new ArrayList<>();
    private Context mContext;
    private INotesRVAdapter listener;

    NotesAdapter(Context context, INotesRVAdapter listener)
    {
        mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.item_note , parent , false);
      NotesHolder notesHolder = new NotesHolder(view);

      notesHolder.deleteNote.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              listener.notesItemClicked(allNotes.get(notesHolder.getAdapterPosition()));
          }
      });

       return notesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {

        Notes currentNote = allNotes.get(position);
        holder.notesText.setText(currentNote.getText());

    }

    @Override
    public int getItemCount() {
       return allNotes.size();
    }

    void updateNotes(List<Notes> list)
    {
        allNotes.clear();;
        allNotes.addAll(list);
        notifyDataSetChanged();
    }
}

class NotesHolder extends RecyclerView.ViewHolder
{
    public NotesHolder(@NonNull View itemView) {
        super(itemView);
    }

    TextView notesText = itemView.findViewById(R.id.savedNotes);
    ImageView deleteNote = itemView.findViewById(R.id.deleteNote);
}

interface INotesRVAdapter
{
    void notesItemClicked(Notes notes);
}
