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
    public int insertTweet(Tweet tweet) {
        return tweetDao.insertTweet(tweet);
    }

    @Override
    public int[] insertTweets(List<Tweet> tweets) {
        return tweetDao.insertTweets(tweets);
    }

    @Override
    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = tweetDao.getAllTweets();
        return tweets;
    }

    @Override
    public Tweet getTweetById(String tweetId) {
        Tweet tweet = tweetDao.getTweetById(tweetId);
        return tweet;
    }

    @Override
    public List<Tweet> getTweetsByCountry(String country) {
        List<Tweet> tweets = tweetDao.getTweetsByCountry(country);
        return tweets;
    }
}
