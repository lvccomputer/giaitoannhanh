package com.hungcd.giaitoannhanh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.provider.BaseColumns._ID;
import static com.hungcd.giaitoannhanh.db.ScoreTable.Score.COLUMN_SCORE;
import static com.hungcd.giaitoannhanh.db.ScoreTable.Score.COLUMN_TIME;
import static com.hungcd.giaitoannhanh.db.ScoreTable.Score.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    private static final String DATABASE_NAME = "reminder.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_TABLE =
            "create table " + TABLE_NAME + " ( " +
                    _ID + INTEGER_TYPE + " primary key " + COMMA_SEP +
                    COLUMN_TIME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_SCORE + INTEGER_TYPE + " )";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
