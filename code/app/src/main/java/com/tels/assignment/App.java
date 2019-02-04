package com.tels.assignment;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.tels.assignment.connection.TransformerApi;
import com.tels.assignment.utility.AppConstants;

import java.io.IOException;

import retrofit2.Retrofit;

/**
 * Created by Nikhil
 */

public class App extends Application {
    private String mToken;

    @Override
    public void onCreate() {
        super.onCreate();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    mToken = new Retrofit.Builder().baseUrl(AppConstants.TRANSFORMER_URL).build().
                            create(TransformerApi.class).getToken().execute().body().string();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Token",mToken);
                    editor.apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();


    }

}

