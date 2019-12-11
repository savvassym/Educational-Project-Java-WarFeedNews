package com.WarNewsFeed.wnf;

import com.WarNewsFeed.wnf.backend.api.GeocodeApi;
import com.WarNewsFeed.wnf.backend.api.TwitterApi;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.nlp.Nlp;
import com.WarNewsFeed.wnf.backend.nlp.Tokenizer;
import com.WarNewsFeed.wnf.backend.service.TweetService;
import com.WarNewsFeed.wnf.backend.service.TwitterApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import twitter4j.TwitterException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class WnfApplication {
	@Autowired
	TweetService tweetService;
	@Autowired
    Nlp filter;
	@Autowired
	Tokenizer tokenizer;

	public static void main(String[] args) throws TwitterException{
		ApplicationContext context = SpringApplication.run(WnfApplication.class, args);
		TweetService tweetService = context.getBean(TweetService.class);
        Nlp filter  = context.getBean(Nlp.class);
        Tokenizer tokenizer = context.getBean(Tokenizer.class);


        GeocodeApi geocodeApi = new GeocodeApi();
		String coordinates;
        Map<String,String> collection = new HashMap<>();

		TwitterApi twitterApi = new TwitterApi();
        TwitterApiService twitterApiService = new TwitterApiService(twitterApi);
        List<String> tweetTexts = twitterApiService.getTweets();
        List<String> countries = new ArrayList<>();


        for (String st : tweetTexts){
            System.out.println(st);
        }

        List<String> tokens = new ArrayList<>();
        for(String tw : tweetTexts){
            tokens.add(tokenizer.tokenize(tw));
        }


        System.out.println("Run with only one annotation");
        long startTime = System.currentTimeMillis();

        for (String st : tokens){
            collection.putAll(filter.analyzer(st));
            System.out.println("analyze tweets...please wait...");
        }



        collection.forEach((key,value)->System.out.println(key+" : "+ value));
        for(Map.Entry entry: collection.entrySet()){
		    if("COUNTRY".equals(entry.getValue())){
		        countries.add((String) entry.getKey());
            }
        }
        for(String cs : countries){
            System.out.println(cs);
        }
        int i=1;
        for (String cs : countries){
            coordinates=geocodeApi.getCoordinates(cs.toLowerCase());
            Tweet tweet = new Tweet(Integer.toString(i),cs.toLowerCase(), Timestamp.valueOf(LocalDateTime.now()),coordinates);
            tweetService.insertTweet(tweet);
            i++;
        }
        List<Tweet> tweets = tweetService.getAllTweets();
        for(Tweet tw : tweets){
            System.out.println(tw.toString());
        }

        //stop time
        long stopTime = System.currentTimeMillis();
        double toSec = (stopTime-startTime) / 1000.0;
        System.out.println(Math.round(toSec));


    }

}
