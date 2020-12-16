package com.hungcd.giaitoannhanh.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.hungcd.giaitoannhanh.R;
import com.hungcd.giaitoannhanh.db.DBManager;
import com.hungcd.giaitoannhanh.math.RandomMath;

public class PlayingActivity extends AppCompatActivity {
    private DBManager dbManager;
    private CountDownTimer countDownTimer;
    private RandomMath randomMathL;
    private TextView tvQuestion, tvScore, tvTimer;
    private ImageView imgTrue, imgFalse;
    private int score = 0, total = 9999999;
    private long startTime = 0;

    //replay init
    private LinearLayout lnAnswer, lnReplay;
    private CardView cvPlay,cvShare,cvMore;
    private TextView tvCurrentScore,tvBestScore;


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
        tvTimer = findViewById(R.id.tv_countdown_timer);
        lnAnswer = findViewById(R.id.ln_answer);
        lnReplay = findViewById(R.id.ln_replay);
        cvPlay = findViewById(R.id.cv_play);
        cvMore =findViewById(R.id.cv_more);
        cvShare = findViewById(R.id.cv_share);
        tvCurrentScore = findViewById(R.id.tv_current_score);
        tvBestScore = findViewById(R.id.tv_best_score);

        randomMathL = new RandomMath();
        tvQuestion.setText(randomMathL.printEquation());
    }

    private void actions() {
        imgTrue.setOnClickListener(v -> {
            if (randomMathL.correct) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                score++;
                setScore(score);
                countDownTimer.start();
                randomMathL = new RandomMath();
                tvQuestion.setText(randomMathL.printEquation());
            } else {
                Toast.makeText(this, "Wrong~", Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
                if (score>0){
                    saveScore(dbManager,startTime,score);
                }
                showReplayLayout();
                hideAnswerLayout();
            }

        });
        imgFalse.setOnClickListener(v -> {
            if (!randomMathL.correct) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                score++;
                setScore(score);
                countDownTimer.start();
                randomMathL = new RandomMath();
                tvQuestion.setText(randomMathL.printEquation());
            } else {
                Toast.makeText(this, "Wrong~", Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
                if (score>0){
                    saveScore(dbManager,startTime,score);
                }
                showReplayLayout();
                hideAnswerLayout();
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
                if (score>0){
                    saveScore(dbManager,startTime,score);
                }
                showReplayLayout();
                hideAnswerLayout();
            }
        };
        cvPlay.setOnClickListener(v -> {
            showAnswerLayout();
            hideReplayLayout();
            countDownTimer.start();
            randomMathL = new RandomMath();
            tvQuestion.setText(randomMathL.printEquation());
            score=0;
            startTime= System.currentTimeMillis();
        });
        cvShare.setOnClickListener(v -> {
            /*Create an ACTION_SEND Intent*/
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            /*This will be the actual content you wish you share.*/
            String shareBody = "My score in giai toan nhanh: " + score;
            /*The type of the content is text, obviously.*/
            intent.setType("text/plain");
            /*Applying information and Body.*/
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            /*Fire!*/
            startActivity(Intent.createChooser(intent,"choose"));
        });
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
        if (cursor!=null)
        return cursor.getInt(2);
        else return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }

    private void showAnswerLayout() {
        if (lnAnswer.getVisibility() == View.GONE) lnAnswer.setVisibility(View.VISIBLE);
    }
    private void hideAnswerLayout(){
        if (lnAnswer.getVisibility()==View.VISIBLE) lnAnswer.setVisibility(View.GONE);
    }
    private void showReplayLayout(){
        if (lnReplay.getVisibility() == View.GONE) lnReplay.setVisibility(View.VISIBLE);
        tvCurrentScore.setText(score+"");
        tvBestScore.setText(bestScore(dbManager)+"");
    }
    private void hideReplayLayout(){
        if (lnReplay.getVisibility() ==View.VISIBLE) lnReplay.setVisibility(View.GONE);
    }
}
