package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ca.nait.dmit2504.courseproject.databinding.ActivityAddNoteBinding;
import ca.nait.dmit2504.courseproject.databinding.ActivityMainBinding;


public class AddNoteActivity extends AppCompatActivity {
    ActivityMainBinding mActivityMainBinding;
    ActivityAddNoteBinding mActivityAddNoteBinding;
    AddNoteListeners handlers;
    private NotesDB mNotesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);
        handlers = new AddNoteListeners(this);
        mActivityAddNoteBinding.setClickHandlers(handlers);

        mNotesDB = new NotesDB(this);


    }
    public class AddNoteListeners{
        Context context;
        public AddNoteListeners(Context context){
            this.context = context;
        }
        public void onAddNote(View view){
            mNotesDB.createNote(mActivityAddNoteBinding.addNoteActivityTitleEditText.getText().toString(),
                    mActivityAddNoteBinding.addNoteActivityDescriptionEditTextMulti.getText().toString());
            Toast.makeText(AddNoteActivity.this, "New note added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
            AddNoteActivity.this.startActivity(intent);
        }
    }
}