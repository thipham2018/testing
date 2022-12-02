package com.bbc_reader.finalproject;

import android.os.Bundle;
import android.widget.ListView;

import com.bbc_reader.finalproject.adapter.FeedAdapter;
import com.bbc_reader.finalproject.model.Feed;
import com.bbc_reader.finalproject.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends ActivityExtend {

    private ListView lvFeed;
    private FeedAdapter feedAdapter;
    private List<Feed> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_favorite);
        super.initToolbar("Favorite");
        super.initDrawerLayout();
        super.setCurrent(this);

        connectView();
        initData();
    }

    private void connectView(){
        lvFeed = findViewById(R.id.lv_feed);
        feedAdapter = new FeedAdapter(this, data);
        lvFeed.setAdapter(feedAdapter);
    }

    private void initData(){
        try {
            List<Feed> feeds = SessionUtils.get(this, SessionUtils.FEED, null);
            if(feeds.size() > 0){
                for(int i = 0; i < feeds.size(); i++){
                    if(feeds.get(i).isFavorite()){
                        data.add(feeds.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}