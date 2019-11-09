package com.WarNewsFeed.wnf.Backend.API;

import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TwitterApiTest {

    @org.junit.Test
    public void twitterConnection() throws TwitterException {
        TwitterApi connection = new TwitterApi();
        connection.twitterConnection().getAccountSettings().equals("twitter4j.AccountSettingsJSONImpl@2a54a73f");
    }

    @org.junit.Test
    public void getData() throws TwitterException {
        TwitterApi connection = new TwitterApi();
        List<Status> data = connection.getData();
        assertNotNull(data);
    }
}