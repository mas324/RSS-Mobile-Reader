package com.mas.rssreader;

import androidx.annotation.NonNull;

public class Site {

    private int id;
    private String url;
    private final String name;

    public Site(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Site(String name, String url, int id) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Site:{ id=%s, name=%s, url=%s }", id, name, url);
    }
}
