package com.mas.rssreader;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class RssItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "link")
    private String link;

    @ColumnInfo(name = "description")
    private String description;

    public RssItem() {
        uuid = UUID.randomUUID().toString();
        title = "title";
        link = "link";
        description = "description";
    }

    public RssItem(String title, String link, String description) {
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.link = link;
        this.description = description;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
