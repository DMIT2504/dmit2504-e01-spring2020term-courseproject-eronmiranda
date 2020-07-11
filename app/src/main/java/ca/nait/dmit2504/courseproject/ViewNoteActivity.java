package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import ca.nait.dmit2504.courseproject.databinding.ActivityViewNoteBinding;

public class ViewNoteActivity extends AppCompatActivity {
    private ActivityViewNoteBinding mActivityViewNoteBinding;
    //    private NotesDB mNotesDB;
    private NoteDatabase noteDB;
    private String mNoteId;
    private Note mNote;
    private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityViewNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_view_note);
        ViewNoteActivityListeners handlers = new ViewNoteActivityListeners(this);
        mActivityViewNoteBinding.setClickHandlers(handlers);
//        mNotesDB = new NotesDB(this);

        if (getIntent() != null && getIntent().hasExtra("noteId")){
            mNoteId = getIntent().getStringExtra("noteId");
        }
        noteDB = NoteDatabase.getNoteDatabase(getApplicationContext());
        mNote = noteDB.noteDAO().findById(Integer.parseInt(mNoteId));
//        Note note = mNotesDB.findNote(Integer.parseInt(mNoteId));
        mActivityViewNoteBinding.setNote(mNote);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }
    public class ViewNoteActivityListeners{
        Context context;
        public ViewNoteActivityListeners(Context context){
            this.context = context;
        }

        public void onUpdateClick(View view){
            Note editedNote = new Note(
                    Integer.parseInt(mNoteId),
                    mActivityViewNoteBinding.viewNoteActivityTitleEditText.getText().toString(),
                    mActivityViewNoteBinding.viewNoteActivityDescriptionEditTextMulti.getText().toString()
            );
            noteDB.noteDAO().updateNote(editedNote);
            playSound(R.raw.cake);
            Toast.makeText(ViewNoteActivity.this, "Note updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ViewNoteActivity.this, MainActivity.class);
            ViewNoteActivity.this.startActivity(intent);
        }
        public void onDeleteClick(View view){
            noteDB.noteDAO().deleteNote(mNote);
            playSound(R.raw.juntos);

//            mNotesDB.deleteNote(Integer.parseInt(mNoteId));
            Toast.makeText(ViewNoteActivity.this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ViewNoteActivity.this, MainActivity.class);
            ViewNoteActivity.this.startActivity(intent);
        }
        public void playSound(int id){
            mMediaPlayer = MediaPlayer.create(context, id);
            mMediaPlayer.start();
        }
    }
}