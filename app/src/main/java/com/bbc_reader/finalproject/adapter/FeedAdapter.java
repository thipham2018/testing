package com.bbc_reader.finalproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bbc_reader.finalproject.FeedDetailActivity;
import com.bbc_reader.finalproject.R;
import com.bbc_reader.finalproject.model.Feed;

import java.util.List;

public class FeedAdapter extends ArrayAdapter<Feed> {

    private static Integer RES = R.layout.item_feed;
    private Activity activity;
    private List<Feed> data;

    public FeedAdapter(@NonNull Activity context, @NonNull List<Feed> data) {
        super(context, RES, data);
        this.activity = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(RES, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.loadData(data.get(position));
        viewHolder.actionView(data.get(position));
        return convertView;
    }

    private class ViewHolder{
        private View view;
        private TextView tvTitle;
        private ConstraintLayout itemLayout;

        public ViewHolder(View v){
            this.view = v;
            connectView();
        }

        private void connectView(){
            this.tvTitle = this.view.findViewById(R.id.tv_title);
            this.itemLayout = this.view.findViewById(R.id.item_layout);
        }

        public void loadData(Feed f){
            this.tvTitle.setText(f.getTitle());
            this.view.setVisibility(f.getVisible());
            if(f.getVisible() == View.GONE){
                this.view.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            }
        }

        public void actionView(Feed f){
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, FeedDetailActivity.class);
                    i.putExtra("guid", f.getGuid());
                    activity.startActivityForResult(i, 100);
                }
            });
        }
    }
}
