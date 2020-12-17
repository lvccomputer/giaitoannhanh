package com.hungcd.giaitoannhanh.retrofit;


public class ApiUtils {

    public static final String BASE_URL = "http://68.183.234.90:8000/home/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}