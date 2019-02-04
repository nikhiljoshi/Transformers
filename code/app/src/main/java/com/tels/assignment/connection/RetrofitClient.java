package com.tels.assignment.connection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.tels.assignment.ui.CreateActivity;
import com.tels.assignment.utility.AppConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;


    public static Retrofit getInstance(Context context) {
        if (retrofit == null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String mToken = preferences.getString("Token", "");

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader(AppConstants.HEADER_AUTH, "Bearer " + mToken)
                            .addHeader(AppConstants.HEADER_CONTENT_TYPE,AppConstants.HEADER_CONTENT_TYPE_VALUE )
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.TRANSFORMER_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}