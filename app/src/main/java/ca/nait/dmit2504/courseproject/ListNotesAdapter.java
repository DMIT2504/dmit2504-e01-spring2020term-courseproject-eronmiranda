package ca.nait.dmit2504.courseproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;

import ca.nait.dmit2504.courseproject.databinding.ListItemBinding;

public class ListNotesAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Note> mListNotes;
    public ListNotesAdapter(Context context) {
        mContext = context;
        mListNotes = new ArrayList<>();
    }
    public void addItem(Note newNote){
        mListNotes.add(newNote);
        notifyDataSetChanged();
    }
    public void removeItem(Note existingNote){
        mListNotes.remove(existingNote);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mListNotes.size();
    }

    @Override
    public Note getItem(int position) {
        return mListNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListNotes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        @SuppressLint("ViewHolder") ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);

      //  View listNoteView = inflater.inflate(R.layout.list_item,parent,false);

        Note currentNote = mListNotes.get(position);
        binding.listItemTitleTextview.setText(currentNote.getTitle());
        binding.listItemDescriptionTextview.setText(currentNote.getDescription());
        binding.listItemDateTextview.setText(currentNote.getDate());

        return binding.getRoot();
    }
}
