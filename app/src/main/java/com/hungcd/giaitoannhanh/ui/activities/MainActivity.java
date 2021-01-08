package com.hungcd.giaitoannhanh.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.hungcd.giaitoannhanh.R;
import com.hungcd.giaitoannhanh.db.DBHelper;
import com.hungcd.giaitoannhanh.db.DBManager;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();

        rlPlay = findViewById(R.id.rl_play);
        rlPlay.setOnClickListener(v -> {
            startActivity(new Intent(this,PlayingActivity.class));
        });
        Log.e("cuong", "onCreate: "+ DBHelper.CREATE_TABLE);
    }
    private void initDatabase(){
        DBManager dbManager =new DBManager(this);
        dbManager.opeDB();
        dbManager.closeDB();
    }
}