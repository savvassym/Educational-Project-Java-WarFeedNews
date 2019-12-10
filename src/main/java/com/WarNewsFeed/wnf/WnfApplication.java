package com.WarNewsFeed.wnf;

import com.WarNewsFeed.wnf.Backend.API.GeocodeApi;
import com.WarNewsFeed.wnf.Backend.API.TwitterApi;
import com.WarNewsFeed.wnf.Backend.NLP.Nlp;
import com.WarNewsFeed.wnf.Backend.NLP.Tokenizer;
import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import com.WarNewsFeed.wnf.Backend.service.TwitterApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import twitter4j.TwitterException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        Map<String,String> collect = new TreeMap<>();

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


        for (String st : tokens){
            collect = filter.analyzer(st);
        //    collect.putAll(filter.analyzer(st));
        }
        collect.forEach((key,value)->System.out.println(key+" : "+ value));
        for(Map.Entry entry: collect.entrySet()){
		    if("COUNTRY".equals(entry.getValue()) || "NATIONALITY".equals(entry.getValue())){
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

	}

}
