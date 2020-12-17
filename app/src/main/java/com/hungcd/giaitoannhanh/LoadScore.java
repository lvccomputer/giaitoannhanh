package com.hungcd.giaitoannhanh;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.hungcd.giaitoannhanh.db.DBManager;
import com.hungcd.giaitoannhanh.math.Score;

import java.util.ArrayList;

public class LoadScore extends AsyncTask<Void, Void, ArrayList<Score>> {
    public interface OnLoad {
        void onLoadFinish(ArrayList<Score> scores);
    }

    private OnLoad onLoad;
    private Context context;

    public LoadScore(Context context, OnLoad onLoad) {
        this.onLoad = onLoad;
        this.context = context;
    }

    @Override
    protected ArrayList<Score> doInBackground(Void... voids) {
        ArrayList<Score> scoreArrayList = new ArrayList<>();
        DBManager dbManager = new DBManager(context);
        Cursor cursor = dbManager.queryScore();
        if (cursor.getCount() > 0) {
            for (int index = 0; index < cursor.getCount(); index++) {
                Score score = new Score();
                score.setId(cursor.getInt(0));
                score.setTime(cursor.getLong(1));
                score.setScore(cursor.getInt(2));
                scoreArrayList.add(score);
                cursor.moveToNext();
            }

        }
        return scoreArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Score> scores) {
        super.onPostExecute(scores);
        if (onLoad != null) onLoad.onLoadFinish(scores);
    }
}