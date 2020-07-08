package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ca.nait.dmit2504.courseproject.databinding.ActivityAddNoteBinding;


public class AddNoteActivity extends AppCompatActivity {
    private ActivityAddNoteBinding mActivityAddNoteBinding;
    AddNoteListeners handlers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);
        handlers = new AddNoteListeners(this);
        mActivityAddNoteBinding.setClickHandlers(handlers);
    }
    public class AddNoteListeners{
        Context context;
        public AddNoteListeners(Context context){
            this.context = context;
        }
        public void onAddNote(View view){
            Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
            AddNoteActivity.this.startActivity(intent);
        }
    }
}