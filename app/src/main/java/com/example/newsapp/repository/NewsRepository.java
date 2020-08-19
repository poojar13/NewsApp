package com.example.newsapp.repository;

import com.example.newsapp.models.NewsResponse;
import com.example.newsapp.networkManager.ApiModule;
import com.example.newsapp.networkManager.NetworkCom;
import com.example.newsapp.networkManager.RestService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository newsRepository;
    private RestService restService;
    public static NewsRepository getInstance(){
        if(newsRepository==null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsRepository(){
        restService = ApiModule.getInstance().getRestService();
    }
    public void getNews(String query, final NetworkCom<NewsResponse> newsResponseNetworkCom) {
        if(newsResponseNetworkCom.isLoading())
            return;
        newsResponseNetworkCom.startLoading();
        restService.getNews(query).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    newsResponseNetworkCom.publishSuccess(response.body(), response.code(), response.headers());

                }
                else {
                    newsResponseNetworkCom.publishError(NetworkCom.Error.HTTP, response.message(), response.code(), response.headers());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                NetworkCom.Error error = NetworkCom.Error.OTHER;
                if (t instanceof IOException) {
                    error = NetworkCom.Error.NETWORK;
                }
                newsResponseNetworkCom.publishError(error, "Something went wrong", -1, null);
            }
        });
    }
}
