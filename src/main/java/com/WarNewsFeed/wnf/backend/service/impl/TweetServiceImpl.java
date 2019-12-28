package com.WarNewsFeed.wnf.backend.service.impl;

import com.WarNewsFeed.wnf.backend.dao.TweetDao;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.model.TweetText;
import com.WarNewsFeed.wnf.backend.service.TweetService;
import com.WarNewsFeed.wnf.helpers.Triplet;
import com.WarNewsFeed.wnf.helpers.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
    public int findConflictsByTime(String country, Timestamp timeUp, Timestamp timeTo) {
        return tweetDao.findConflictsByTime(country,timeUp,timeTo);
    }

    @Override
    public List<Tweet> sortByCountry() {
        List<Tweet> tweets = tweetDao.sortByCountry();
        for(Tweet tweet : tweets){
            System.out.println(tweet.getCountry());
        }
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

    @Override
    public int getConflictsByCountry(String country) {
        return tweetDao.getConflictsByCountry(country);
    }

    @Override
    public Map<Object,Object> showCountOfEveryCountry(){
        Map<Object,Object> result = tweetDao.showCountOfEveryCountry();
        return result;
    }

    @Override
    public List<TweetText> getAllTweetText() {
        List<TweetText> tweetTexts = tweetDao.getAllTweetText();
        return tweetTexts;
    }

    @Override
    public int insertTweetText(TweetText text) {
        return  tweetDao.insertTweetText(text);
    }

    @Override
    public int[] insertTweetsTexts(List<TweetText> tweetsTexts) {
        return tweetDao.insertTweetsTexts(tweetsTexts);
    }

    @Override
    public List<Triplet> getConflictsWithCoordinate() {
        return tweetDao.getConflictsWithCoordinate();
    }


}
