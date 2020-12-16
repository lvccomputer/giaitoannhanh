package com.hungcd.giaitoannhanh.db;

import android.provider.BaseColumns;

public class ScoreTable {
    public static class Score implements BaseColumns {
        public static final String TABLE_NAME = "_tb_score";
        public static final String COLUMN_TIME= "_time";
        public static final String COLUMN_SCORE = "_score";
    }
}
