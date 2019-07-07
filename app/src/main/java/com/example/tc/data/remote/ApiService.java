package com.example.tc.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(ApiConfig.EndPoint.BLOG)
    Call<String> getBlog();
}
