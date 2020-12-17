package com.hungcd.giaitoannhanh;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class UserConstant {
    private static SharedPreferences sharedPreferences;

    public static void setToken(String tk) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getApp());
        sharedPreferences.edit().putString("_token", tk).apply();
    }

    public static String getToken() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getApp());

        return sharedPreferences.getString("_token", "");
    }
}
