package com.WarNewsFeed.wnf.Backend.Controller;

import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/")
    String home(){
        return "rel";
    }

    @GetMapping("/tweets")
    List<Tweet> findAll(){
        return tweetService.getAllTweets();
    }

    @GetMapping("/tweets/{country}")
    List<Tweet> findByCountries(@PathVariable String country){
        return tweetService.getTweetsByCountry(country);
    }



//
//    @PostMapping("/tweet/in")
//    int addTweet(@RequestBody @JsonProperty Tweet tweet){
//        return tweetService.insertTweet(tweet);
//    }

}
