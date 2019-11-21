package com.WarNewsFeed.wnf.Backend.API;

import org.junit.Test;
import org.mockito.Mockito;
import twitter4j.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TwitterApiTest {
    
    @Test
    public void TestGetTwitterPostsWhenListOfStatusIsEmpty() throws TwitterException {
        TwitterApi twitterApiMock = mock(TwitterApi.class);
        when(twitterApiMock.getTwitterPosts()).thenReturn(new ArrayList<>());
        List<String> expected = new ArrayList<>();
        List<Status> result = twitterApiMock.getTwitterPosts();
        assertEquals(result,expected);
    }

    @Test(expected = RuntimeException.class)
    public void TestGetTwitterPostsWhenIsException() throws TwitterException {
        List<Status> list = (List<Status>) mock(TwitterApi.class);
        when(list.get(Mockito.anyInt())).thenThrow(
                new RuntimeException("Something went wrong"));
        list.get(0);
    }
    
    }