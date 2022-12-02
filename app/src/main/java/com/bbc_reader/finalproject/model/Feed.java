package com.bbc_reader.finalproject.model;

import android.view.View;

import org.w3c.dom.Element;

import java.util.Date;

public class Feed {
    private String title;
    private String description;
    private String link;
    private String guid;
    private String pubDate;
    private Integer visible = View.VISIBLE;
    private boolean isFavorite = false;

    public Feed(){}

    public Feed(Element el){
        this.title = el.getElementsByTagName("title").item(0).getTextContent();
        this.description = el.getElementsByTagName("description").item(0).getTextContent();
        this.link = el.getElementsByTagName("link").item(0).getTextContent();
        this.guid = el.getElementsByTagName("guid").item(0).getTextContent();
        this.pubDate = el.getElementsByTagName("pubDate").item(0).getTextContent();
    }

    public Feed(String title, String description, String link, String guid) {

        this.title = title;
        this.description = description;
        this.link = link;
        this.guid = guid;
        this.pubDate = new Date().toString();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", guid='" + guid + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
