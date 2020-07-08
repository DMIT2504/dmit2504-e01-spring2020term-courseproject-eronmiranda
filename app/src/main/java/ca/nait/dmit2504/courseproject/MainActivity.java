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

import java.util.List;

import ca.nait.dmit2504.courseproject.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    // data binding declaration
    private ActivityMainBinding mActivityMainBinding;
    private NotesDB notesDB;
    private RecyclerViewClickListener mListener;
    private RecyclerView.ItemDecoration mItemDecoration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        notesDB = new NotesDB(this);

        // Initialize (construct) main activity listeners.
        MainActivityListeners handlers = new MainActivityListeners(this);
        // Pass click handlers internal class.
        mActivityMainBinding.setClickHandlers(handlers);

        mListener = this::getListener;

        // Creates divider for every item in recyclerview
        mItemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        setupRecyclerView();
    }

    public void getListener (View view, Note note){
        Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
        intent.putExtra("noteId", String.valueOf(note.getId()));
        MainActivity.this.startActivity(intent);
    }

    public void setupRecyclerView(){
        RecyclerView recyclerView = mActivityMainBinding.activityMainRecyclerview;
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Note> movieList = notesDB.getAllNotesPOJO();

        NotesRecyclerAdapter adapter = new NotesRecyclerAdapter(movieList,mListener);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(mItemDecoration);
        recyclerView.setAdapter(adapter);
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