package com.mas.rssreader;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class FeedItem extends RecyclerView.ViewHolder {

    private final FeedAdapter feedAdapter;
    private ImageView imageView;
    private final ConstraintLayout layout;
    private final TextView name;
    private final TextView description;

    public FeedItem(FeedAdapter adapter, View view) {
        super(view);
        layout = view.findViewById(R.id.feedItem);
        name = view.findViewById(R.id.feedName);
        description = view.findViewById(R.id.feedDescription);
        feedAdapter = adapter;
    }

    public void fill(final Site site, SiteDBHandler handler) {
        name.setText(site.getName());
        description.setText(site.getUrl());
        layout.setOnLongClickListener(v -> {
            handler.deleteSite(site.getId());
            feedAdapter.notifyItemRemoved(getAdapterPosition());
            return true;
        });

        layout.setOnClickListener(v -> {

        });
    }
}
