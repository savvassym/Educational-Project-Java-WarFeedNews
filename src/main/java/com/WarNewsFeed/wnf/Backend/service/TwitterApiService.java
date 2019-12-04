package com.WarNewsFeed.wnf.Backend.service;

import com.WarNewsFeed.wnf.Backend.API.TwitterApi;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TwitterApiService {
    TwitterApi twitterApi;

    public TwitterApiService(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    //get tweet status and transform it into a mytweetstatus
    public List<String> getTweets() {
        List<String> results = new ArrayList<>();
        try {
            results =
                    twitterApi.getTwitterPosts().stream().map(Status::getText)
                            .filter(Objects::nonNull).collect(Collectors.toList());
        } catch (TwitterException e) {
            //log error ??
        }
        return results;
    }
}
