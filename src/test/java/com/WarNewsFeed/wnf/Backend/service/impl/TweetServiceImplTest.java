package com.WarNewsFeed.wnf.Backend.service.impl;

import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import com.WarNewsFeed.wnf.WnfApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TweetServiceImplTest extends JdbcDaoSupport {
    @Autowired
    TweetService service;


    @Test
    public void TestThatICanInsertTweetOnDB(){
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        Tweet tweet = new Tweet();
        tweet.setTid("7");
        tweet.setCountry("Germany");
        tweet.setTime_stamp(Timestamp.valueOf("2019-11-21 14:19:30.554"));
        tweet.setCoordinates("2393281");
        int actual = service.insertTweet(tweet);
        int expected =1;
        Assert.assertEquals(expected,actual);
        SpringApplication.exit(context, () -> 0);
    }

    @Test
    public void TestThatICanInsertListOfTweetsOnDB() {
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        Tweet tweet = new Tweet();
        tweet.setTid("5");
        tweet.setCountry("TestCountry3");
        tweet.setTime_stamp(Timestamp.valueOf("2019-11-21 14:41:20.55"));
        tweet.setCoordinates("1234");
        Tweet tweet1 = new Tweet();
        tweet1.setTid("6");
        tweet1.setCountry("TestCountry4");
        tweet1.setTime_stamp(Timestamp.valueOf("2019-11-21 14:41:20.55"));
        tweet1.setCoordinates("4567");
        List<Tweet> list = new ArrayList<>();
        list.add(tweet);
        list.add(tweet1);
        int[] actual = service.insertTweets(list);
        int[] expected = {1,1};
        Assert.assertArrayEquals(expected,actual);
        SpringApplication.exit(context, () -> 0);
    }

    @Test
    public void TestThatDatabaseResponseToGetAllTweetsQuery() {
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        List<Tweet> actual = service.getAllTweets();
        Assert.assertTrue(actual.size()>0);
        SpringApplication.exit(context,()->0);
    }

    @Test
    public void TestThatDatabaseResponseToGetTweetByID() {
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        Tweet response = service.getTweetById("4");
        Assert.assertNotNull(response);
        SpringApplication.exit(context,()->0);
    }

    @Test
    public void TestThatDatabaseResponseToGetTweetsByCountry() {
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        List<Tweet> response = service.getTweetsByCountry("Greece");
        Assert.assertTrue(response.size()>0);
        SpringApplication.exit(context,()->0);
    }

    @Test
    public void TestThatDbResponseToQueryfindConfilcsByTimestamp() {
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        int response = service.findConflictsByTime("Greece", Timestamp.valueOf("2019-11-21 14:19:30.554"), Timestamp.valueOf("2019-11-25 22:42:48.000000000"));
        Assert.assertTrue(response>0);
        SpringApplication.exit(context, () -> 0);
    }

    @Test
    public void sortByCountry() {
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        List<Tweet> response = service.sortByCountry();
        Assert.assertTrue(response.size()>0);
        SpringApplication.exit(context,()->0);
    }
}