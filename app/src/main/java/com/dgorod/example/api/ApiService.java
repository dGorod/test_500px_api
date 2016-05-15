package com.dgorod.example.api;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dgorod.example.BuildConfig;
import com.dgorod.example.R;
import com.dgorod.example.exception.NotInitializedException;
import com.dgorod.example.util.Preconditions;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public abstract class ApiService {
    private static ApiInterface instance;

    private ApiService() { }

    /**
     * Initialization method. Call it before usage (preferably in {@link Application#onCreate()}.
     * @param context
     */
    public static synchronized void init(@NonNull Context context) {
        Preconditions.checkNotNull(context);

        if(instance == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ?
                    HttpLoggingInterceptor.Level.BASIC :
                    HttpLoggingInterceptor.Level.NONE);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES);

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.api_url))
                    .addConverterFactory(PrimitiveConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            instance = restAdapter.create(ApiInterface.class);
        }
    }

    /**
     * Singleton instance of API service helper.
     * @return
     * @throws NotInitializedException
     */
    public static synchronized ApiInterface getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException(ApiService.class);
        }
        else {
            return instance;
        }
    }
}
