package com.bbc_reader.finalproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bbc_reader.finalproject.adapter.FeedAdapter;
import com.bbc_reader.finalproject.model.Feed;
import com.bbc_reader.finalproject.utils.SessionUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @description: Home page
 * */
public class MainActivity extends ActivityExtend {

    private ListView lvFeed;
    private FeedAdapter feedAdapter;
    private List<Feed> feeds;
    private List<Feed> data = new ArrayList<>();
    private EditText etSearch;
    private Button btnSearch, btnClear;
    private ProgressBar pbMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        super.initToolbar("Home");
        super.initDrawerLayout();
        super.setCurrent(this);

        connectView();
        initData();
        actionView();
    }

    private void connectView(){
        lvFeed = findViewById(R.id.lv_feed);
        feedAdapter = new FeedAdapter(this, data);
        lvFeed.setAdapter(feedAdapter);
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        btnClear = findViewById(R.id.btn_clear);
        pbMain = findViewById(R.id.pb_main);
        pbMain.setProgressTintList(ColorStateList.valueOf(Color.rgb(242,57,109)));
    }

    private void initData(){
        try {
            feeds = SessionUtils.get(this, SessionUtils.FEED, null);
            if(feeds.size() == 0){
                feeds.addAll(new MyAsyncTask().execute("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml").get());
                SessionUtils.set(this, SessionUtils.FEED, feeds);
            }
            data.addAll(feeds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search(){

    }

    private void actionView(){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txSearch = etSearch.getText().toString();
                data.clear();
                Toast.makeText(MainActivity.this, "Search is in progress", Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.cl_main_content), "Please wait a few minutes", Snackbar.LENGTH_LONG).show();
                for(int i = 0; i < feeds.size(); i++){
                    Integer progress = (Integer) (i+1)*100/feeds.size();
                    pbMain.setProgress(progress);
                    if(feeds.get(i).getTitle().toLowerCase(Locale.ROOT).contains(txSearch.toLowerCase(Locale.ROOT))){
                        data.add(feeds.get(i));
                    }
                }
                feedAdapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.clear();
                data.addAll(feeds);
                etSearch.setText("");
                feedAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show();
        feeds = SessionUtils.get(this, SessionUtils.FEED, null);
        this.data.clear();
        this.data.addAll(feeds);
        feedAdapter.notifyDataSetChanged();
    }
}