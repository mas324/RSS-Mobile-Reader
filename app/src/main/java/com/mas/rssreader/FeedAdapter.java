package com.mas.rssreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context c;
    private SiteDBHandler sites;

    public FeedAdapter(Context context, SiteDBHandler sites) {
        this.c = context;
        this.sites = sites;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FeedItem(this, inflater.inflate(R.layout.feed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FeedItem) holder).fill(sites.getSite(position + 1));
    }

    @Override
    public int getItemCount() {
        return sites.getSitesCount();
    }
}
