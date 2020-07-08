package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;


import ca.nait.dmit2504.courseproject.databinding.ActivityMainBinding;
import ca.nait.dmit2504.courseproject.databinding.ListItemBinding;


public class MainActivity extends AppCompatActivity {
    // data binding declaration
    ActivityMainBinding mActivityMainBinding;
    MainActivityListeners handlers;
    ListItemBinding mListItemBinding;
    private NotesDB mNotesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mNotesDB = new NotesDB(this);
        handlers = new MainActivityListeners(this);
        mActivityMainBinding.setClickHandlers(handlers);
    }

    private void bindListNotesView(){
        Cursor dbCursor = mNotesDB.getAllNotes();

        // Define an array of columns names used by the cursor
        String[] fromFields = {
                NotesDB.TABLE_NOTE_COLUMN_TITLE,
                NotesDB.TABLE_NOTE_COLUMN_DESCRIPTION,
                NotesDB.TABLE_NOTE_COLUMN_DATE
        };
        // Define an array of resource ids in the listview item layout
        int[] toViews = new int[] {
                mListItemBinding.listItemTitleTextview.getId(),
                mListItemBinding.listItemDescriptionTextview.getId(),
                mListItemBinding.listItemDateTextview.getId()
        };
        // Create a SimpleCursorAdapter for the ListView
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                mListItemBinding.getRoot().getId(),
                dbCursor,
                fromFields,
                toViews,
                0);
        mActivityMainBinding.activityMainNotesListview.setAdapter(cursorAdapter);
    }
    // this internal class handles all the click events for main activity.
    public class MainActivityListeners{
        Context context;
        public MainActivityListeners(Context context){
            this.context = context;
        }
        public void onAddNote(View view){
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }
}