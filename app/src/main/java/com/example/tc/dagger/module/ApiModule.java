package com.example.tc.dagger.module;

import android.app.Application;


import com.example.tc.data.remote.ApiConfig;
import com.example.tc.data.remote.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.setLenient().create();
    }

    @Provides
    @Singleton
    Cache cache(Application application) {
        long cacheSize = 5 * 1024 * 1024;
        File httpCacheDirectory = new File(application.getCacheDir(), "networkCache");
        return new Cache(httpCacheDirectory, cacheSize);
    }

    @Provides
    @Singleton
    Dispatcher dispatcher() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(3);
        return dispatcher;
    }

    @Provides
    @Singleton
    OkHttpClient okHttpClient(Cache cache, Dispatcher dispatcher) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache);
        httpClient.dispatcher(dispatcher);
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        return httpClient.build();
    }


    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .client(okHttpClient)
                .build();
    }

}
