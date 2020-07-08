package ca.nait.dmit2504.courseproject;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.nait.dmit2504.courseproject.databinding.ListItemBinding;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.NoteViewHolder>{
    private List<Note> notesList;

    public NotesRecyclerAdapter(List<Note> notes){
        notesList = notes;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemBinding itemBinding = ListItemBinding.inflate(layoutInflater, parent, false);
        return new NoteViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notesList != null ? notesList.size(): 0;
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private ListItemBinding mListItemBinding;
        public NoteViewHolder(ListItemBinding listItemBinding){
            super(listItemBinding.getRoot());
            mListItemBinding = listItemBinding;
        }
        public void bind(Note note){
            mListItemBinding.setNote(note);
            mListItemBinding.executePendingBindings();
        }
    }
}
