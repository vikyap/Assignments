package au.edu.jcu.cp3406.quizgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HighScoresDatabaseHelper extends SQLiteOpenHelper {
// code referenced from Android Developers website
// https://developer.android.com/training/data-storage/sqlite

    private static final String DB_NAME = "HighScores";
    private static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "HighScores";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    public static final String KEY_ID = "id";


    public HighScoresDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table with necessary columns (name, score)
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT," +
                COLUMN_SCORE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addScore(String name, String score) {
        // add the name and score into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues scoreValues = new ContentValues();
        scoreValues.put(COLUMN_NAME, name);
        scoreValues.put(COLUMN_SCORE, score);
        db.insert(TABLE_NAME, null, scoreValues);
    }


    public Cursor getScore() {
        // query all the information from the database and add them into cursor object
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }


}


