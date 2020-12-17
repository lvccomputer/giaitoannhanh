package com.hungcd.giaitoannhanh.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hungcd.giaitoannhanh.LoadScore;
import com.hungcd.giaitoannhanh.R;
import com.hungcd.giaitoannhanh.adapters.ScoreAdapter;
import com.hungcd.giaitoannhanh.db.DBManager;
import com.hungcd.giaitoannhanh.math.Score;

import java.util.ArrayList;

public class ScoreDialog extends Dialog {
    private RecyclerView rcvScore;
    private ImageView imgBack;
    private TextView tvNone;

    private ScoreAdapter scoreAdapter;
    private ArrayList<Score> scoreArrayList;
    private Context context;


    public ScoreDialog(@NonNull Context context) {
        super(context, R.style.Dialog_Theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_score);
        bindViews();
        initScore();
    }

    private void bindViews() {
        scoreArrayList = new ArrayList<>();
        rcvScore = findViewById(R.id.rcv_score);
        imgBack = findViewById(R.id.img_back);
        tvNone = findViewById(R.id.tv_none);
        imgBack.setOnClickListener(view -> dismiss());

        scoreAdapter = new ScoreAdapter(scoreArrayList, context);
        rcvScore.setLayoutManager(new LinearLayoutManager(context));
        rcvScore.setAdapter(scoreAdapter);
    }

    private void initScore() {
        scoreArrayList.clear();
        LoadScore score = new LoadScore(context, scores -> {
            scoreArrayList.addAll(scores);
            if (scoreArrayList.size() > 0) {
                tvNone.setVisibility(View.GONE);
                rcvScore.setVisibility(View.VISIBLE);
            } else {
                tvNone.setVisibility(View.VISIBLE);
                rcvScore.setVisibility(View.GONE);
            }
            scoreAdapter.notifyDataSetChanged();
        });
        score.execute();
    }

}