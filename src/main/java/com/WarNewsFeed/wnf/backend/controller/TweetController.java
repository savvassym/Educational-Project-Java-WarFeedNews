package com.WarNewsFeed.wnf.backend.controller;

import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @GetMapping(value = "/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tweet> findAll() {
        return tweetService.getAllTweets();
    }

    @GetMapping(value = "/tweets/{country}")
    public List<Tweet> findByCountries(@PathVariable String country) {
        return tweetService.getTweetsByCountry(country);
    }

    @GetMapping(value = "/conflicts/{country}")
    public int numOfConflict(@PathVariable String country){
        return tweetService.getConflictsByCountry(country);
    }

    @GetMapping(value = "/showCountOfEveryCountry", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Object, Object> showCountForCountries(){
        return tweetService.showCountOfEveryCountry();
    }

//
//    @PostMapping("/tweet/in")
//    int addTweet(@RequestBody @JsonProperty Tweet tweet){
//        return tweetService.insertTweet(tweet);
//    }

}
