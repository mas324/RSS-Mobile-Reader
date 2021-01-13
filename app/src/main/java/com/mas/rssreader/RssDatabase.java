package com.mas.rssreader;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class RssDatabase {
    private final AppDatabase db;

    public RssDatabase(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "rssdata").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public List<RssItem> getAllItems() {
        return db.rssDao().getAll();
    }

    public void insertFeed(RssItem item) {
    db.rssDao().insertAll(item);
    }

    public void deleteItem(String uuid) {
        List<RssItem> allItems = db.rssDao().getAll();

        for (RssItem item : allItems) {
            if (item.getUuid().equalsIgnoreCase(uuid))
                db.rssDao().delete(item);
        }
    }
}
