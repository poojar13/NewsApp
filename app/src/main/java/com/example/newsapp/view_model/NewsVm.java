package com.example.newsapp.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.newsapp.models.NewsResponse;
import com.example.newsapp.networkManager.NetworkCom;
import com.example.newsapp.repository.NewsRepository;

import java.util.ArrayList;

public class NewsVm extends AndroidViewModel {
    private NewsRepository newsRepository;
    public final NetworkCom<NewsResponse> newsResponseNetworkCom = new NetworkCom.Factory<NewsResponse>().createNonMutableNetworkCom();

    public NewsVm(@NonNull Application application) {
        super(application);
        newsRepository = NewsRepository.getInstance();
    }

    public void getNews(String query) {
        newsRepository.getNews(query, newsResponseNetworkCom);
    }
}
