package com.example.android.lernen.main.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alfredchang on 2017-02-21.
 */

public class ExamsDatabaseHelper extends SQLiteOpenHelper {

    // Increment database version each update
    private static int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "exams.db";
    static final String TABLE_NAME = "exams_table";

    // Columns
    private static final String COLUMN_1 = "ID";
    private static final String COLUMN_2 = "EXAM";
    private static final String COLUMN_3 = "TIME";
    private static final String COLUMN_4 = "LOCATION";

    public ExamsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_2 + " TEXT UNIQUE NOT NULL, " +
                COLUMN_3 + " BLOB, " +
                COLUMN_4 + " TEXT" + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        DATABASE_VERSION++;
        onCreate(sqLiteDatabase);
    }

    // Helper method to insert new values
    public boolean insert(String exam, String time, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_2, exam);
        contentValues.put(COLUMN_3, time);
        contentValues.put(COLUMN_4, location);
        long isSuccess = db.insert(TABLE_NAME, null, contentValues);

        return isSuccess > -1;
    }

    // Helper method to display values
    public Cursor display() {
        // TODO
        return null;
    }

    // Helper method to update old values
    public void update(String id, String exam, String time, String location) {
        // TODO
    }

    // Helper method to delete old values
    public void delete(String id) {
        // TODO
    }
}
