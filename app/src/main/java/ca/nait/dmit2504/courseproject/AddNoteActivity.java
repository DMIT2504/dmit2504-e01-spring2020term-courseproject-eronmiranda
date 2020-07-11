package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import ca.nait.dmit2504.courseproject.databinding.ActivityAddNoteBinding;


public class AddNoteActivity extends AppCompatActivity {
    private ActivityAddNoteBinding mActivityAddNoteBinding;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
//    private NotesDB mNotesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);
        AddNoteListeners handlers = new AddNoteListeners(this);
        mActivityAddNoteBinding.setClickHandlers(handlers);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        mNotesDB = new NotesDB(this);


    }
    public class AddNoteListeners{
        Context context;
        public AddNoteListeners(Context context){
            this.context = context;
        }
        public void onAddNote(View view){
            if(
                    !mActivityAddNoteBinding.addNoteActivityTitleEditText.getText().toString().isEmpty()
                    && !mActivityAddNoteBinding.addNoteActivityDescriptionEditTextMulti.getText().toString().isEmpty()
            ) {
//                mNotesDB.createNote(mActivityAddNoteBinding.addNoteActivityTitleEditText.getText().toString(),
////                        mActivityAddNoteBinding.addNoteActivityDescriptionEditTextMulti.getText().toString());
                NoteDatabase noteDB = NoteDatabase.getNoteDatabase(getApplicationContext());
                Note note = new Note(
                        mActivityAddNoteBinding.addNoteActivityTitleEditText.getText().toString(),
                        mActivityAddNoteBinding.addNoteActivityDescriptionEditTextMulti.getText().toString()
                );
                noteDB.noteDAO().insertNote(note);
                playSound(R.raw.swiftly);

                Toast.makeText(AddNoteActivity.this, "New note added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                AddNoteActivity.this.startActivity(intent);
            }
            else{
                Toast.makeText(AddNoteActivity.this, "Please fill title and description", Toast.LENGTH_SHORT).show();
            }
        }
        public void playSound(int id){
            mMediaPlayer = MediaPlayer.create(context, id);
            mMediaPlayer.start();
        }
    }
}