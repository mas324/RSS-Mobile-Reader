package com.mas.rssreader;

public class RSSReader {

    private final String title;
    private final String link;
    private final String description;

    public RSSReader() {
        title = "title";
        link = "link";
        description = "description";
    }

    public RSSReader(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }
}
