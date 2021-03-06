package com.mas.rssreader;

public class RssItem {

    private String title;

    private String link;

    private String description;

    public RssItem() {
        title = "title";
        link = "link";
        description = "description";
    }

    public RssItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.link = "link";
    }

    public RssItem(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
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

    @Override
    public String toString() {
        return String.format("RssItem: [Title = %s, Description = %s]", title, description);
    }
}
