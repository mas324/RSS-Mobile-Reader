package com.mas.rssreader;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class FeedItem extends RecyclerView.ViewHolder {

    private final FeedAdapter feedAdapter;
    private ImageView imageView;
    private ConstraintLayout layout;
    private TextView name;
    private TextView description;
    private TextView index;

    public FeedItem(FeedAdapter adapter, View view) {
        super(view);
        layout = view.findViewById(R.id.feedItem);
        name = view.findViewById(R.id.feedName);
        description = view.findViewById(R.id.feedDescription);
        index = view.findViewById(R.id.feedNumber);
        feedAdapter = adapter;
    }

    public void fill(final Site site) {
        name.setText(site.getName());
        description.setText(site.getUrl());
        index.setText(String.valueOf(site.getId()));
        layout.setOnLongClickListener(v -> {
            return true;
        });
    }
}
