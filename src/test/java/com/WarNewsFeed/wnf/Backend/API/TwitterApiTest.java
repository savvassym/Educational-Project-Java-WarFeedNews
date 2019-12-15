package com.WarNewsFeed.wnf.Backend.API;

import com.WarNewsFeed.wnf.backend.api.TwitterApi;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class TwitterApiTest {


    @Test
    public void TestGetTwitterPostsWhenListOfStatusIsEmpty() throws TwitterException {
        TwitterApi twitterApiMock = mock(TwitterApi.class);
        when(twitterApiMock.getTwitterPosts()).thenReturn(new ArrayList<>());
        List<String> expected = new ArrayList<>();
        List<Status> result = twitterApiMock.getTwitterPosts();
        Assert.assertEquals(result,expected);
    }

    @Test(expected = RuntimeException.class)
    public void TestGetTwitterPostsWhenIsException() throws TwitterException {
        List<Status> list = (List<Status>) mock(TwitterApi.class);
        when(list.get(Mockito.anyInt())).thenThrow(
                new RuntimeException("Something went wrong"));
        list.get(0);
    }

    }