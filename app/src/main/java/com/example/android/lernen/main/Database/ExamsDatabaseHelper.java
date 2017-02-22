package com.example.android.lernen.main.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alfredchang on 2017-02-21.
 */

public class ExamsDatabaseHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "exams.db";

    public ExamsDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
