package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import ca.nait.dmit2504.courseproject.databinding.ActivityViewNoteBinding;

public class ViewNoteActivity extends AppCompatActivity {
    private ActivityViewNoteBinding mActivityViewNoteBinding;
    private ViewNoteActivityListeners mHandlers;
    private NotesDB mNotesDB;
    private String mNoteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityViewNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_view_note);
        mHandlers = new ViewNoteActivityListeners(this);
        mActivityViewNoteBinding.setClickHandlers(mHandlers);
        mNotesDB = new NotesDB(this);

        if (getIntent() != null && getIntent().hasExtra("noteId")){
            mNoteId = getIntent().getStringExtra("noteId");
        }
        Note note = mNotesDB.findNote(Integer.parseInt(mNoteId));
        mActivityViewNoteBinding.setNote(note);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }
    public class ViewNoteActivityListeners{
        Context context;
        public ViewNoteActivityListeners(Context context){
            this.context = context;
        }
        public void onUpdateClick(View view){
            mNotesDB.updateNote(
                    Integer.parseInt(mNoteId),
                    mActivityViewNoteBinding.viewNoteActivityTitleEditText.getText().toString(),
                    mActivityViewNoteBinding.viewNoteActivityDescriptionEditTextMulti.getText().toString()
            );
            Toast.makeText(ViewNoteActivity.this, "Note updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ViewNoteActivity.this, MainActivity.class);
            ViewNoteActivity.this.startActivity(intent);
        }
        public void onDeleteClick(View view){
            mNotesDB.deleteNote(Integer.parseInt(mNoteId));
            Toast.makeText(ViewNoteActivity.this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ViewNoteActivity.this, MainActivity.class);
            ViewNoteActivity.this.startActivity(intent);
        }
    }
}