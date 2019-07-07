package com.example.tc.data.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tc.data.DataUtils;
import com.example.tc.data.NetworkBoundResource;
import com.example.tc.data.Resource;
import com.example.tc.data.local.SharedPrefUtil;
import com.example.tc.data.remote.ApiService;

import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class BlogRepository {

    private ApiService apiService;

    private Context context;

    public BlogRepository(ApiService apiService, Context context) {
        this.apiService = apiService;
        this.context = context;
    }

    public LiveData<Resource<String>> getBlogDataAt0thIndex() {
        return new NetworkBoundResource<String, String>() {
            @Override
            protected void saveCallResult(@NonNull String data) {
                SharedPrefUtil.saveStringInSP(context, SharedPrefUtil.BLOG_DATA_AT_0TH_INDEX, DataUtils.stringOfCharAt0thIndex(data));
            }

            @NonNull
            @Override
            protected LiveData<String> loadFromLocal() {
                MutableLiveData<String> liveData = new MutableLiveData<>();
                liveData.setValue(SharedPrefUtil.getStringFromSP(context, SharedPrefUtil.BLOG_DATA_AT_0TH_INDEX));
                return liveData;
            }

            @NonNull
            @Override
            protected Call<String> createCall() {
                return apiService.getBlog();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<String>> getBlogDataAt10xIndex() {
        return new NetworkBoundResource<String, String>() {
            @Override
            protected void saveCallResult(@NonNull String data) {
                SharedPrefUtil.saveStringInSP(context, SharedPrefUtil.BLOG_DATA_AT_10X_INDEX, DataUtils.stringOfCharAt10xIndex(data));
            }

            @NonNull
            @Override
            protected LiveData<String> loadFromLocal() {
                MutableLiveData<String> liveData = new MutableLiveData<>();
                liveData.setValue(SharedPrefUtil.getStringFromSP(context, SharedPrefUtil.BLOG_DATA_AT_10X_INDEX));
                return liveData;
            }

            @NonNull
            @Override
            protected Call<String> createCall() {
                return apiService.getBlog();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<String>> getBlogWordCount() {
        return new NetworkBoundResource<String, String>() {
            @Override
            protected void saveCallResult(@NonNull String data) {
                SharedPrefUtil.saveStringInSP(context, SharedPrefUtil.BLOG_WORD_COUNT, DataUtils.wordFrequency(data));
            }

            @NonNull
            @Override
            protected LiveData<String> loadFromLocal() {
                MutableLiveData<String> liveData = new MutableLiveData<>();
                liveData.setValue(SharedPrefUtil.getStringFromSP(context, SharedPrefUtil.BLOG_WORD_COUNT));
                return liveData;
            }

            @NonNull
            @Override
            protected Call<String> createCall() {
                return apiService.getBlog();
            }
        }.getAsLiveData();
    }

}
