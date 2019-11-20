package com.WarNewsFeed.wnf.Backend.service.impl;

import com.WarNewsFeed.wnf.Backend.Dao.TweetDao;
import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    TweetDao tweetDao;

    @Override
    public void insertTweet(Tweet tweet) {
        tweetDao.insertTweet(tweet);
    }

    @Override
    public void insertTweets(List<Tweet> tweets) {
        tweetDao.insertTweets(tweets);
    }

    @Override
    public void getAllTweets() {
        List<Tweet> tweets = tweetDao.getAllTweets();
        for(Tweet tweet : tweets){
            System.out.println(tweet.toString());
        }
    }

    @Override
    public void getTweetById(String tweetId) {
        Tweet tweet = tweetDao.getTweetById(tweetId);
        System.out.println(tweet);
    }

    @Override
    public void getTweetsByCountry(String country) {
        List<Tweet> tweets = tweetDao.getTweetsByCountry(country);
        for (Tweet tweet : tweets){
            System.out.println(tweet.toString());
        }
    }
}
