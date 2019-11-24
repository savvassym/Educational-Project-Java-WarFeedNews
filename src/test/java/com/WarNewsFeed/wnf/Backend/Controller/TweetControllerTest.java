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
    public void TestThatGivenTweetsWillProduceStatus200WhenGetTweetsRun(){
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        List<Tweet> tweets = service.getAllTweets();
        Assert.assertTrue("Tweets size is: "+tweets.size(),tweets.size()>0);
        SpringApplication.exit(context, () -> 0);
    }

    @Test
    public void findByCountries() throws Exception {
        ApplicationContext context = SpringApplication.run(WnfApplication.class);
        TweetService service = context.getBean(TweetService.class);
        List<Tweet> tweets = service.getTweetsByCountry("Greece");
        Assert.assertTrue("Tweets size is: "+tweets.size(),tweets.size()>0);
        SpringApplication.exit(context, () -> 0);
    }
}