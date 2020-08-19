package com.example.newsapp.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.models.NewsResponse;
import com.example.newsapp.networkManager.NetworkCom;
import com.example.newsapp.view_model.NewsVm;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NewsVm newsVm;
    ProgressDialog progressDialog;
    NewsAdapter newsAdapter = new NewsAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        newsVm = ViewModelProviders.of(this).get(NewsVm.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newsVm.getNews(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        newsVm.getNews("");
        observeNews();
    }

    private void observeNews() {
        newsVm.newsResponseNetworkCom.getState().observe(this, new Observer<NetworkCom.States>() {
            @Override
            public void onChanged(NetworkCom.States states) {
                switch (states){
                    case LOADING:
                        if(progressDialog!=null && !progressDialog.isShowing()){
                            progressDialog.show();
                        }
                        break;
                    case SUCCESS:
                        if(progressDialog!=null && progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                       NewsResponse newsResponse = newsVm.newsResponseNetworkCom.getData().getValue();
                        ArrayList<NewsResponse.News> newsArrayList = newsResponse.news;
                        newsAdapter.addNews(newsResponse.news);
                        binding.recyclerView.setAdapter(newsAdapter);
                        break;
                    case FAILED:
                        if(progressDialog!=null && progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        break;
                }
            }
        });
    }

}