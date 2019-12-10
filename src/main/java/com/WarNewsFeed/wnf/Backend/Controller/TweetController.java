package com.WarNewsFeed.wnf.Backend.Controller;

import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
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


//
//    @PostMapping("/tweet/in")
//    int addTweet(@RequestBody @JsonProperty Tweet tweet){
//        return tweetService.insertTweet(tweet);
//    }

}
