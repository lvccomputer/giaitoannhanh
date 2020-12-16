package com.hungcd.giaitoannhanh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hungcd.giaitoannhanh.R;
import com.hungcd.giaitoannhanh.UserConstant;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUser,edtPassword;
    private TextView tvLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        action();
    }
    private void bindViews(){
        edtUser = findViewById(R.id.edt_user);
        edtPassword = findViewById(R.id.edt_password);
        tvLogin = findViewById(R.id.tv_login);
    }
    private void action(){
        tvLogin.setOnClickListener(v -> {
            if (isUser() && isPassword()){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    private boolean isUser(){
        if (!TextUtils.isEmpty(edtUser.getText().toString())){
            if (edtUser.getText().toString().toLowerCase().contains(UserConstant.USER.toLowerCase())){
                return true;
            }else {
                Toast.makeText(this, "User wrong!", Toast.LENGTH_SHORT).show();
            }
        }else Toast.makeText(this, "User is empty! Try again", Toast.LENGTH_SHORT).show();
        return false;
    }
    private boolean isPassword(){
        if (!TextUtils.isEmpty(edtPassword.getText().toString())){
            if (edtPassword.getText().toString().toLowerCase().contains(UserConstant.PASSWORD.toLowerCase())){
                return true;
            }else {
                Toast.makeText(this, "Password wrong!", Toast.LENGTH_SHORT).show();
            }
        }else Toast.makeText(this, "Password is empty! Try again", Toast.LENGTH_SHORT).show();
        return false;
    }
}