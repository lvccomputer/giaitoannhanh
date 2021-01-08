package com.hungcd.giaitoannhanh.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hungcd.giaitoannhanh.R;
import com.hungcd.giaitoannhanh.UserConstant;
import com.hungcd.giaitoannhanh.retrofit.ApiUtils;
import com.hungcd.giaitoannhanh.retrofit.models.Login;
import com.hungcd.giaitoannhanh.retrofit.SOService;
import com.hungcd.giaitoannhanh.retrofit.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {

    private static final String TAG = "LoginActivity";
    private EditText edtUser, edtPassword;
    private TextView tvLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        initProgressDialog();
        action();

        if (UserConstant.getToken() != null && !UserConstant.getToken().equals("")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void bindViews() {
        edtUser = findViewById(R.id.edt_user);
        edtPassword = findViewById(R.id.edt_password);
        tvLogin = findViewById(R.id.tv_login);
    }

    private void action() {
        tvLogin.setOnClickListener(v -> {

            progressDialog.show();
            SOService soService = ApiUtils.getSOService();
            if (isUser() && isPassword()) {
                soService.login(new User(edtUser.getText().toString(), edtPassword.getText().toString())).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Log.e(TAG, "onResponse: "+response.toString());
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            Log.e(TAG, "onResponse: "+response.body().getMessage());
                            Log.e(TAG, "onResponse: "+response.body().getData().getToken());
                            UserConstant.setToken(response.body().getData().getToken());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Something Wrong. Try again!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.toString());
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Server not working or Information is correct!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private boolean isUser() {
        if (!TextUtils.isEmpty(edtUser.getText().toString())) {
            return true;
        } else {
            Toast.makeText(this, "User is empty! Try again", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        return false;
    }

    private boolean isPassword() {
        if (!TextUtils.isEmpty(edtPassword.getText().toString())) {
            return true;
        } else {
            Toast.makeText(this, "Password is empty! Try again", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        return false;
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar);
    }

}