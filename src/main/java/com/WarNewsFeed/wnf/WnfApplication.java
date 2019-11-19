package com.WarNewsFeed.wnf;

import com.WarNewsFeed.wnf.Backend.API.GeocodeApi;
import com.WarNewsFeed.wnf.Backend.API.TwitterApi;
import com.WarNewsFeed.wnf.Backend.NLP.Nlp;
import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class WnfApplication {
	@Autowired
	TweetService tweetService;
	public static void main(String[] args) throws TwitterException, SQLException {
		ApplicationContext context = SpringApplication.run(WnfApplication.class, args);
		TweetService tweetService = context.getBean(TweetService.class);

//		Tweet tweet = new Tweet();
//		tweet.setTid("1");
//		tweet.setCountry("England");
//		tweet.setTime_stamp(null);
//		tweet.setCoordinates("3428349238");
//
//		tweetService.insertTweet(tweet);
//
//		tweetService.getAllTweets();

		Map<Double,Double> test = new HashMap<>();
		//In country we put the userInput
		GeocodeApi geocodeApi = new GeocodeApi();
		test=geocodeApi.getCoordinates("greece");


	    List<Status> status;
		TwitterApi twitterApi = new TwitterApi();
		status = twitterApi.getTwitterPosts();
		for(Status st : status) {
			System.out.println(st.getUser().getName()+"========"+st.getText());
		}


		Nlp filter = new Nlp();
		Map<String, String> collect;
		long startTime = System.currentTimeMillis();
		collect = filter.analyzer("It takes a lifetime for someone to discover Greece, but it only takes an instance to fall in love with her.");
		long stopTime = System.currentTimeMillis();
		long ms = stopTime-startTime;
		long sec = ms/1000;
		collect.forEach((key,value)-> System.out.println(key + " : "+ value));
		System.out.println(sec);
		test.forEach((key,value)-> System.out.println(key + ":"+value));

	}

}
