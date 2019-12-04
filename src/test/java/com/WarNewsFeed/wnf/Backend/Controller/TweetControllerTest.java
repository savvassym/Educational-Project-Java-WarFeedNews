package com.WarNewsFeed.wnf.Backend.Controller;

import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import com.WarNewsFeed.wnf.WnfApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class TweetControllerTest {
    @Autowired
    TweetService service;

    @Test
    public void TestThatRestWillReturnANonEmptyList(){
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        List<Tweet> tweets = service.getAllTweets();
        Assert.assertTrue("Tweets size is: "+tweets.size(),tweets.size()>0);
        SpringApplication.exit(context, () -> 0);
    }

    @Test
    public void TestThatRestWillReturnANonEmptyListWhenFindTweetsByGivenCountry(){
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        List<Tweet> tweets = service.getTweetsByCountry("Greece");
        Assert.assertTrue("Tweets size is: "+tweets.size(),tweets.size()>0);
        SpringApplication.exit(context, () -> 0);
    }
    @Test
    public void getConflictsByCountry(){
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        String  country = "Dutch";
        int numOfConflicts = service.getConflictsByCountry(country.toLowerCase());
        Assert.assertTrue(numOfConflicts>0);
        SpringApplication.exit(context, () -> 0);
    }

    @Test
    public void TestThatGetConflictsByCountryWillReturn0whenInputIsEmpty(){
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        String  country = "";
        int numOfConflicts = service.getConflictsByCountry(country.toLowerCase());
        Assert.assertEquals(0, numOfConflicts);
        SpringApplication.exit(context, ()->0);
    }

    @Test
    public void TestThatGetConflictByCountryWillReturn0whenInputIsRandomPressedKeys(){
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        String  country ="3jjfsdu89030423nj";
        int numOfConflicts = service.getConflictsByCountry(country.toLowerCase());
        Assert.assertEquals(0, numOfConflicts);
        SpringApplication.exit(context, ()->0);
    }

}