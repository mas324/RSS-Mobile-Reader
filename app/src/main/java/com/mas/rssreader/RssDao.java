package com.mas.rssreader;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RssDao {
    @Query("SELECT * FROM rssItem")
    List<RssItem> getAll();

    @Insert
    void insertAll(RssItem... rssItems);

    @Delete
    void delete(RssItem rssItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(RssItem item);
}
