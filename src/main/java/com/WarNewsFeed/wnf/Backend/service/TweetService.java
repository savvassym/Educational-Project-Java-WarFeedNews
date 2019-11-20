package com.WarNewsFeed.wnf.Backend.service;

import com.WarNewsFeed.wnf.Backend.model.Tweet;

import java.util.List;

public interface TweetService {
    void insertTweet(Tweet tweet);
    void insertTweets(List<Tweet> tweets);
    void getAllTweets();
    void getTweetById(String tweetId);
    void getTweetsByCountry(String country);
}
