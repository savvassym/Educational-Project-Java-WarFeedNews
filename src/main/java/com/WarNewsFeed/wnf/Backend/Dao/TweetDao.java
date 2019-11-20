package com.WarNewsFeed.wnf.Backend.Dao;

import com.WarNewsFeed.wnf.Backend.model.Tweet;

import java.util.List;

public interface TweetDao {
    void insertTweet(Tweet tweet);
    void insertTweets(List<Tweet> tweets);
    List<Tweet> getAllTweets();
    Tweet getTweetById(String tweetId);
    List<Tweet> getTweetsByCountry(String country);
}
