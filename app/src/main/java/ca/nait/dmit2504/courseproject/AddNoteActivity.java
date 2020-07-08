package ca.nait.dmit2504.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import ca.nait.dmit2504.courseproject.databinding.ActivityAddNoteBinding;


public class AddNoteActivity extends AppCompatActivity {
    private ActivityAddNoteBinding mActivityAddNoteBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);
    }
}