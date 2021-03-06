package com.hungcd.giaitoannhanh.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import static com.hungcd.giaitoannhanh.db.ScoreTable.Score.COLUMN_SCORE;
import static com.hungcd.giaitoannhanh.db.ScoreTable.Score.COLUMN_TIME;
import static com.hungcd.giaitoannhanh.db.ScoreTable.Score.TABLE_NAME;

public class DBManager {

    private static final String TAG = "DBManager";
    //TODO add SQLiteException
    private Context context;
    private static SQLiteDatabase db;
    private DBHelper mDBHelper;

    public DBManager(Context context) {
        this.context = context;
    }

    public DBManager(SQLiteDatabase db) {
        DBManager.db = db;
    }

    /*
     * DB IO
     */
    public void opeDB() throws SQLiteException {
        mDBHelper = new DBHelper(context);
        // Gets the data repository in write mode
        db = mDBHelper.getWritableDatabase();

    }

    public boolean isOpen() {
        return db.isOpen();
    }

    public void closeDB() {
        mDBHelper.close();
    }

    public void beginTransaction() {
        db.beginTransaction();
    }

    public void setTransactionSuccessful() {
        db.setTransactionSuccessful();
    }

    public void endTransaction() {
        db.endTransaction();
    }

    private ContentValues createScoreValue(String time, int score) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_SCORE, score);
        return values;
    }

    public long insertScore(long time, int score) {
        return db.insert(TABLE_NAME, null, createScoreValue(String.valueOf(time), score));
    }

    public Cursor queryBestScore() {
        String select = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ScoreTable.Score._ID + " ASC";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor != null && cursor.moveToFirst()) {
            return cursor;
        }
        return null;
    }

    public Cursor queryScore() {
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
}
