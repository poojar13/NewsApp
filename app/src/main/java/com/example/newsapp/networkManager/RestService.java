package com.example.newsapp.networkManager;

import com.example.newsapp.models.NewsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {
    @GET("v1/search/")
    Call<NewsResponse> getNews(@Query("query") String searchQuery);
}
