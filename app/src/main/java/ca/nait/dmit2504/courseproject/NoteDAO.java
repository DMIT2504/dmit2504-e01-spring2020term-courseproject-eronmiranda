package ca.nait.dmit2504.courseproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import android.provider.BaseColumns;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM note")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE mId IN (:id)")
    Note findById(long id);
    @Update
    void updateNote(Note note);

    @Insert
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);


}
