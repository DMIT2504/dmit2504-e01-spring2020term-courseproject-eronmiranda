package ca.nait.dmit2504.courseproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.nait.dmit2504.courseproject.databinding.ListItemBinding;

// Recycler View Adapter.
public class NotesAdapter
        extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{
    private ListItemBinding mListItemBinding;
    private List<Note> mNotes;
    private RecyclerViewClickListener mListener;
    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView dateTextView;
        private RecyclerViewClickListener mListener;
        // Store a member variable for the contacts


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.list_item_title_textview);
            descriptionTextView = (TextView) itemView.findViewById(R.id.list_item_description_textview);
            dateTextView = (TextView) itemView.findViewById(R.id.list_item_date_textview);

            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }

    // Pass in the notes array into the constructor
    public NotesAdapter(List<Note> notes, RecyclerViewClickListener listener) {
        mNotes = notes;
        mListener = listener;
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View noteView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(noteView, mListener);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Note note = mNotes.get(position);

        // Set item views based on your views and data model
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView dateTextView = holder.dateTextView;
        titleTextView.setText(note.getTitle());
        descriptionTextView.setText(note.getDescription());
        dateTextView.setText(note.getDate());
    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
