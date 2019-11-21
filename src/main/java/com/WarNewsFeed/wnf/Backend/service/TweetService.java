package com.WarNewsFeed.wnf.Backend.service;

import com.WarNewsFeed.wnf.Backend.model.Tweet;

import java.util.List;

public interface TweetService {
    int insertTweet(Tweet tweet);
    int[] insertTweets(List<Tweet> tweets);
    List<Tweet> getAllTweets();
    Tweet getTweetById(String tweetId);
    List<Tweet> getTweetsByCountry(String country);
}
