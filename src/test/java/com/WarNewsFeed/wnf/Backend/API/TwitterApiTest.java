package com.WarNewsFeed.wnf.Backend.API;

import org.junit.Test;
import twitter4j.Status;
import twitter4j.TwitterException;
import java.util.List;
import static org.junit.Assert.assertNotNull;

public class TwitterApiTest {

    @Test
    public void getTwitterPosts() throws TwitterException {
        TwitterApi connection = new TwitterApi();
        List<Status> data = connection.getTwitterPosts();
        assertNotNull(data);
    }
}