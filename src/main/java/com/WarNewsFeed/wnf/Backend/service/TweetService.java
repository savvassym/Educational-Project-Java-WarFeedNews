package com.WarNewsFeed.wnf.Backend.service;

import com.WarNewsFeed.wnf.Backend.model.Tweet;

import java.sql.Timestamp;
import java.util.List;

public interface TweetService {
    int insertTweet(Tweet tweet);
    int[] insertTweets(List<Tweet> tweets);
    List<Tweet> getAllTweets();
    Tweet getTweetById(String tweetId);
    List<Tweet> getTweetsByCountry(String country);
    int  findConflictsByTime(String country, Timestamp timeUp, Timestamp timeTo);
    int getConflictsByCountry(String country);
    List<Tweet> sortByCountry();
}
