package com.mas.rssreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //private Context c;
    private final SiteDBHandler sites;

    public FeedAdapter(Context context, SiteDBHandler sites) {
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
        List<Site> items = sites.getAllSites();
        ((FeedItem) holder).fill(items.get(position), sites);
    }

    @Override
    public int getItemCount() {
        return sites.getSitesCount();
    }
}
