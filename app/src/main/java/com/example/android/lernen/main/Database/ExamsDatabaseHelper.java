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
    private static int EXAMS_DB_VERSION = 1;
    static final String EXAMS_DB = "exams.db";
    static final String EXAMS_TABLE = "exams_table";

    // Columns
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "EXAM";
    public static final String COLUMN_3 = "TIME";
    public static final String COLUMN_4 = "LOCATION";

    public ExamsDatabaseHelper(Context context) {
        super(context, EXAMS_DB, null, EXAMS_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + EXAMS_TABLE + " (" +
                COLUMN_1 + " INTEGER AUTOINCREMENT PRIMARY KEY, " +
                COLUMN_2 + " TEXT NOT NULL, " +
                COLUMN_3 + " BLOB, " +
                COLUMN_4 + " TEXT" + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXAMS_TABLE);
        EXAMS_DB_VERSION++;
        onCreate(sqLiteDatabase);
    }

    // Helper method to insert new values
    public boolean insert(String exam, String time, String location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, exam);
        contentValues.put(COLUMN_3, time);
        contentValues.put(COLUMN_4, location);

        long isSuccess = db.insert(EXAMS_TABLE, null, contentValues);
        return isSuccess > -1;
    }

    // Helper method to display values
    public Cursor query() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(EXAMS_TABLE, null, null, null, null, null, null);
        return cursor;
    }

    // Helper method to update old values
    public int update(String id, String exam, String time, String location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1, id);
        contentValues.put(COLUMN_2, exam);
        contentValues.put(COLUMN_3, time);
        contentValues.put(COLUMN_4, location);

        int numRowsUpdated = db.update(EXAMS_TABLE, contentValues, COLUMN_1 + " = ?", new String[]{id});
        return numRowsUpdated;
    }

    // Helper method to delete old values
    public int delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numRowsDeleted = db.delete(EXAMS_TABLE, "SELECT * FROM " + EXAMS_TABLE + " WHERE " + COLUMN_1 + " LIKE " + id,
                null);
        return numRowsDeleted;
    }
}
