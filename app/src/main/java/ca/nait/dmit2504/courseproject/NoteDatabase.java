package ca.nait.dmit2504.courseproject;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 8, exportSchema = false)//add exportSchema to stop a debug warning
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDAO noteDAO();

    private static NoteDatabase sNoteDatabase;

    static NoteDatabase getNoteDatabase(Context context){
        if (sNoteDatabase == null){
            synchronized (NoteDatabase.class){
                if(sNoteDatabase == null){
                    sNoteDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return sNoteDatabase;
    }


}
