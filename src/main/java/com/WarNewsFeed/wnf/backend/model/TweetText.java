package com.WarNewsFeed.wnf.backend.model;

public class TweetText {
    private String text;

    public TweetText(){}

    public TweetText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TweetText{" +
                "text='" + text + '\'' +
                '}';
    }
}
