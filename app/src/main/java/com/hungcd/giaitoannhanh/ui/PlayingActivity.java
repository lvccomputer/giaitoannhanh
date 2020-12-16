package com.hungcd.giaitoannhanh.ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hungcd.giaitoannhanh.R;
import com.hungcd.giaitoannhanh.db.DBManager;
import com.hungcd.giaitoannhanh.math.MathLogic;

public class PlayingActivity extends AppCompatActivity {
    private DBManager dbManager;
    private CountDownTimer countDownTimer;
    private MathLogic mathL;
    private TextView tvQuestion, tvScore, tvTimer;
    private ImageView imgTrue, imgFalse;
    private int score = 0, total = 9999999;
    private long startTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        bindViews();
        actions();
    }

    private void bindViews() {
        dbManager = new DBManager(this);
        dbManager.opeDB();
        startTime = System.currentTimeMillis();
        tvQuestion = findViewById(R.id.tv_question);
        tvScore = findViewById(R.id.tv_score);
        imgTrue = findViewById(R.id.img_true);
        imgFalse = findViewById(R.id.img_false);
        mathL = new MathLogic();
        tvQuestion.setText(mathL.printEquation());
    }

    private void actions() {
        imgTrue.setOnClickListener(v -> {
            if (mathL.correct) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                score++;
                setScore(score);
                countDownTimer.start();
                mathL = new MathLogic();
                tvQuestion.setText(mathL.printEquation());
            } else {
                Toast.makeText(this, "Wrong~", Toast.LENGTH_SHORT).show();
            }

        });
        imgFalse.setOnClickListener(v -> {
            if (!mathL.correct) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                score++;
                setScore(score);
                countDownTimer.start();
                mathL = new MathLogic();
                tvQuestion.setText(mathL.printEquation());
            } else {
                Toast.makeText(this, "Wrong~", Toast.LENGTH_SHORT).show();
            }

        });
        countDownTimer = new CountDownTimer(3000, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("0" + millisUntilFinished / 1000);
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                Toast.makeText(PlayingActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
            }
        };
    }

    //update score when answer question
    private void setScore(int score) {
        tvScore.setText("Score: " + score);
    }

    //insert score to sqlite
    private void saveScore(DBManager dbManager, long time, int score) {
        dbManager.insertScore(time, score);
    }

    private int bestScore(DBManager dbManager) {
        Cursor cursor = dbManager.queryBestScore();
        return cursor.getInt(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}
