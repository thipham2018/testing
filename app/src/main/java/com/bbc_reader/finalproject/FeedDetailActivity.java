package com.bbc_reader.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.bbc_reader.finalproject.model.Feed;
import com.bbc_reader.finalproject.utils.SessionUtils;

import java.util.List;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class FeedDetailActivity extends ActivityExtend {

    private TextView tvTitle, tvUrl, tvDescription, tvGuid, tvPubDate;
    private Button btnDelete;
    private Switch swFavorite;
    private Integer position = 0;
    private List<Feed> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_feed_detail);

        super.initToolbar("Detail");
        super.initDrawerLayout();
        super.setCurrent(this);

        connectView();
        initData();
        actionView();
    }

    private void connectView(){
        tvTitle = findViewById(R.id.tv_title);
        tvUrl = findViewById(R.id.tv_url);
        tvDescription = findViewById(R.id.tv_description);
        tvGuid = findViewById(R.id.tv_guid);
        tvPubDate = findViewById(R.id.tv_pub_date);
        swFavorite = findViewById(R.id.sw_favorite);
        btnDelete = findViewById(R.id.btn_delete);
    }

    private void initData(){
        Intent intent = getIntent();
        String guid = intent.getStringExtra("guid");

        feeds = SessionUtils.get(this, SessionUtils.FEED, null);
        for(int i = 0; i < feeds.size(); i++){
            if(guid.equals(feeds.get(i).getGuid())){
                position = i;
                break;
            }
        }
        if(feeds != null && feeds.get(position) != null){
            Feed feed = feeds.get(position);
            tvTitle.setText(feed.getTitle());
            tvUrl.setText(Html.fromHtml(String.format("<u>%s<u>", feed.getLink())));
            tvDescription.setText(feed.getDescription());
            tvGuid.setText(Html.fromHtml(String.format("<u>%s<u>", feed.getGuid())));
            tvPubDate.setText(feed.getPubDate());
            swFavorite.setChecked(feed.isFavorite());
        }
    }

    private void actionView(){
        swFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                feeds.get(position).setFavorite(b);
                SessionUtils.set(getApplicationContext(), SessionUtils.FEED, feeds);
            }
        });

        tvUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = tvUrl.getText().toString();
                intentBrowser(url);
            }
        });

        tvGuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = tvGuid.getText().toString();
                intentBrowser(url);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feeds.remove(feeds.get(position));
                SessionUtils.set(getApplicationContext(), SessionUtils.FEED, feeds);
                finish();
            }
        });
    }

    private void intentBrowser(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}