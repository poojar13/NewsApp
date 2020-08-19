package com.example.newsapp.screen;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ListItemBinding;
import com.example.newsapp.models.NewsResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    ArrayList<NewsResponse.News> arrayList = new ArrayList<>();
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item,null,false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bindView(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        ListItemBinding binding;
        public NewsViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(final NewsResponse.News hits) {
            binding.title.setText(hits.title);
            binding.author.setText(hits.author);
            binding.date.setText(formattedDate(hits.created_at));
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(binding.getRoot().getContext(), WebViewActivity.class);
                    intent.putExtra("loadUrl",hits.url);
                    binding.getRoot().getContext().startActivity(intent);
                }
            });
        }
    }

    public void addNews(ArrayList<NewsResponse.News> hitsArrayList){
        if(hitsArrayList!=null){
            arrayList.clear();
            arrayList.addAll(hitsArrayList);
            notifyDataSetChanged();
        }
    }

    private String formattedDate(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String strDate = "";
        try {
            Date date = formatter.parse(dateString);
            strDate = new SimpleDateFormat("dd-MM-yyyy").format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strDate;
    }
}
