package com.WarNewsFeed.wnf.backend.service;

import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.model.TweetText;
import com.WarNewsFeed.wnf.helpers.Triplet;
import com.WarNewsFeed.wnf.helpers.Tuple;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface TweetService {
    int insertTweet(Tweet tweet);
    int[] insertTweets(List<Tweet> tweets);
    List<Tweet> getAllTweets();
    Tweet getTweetById(String tweetId);
    List<Tweet> getTweetsByCountry(String country);
    int  findConflictsByTime(String country, Timestamp timeUp, Timestamp timeTo);
    int getConflictsByCountry(String country);
    List<Tweet> sortByCountry();
    Map<Object,Object> showCountOfEveryCountry();
    List<TweetText> getAllTweetText();
    int insertTweetText(TweetText text);
    int [] insertTweetsTexts(List<TweetText> tweetsTexts);
    List<Triplet> getConflictsWithCoordinate();
}
