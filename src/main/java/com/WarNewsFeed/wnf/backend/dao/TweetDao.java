package com.WarNewsFeed.wnf.backend.dao;

import com.WarNewsFeed.wnf.backend.model.Tweet;

import java.sql.Timestamp;
import java.util.List;

public interface TweetDao {
    int insertTweet(Tweet tweet);
    int[] insertTweets(List<Tweet> tweets);
    List<Tweet> getAllTweets();
    Tweet getTweetById(String tweetId);
    List<Tweet> getTweetsByCountry(String country);
    int getConflictsByCountry(String country);
    int  findConflictsByTime(String country, Timestamp timeUp, Timestamp timeTo);
    List<Tweet> sortByCountry();
}
