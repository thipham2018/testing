package com.bbc_reader.finalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bbc_reader.finalproject.model.Feed;
import com.bbc_reader.finalproject.utils.SessionUtils;

import java.util.List;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class FeedAddActivity extends ActivityExtend {

    private EditText etTitle, etUrl, etDescription, etGuid;
    private Button btnAdd;
    private List<Feed> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_feed_add);

        super.initToolbar("Add Source");
        super.initDrawerLayout();
        super.setCurrent(this);

        connectView();
        initData();
        actionView();
    }

    private void connectView(){
        etTitle = findViewById(R.id.et_title);
        etUrl = findViewById(R.id.et_url);
        etDescription = findViewById(R.id.et_description);
        etGuid = findViewById(R.id.et_guid);
        btnAdd = findViewById(R.id.btn_add);
    }

    private void initData(){
        feeds = SessionUtils.get(this, SessionUtils.FEED, null);
    }

    private void actionView(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String url = etUrl.getText().toString().trim();
                String desc = etDescription.getText().toString().trim();
                String guid = etGuid.getText().toString().trim();
                if(title.length() > 0
                && url.length() > 0
                && desc.length() > 0
                && guid.length() > 0){
                    Feed f = new Feed(title, desc, url, guid);
                    feeds.add(f);
                    SessionUtils.set(getApplicationContext(), SessionUtils.FEED, feeds);
                    Toast.makeText(FeedAddActivity.this, "Add feed successful", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(FeedAddActivity.this, "Data not empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}