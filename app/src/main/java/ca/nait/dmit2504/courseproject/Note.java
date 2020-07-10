package ca.nait.dmit2504.courseproject;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private long mId;

    private String mTitle;
    private String mDescription;
    private String mDate;


    public Note(long id, String title, String description) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDate = getCurrentDate();
    }
    @Ignore
    public Note(String title, String description){
        mTitle = title;
        mDescription = description;
        mDate = getCurrentDate();
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public long getId() {
        return mId;
    }

//    public void setId(long id) {
//        mId = id;
//    }

    public String getTitle() {
        return mTitle;
    }

//    public void setTitle(String title) {
//        mTitle = title;
//    }

    public String getDescription() {
        return mDescription;
    }

//    public void setDescription(String description) {
//        mDescription = description;
//    }

    public String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }
}
