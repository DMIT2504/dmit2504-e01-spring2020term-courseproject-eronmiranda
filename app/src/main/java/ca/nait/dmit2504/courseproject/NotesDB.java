package ca.nait.dmit2504.courseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NotesDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "NotesDB.db";

    public static final String TABLE_NOTE = "note";
    public static final String TABLE_NOTE_COLUMN_ID = BaseColumns._ID;
    public static final String TABLE_NOTE_COLUMN_TITLE = "title";
    public static final String TABLE_NOTE_COLUMN_DESCRIPTION = "description";
    public static final String TABLE_NOTE_COLUMN_DATE = "dateCreated";

    public NotesDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTE
                + "(" + TABLE_NOTE_COLUMN_ID + " INTEGER PRIMARY KEY, "
                + TABLE_NOTE_COLUMN_TITLE + " TEXT,"
                + TABLE_NOTE_COLUMN_DESCRIPTION + " TEXT, "
                + TABLE_NOTE_COLUMN_DATE + ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE + ";");
        onCreate(db);
    }
    public String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }

    public long createNote(String title, String description){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_NOTE_COLUMN_TITLE, title);
        values.put(TABLE_NOTE_COLUMN_DESCRIPTION, description);
        values.put(TABLE_NOTE_COLUMN_DATE, getCurrentDate());

        return db.insert(TABLE_NOTE, null, values);
    }

    public int updateNote(long id, String title, String description){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_NOTE_COLUMN_ID,id);
        values.put(TABLE_NOTE_COLUMN_TITLE, title);
        values.put(TABLE_NOTE_COLUMN_DESCRIPTION, description);
        values.put(TABLE_NOTE_COLUMN_DATE,getCurrentDate());
        return db.update(TABLE_NOTE, values, TABLE_NOTE_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getAllNotes(){
        SQLiteDatabase db = getReadableDatabase();
        String queryStatement = "SELECT " + TABLE_NOTE_COLUMN_ID + ", "
                + TABLE_NOTE_COLUMN_TITLE + ", "
                + TABLE_NOTE_COLUMN_DESCRIPTION + ", "
                + TABLE_NOTE_COLUMN_DATE
                + " FROM " + TABLE_NOTE
                + " ORDER BY " + TABLE_NOTE_COLUMN_ID + " DESC";
        return db.rawQuery(queryStatement, null);
    }

    public ArrayList<Note> getAllNotesPOJO() {
        ArrayList<Note> notes = new ArrayList<>();
        Cursor resultListCursor = getAllNotes();
        while (resultListCursor.moveToNext()) {
            Note singleNote = new Note();
            singleNote.setId(resultListCursor.getInt(resultListCursor.getColumnIndex(TABLE_NOTE_COLUMN_ID)));
            singleNote.setTitle(resultListCursor.getString(resultListCursor.getColumnIndex(TABLE_NOTE_COLUMN_TITLE)));
            singleNote.setDescription((resultListCursor.getString((resultListCursor.getColumnIndex(TABLE_NOTE_COLUMN_DESCRIPTION)))));
            singleNote.setDate(resultListCursor.getString(resultListCursor.getColumnIndex(TABLE_NOTE_COLUMN_DATE)));
            notes.add(singleNote);
        }
        return  notes;
    }

    public Note findNote(long id) {
        // Get a readable database
        SQLiteDatabase db = getReadableDatabase();

        // Construct a SQL query statement
        String queryStatement = "SELECT " + BaseColumns._ID + ", "
                + TABLE_NOTE_COLUMN_TITLE + ", "
                + TABLE_NOTE_COLUMN_DESCRIPTION + ", "
                + TABLE_NOTE_COLUMN_DATE
                + " FROM " + TABLE_NOTE
                + " WHERE " + BaseColumns._ID + "= ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor singleResultCursor = db.rawQuery(queryStatement, selectionArgs);

        Note foundNote = null;
        if (singleResultCursor.getCount() == 1) {
            singleResultCursor.moveToFirst();

            foundNote = new Note();

            foundNote.setId(singleResultCursor.getInt(singleResultCursor.getColumnIndex(TABLE_NOTE_COLUMN_ID)));
            foundNote.setTitle(singleResultCursor.getString(singleResultCursor.getColumnIndex(TABLE_NOTE_COLUMN_TITLE)));
            foundNote.setDescription(singleResultCursor.getString(singleResultCursor.getColumnIndex(TABLE_NOTE_COLUMN_DESCRIPTION)));
            foundNote.setDate(singleResultCursor.getString(singleResultCursor.getColumnIndex(TABLE_NOTE_COLUMN_DATE)));
        }
        singleResultCursor.close();

        return foundNote;
    }

    public int deleteNote(long id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NOTE, BaseColumns._ID + " = ?", new String[] {String.valueOf(id)});
    }

}
