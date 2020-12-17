package com.hungcd.giaitoannhanh.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface SOService {

    @POST("login")
    Call<Login> post(@Body User user);
}
