package com.hungcd.giaitoannhanh.retrofit;

import com.hungcd.giaitoannhanh.retrofit.models.Login;
import com.hungcd.giaitoannhanh.retrofit.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface SOService {
    @POST("home/login")
    Call<Login> login(@Body User user);
}
