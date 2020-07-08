package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

import ca.nait.dmit2504.courseproject.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    // data binding declaration
    ActivityMainBinding mActivityMainBinding;
    MainActivityListeners handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        NotesDB notesDB = new NotesDB(this);

        // Initialize (construct) main activity listeners.
        handlers = new MainActivityListeners(this);
        // Pass click handlers internal class.
        mActivityMainBinding.setClickHandlers(handlers);
        mActivityMainBinding.setNotesDB(notesDB);

        RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(MainActivity.this, "Position " + position, Toast.LENGTH_SHORT).show();
        };

        RecyclerView rvNotes = mActivityMainBinding.activityMainRecyclerview;

        ArrayList<Note> notes = notesDB.getAllNotesPOJO();

        NotesAdapter adapter = new NotesAdapter(notes, listener);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        rvNotes.setHasFixedSize(true);
        rvNotes.addItemDecoration(itemDecoration);
        rvNotes.setAdapter(adapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));

    }

    public class MainActivityListeners{
        Context context;
        public MainActivityListeners(Context context){
            this.context = context;
        }
        public void onAddClick(View view){
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }
}