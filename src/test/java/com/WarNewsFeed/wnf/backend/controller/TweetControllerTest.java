package com.WarNewsFeed.wnf.backend.controller;

import com.WarNewsFeed.wnf.WnfApplication;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.service.TweetService;
import com.WarNewsFeed.wnf.helpers.Triplet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetControllerTest {


    TweetService service;
    private ApplicationContext context;

    @Before
    public void setUp()
    {
        context = SpringApplication.run(WnfApplication.class);
        service = context.getBean(TweetService.class);
    }
    @Test
    public void TestThatRestWillReturnANonEmptyList(){
        Tweet tweet = new Tweet("1","Greece", Timestamp.valueOf("2019-11-21 14:41:20.55"),"12334");
        Tweet tweet1 = new Tweet("2","Germany", Timestamp.valueOf("2019-10-21 14:41:20.55"),"69583");
        List<Tweet> tweets = service.getAllTweets();
        Assert.assertTrue("Tweets size is: "+tweets.size(),tweets.size()>0);
    }

    @Test
    public void TestThatRestWillReturnANonEmptyListWhenFindTweetsByGivenCountry(){
        Tweet tweet = new Tweet("3","Iran", Timestamp.valueOf("2019-09-21 14:41:20.55"),"9832878");
        service.insertTweet(tweet);
        String string = "Iran";
        List<Tweet> tweets = service.getTweetsByCountry(string.toLowerCase());
        Assert.assertTrue("Tweets size is: "+tweets.size(),tweets.size()>0);
    }

    @Test
    public void TestThatRestWillReturnHowManyTimesTheGivenCountryFound(){
        Tweet tweet = new Tweet("4","Greece", Timestamp.valueOf("2019-11-21 14:41:20.55"),"3283812");
        service.insertTweet(tweet);
        String  country = "Greece";
        int numOfConflicts = service.getConflictsByCountry(country.toLowerCase());
        Assert.assertTrue(numOfConflicts>0);
    }

    @Test
    public void TestThatGetConflictsByCountryWillReturn0whenInputIsEmpty(){
        String  country = "";
        int numOfConflicts = service.getConflictsByCountry(country.toLowerCase());
        Assert.assertEquals(0, numOfConflicts);
    }

    @Test
    public void TestThatGetConflictByCountryWillReturn0whenInputIsRandomPressedKeys(){
        String  country ="3jjfsdu89030423nj";
        int numOfConflicts = service.getConflictsByCountry(country.toLowerCase());
        Assert.assertEquals(0, numOfConflicts);
    }

    @Test
    public void TestThatShowCountForCountriesWillReturnTheCountOfEveryCountry(){
        Map<Object,Object> res = new HashMap<>();
        res = service.showCountOfEveryCountry();
        Assert.assertTrue(res.size()>0);
    }

    @Test
    public void getConflictsWithCoordinates() {
        List<Triplet> res = service.getConflictsWithCoordinate();
        Assert.assertTrue(res.size()>0);
    }


    @After
    public void tearDown()
    {
        SpringApplication.exit(context, ()->0);

    }


}