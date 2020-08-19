package com.example.newsapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsResponse {
    @SerializedName("hits")
    public ArrayList<News> news;

    public static class News {
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("title")
        public String title;
        @SerializedName("url")
        public String url;
        @SerializedName("author")
        public String author;
        @SerializedName("points")
        public long points;
        @SerializedName("story_text")
        public String story_text;
        @SerializedName("comment_text")
        public String comment_text;
        @SerializedName("num_comments")
        public long num_comments;
        @SerializedName("story_id")
        public String story_id;
        @SerializedName("story_title")
        public String story_title;
        @SerializedName("story_url")
        public String story_url;
        @SerializedName("parent_id")
        public long parent_id;
        @SerializedName("created_at_i")
        public long created_at_info;
        @SerializedName("relevancy_score")
        public long relevancy_score;
        @SerializedName("_tags")
        public ArrayList<String> tags;

        @SerializedName("objectID")
        public String objectID;

        @SerializedName("_highlightResult")
        public HighLights highlights;

        public static class HighLights {
            @SerializedName("title")
            public Data title;
            @SerializedName("url")
            public Data url;
            @SerializedName("author")
            public Data author;

            public static class Data {
                @SerializedName("value")
                public String value;
                @SerializedName("matchLevel")
                public String matchLevel;
                @SerializedName("matchedWords")
                public ArrayList<String> matchedWords;
            }
        }
    }
}
