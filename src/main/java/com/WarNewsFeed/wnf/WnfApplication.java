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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class WnfApplication {
	@Autowired
	TweetService tweetService;

	public static void main(String[] args) throws TwitterException, SQLException, InterruptedException {
		ApplicationContext context = SpringApplication.run(WnfApplication.class, args);
		TweetService tweetService = context.getBean(TweetService.class);
        GeocodeApi geocodeApi = new GeocodeApi();
//		Date date = new Date();
//		long time = date.getTime();
		String coordinates;
		Nlp filter = new Nlp();
		Map<String, String> collect = null;
	    List<Status> status;
		TwitterApi twitterApi = new TwitterApi();
		status = twitterApi.getTwitterPosts();
        for(Status st : status){
            System.out.println(st.getText());
        }
		for(Status st : status){
		    collect=filter.analyzer(st.getText());
        }
		List<String> countries = new ArrayList<>();
        assert collect != null;
        for(Map.Entry entry: collect.entrySet()){
		    if("COUNTRY".equals(entry.getValue())){
		        countries.add((String) entry.getKey());
            }
        }

        int i=1;
        for (String cs : countries){
            coordinates=geocodeApi.getCoordinates(cs.toLowerCase());
            Tweet tweet = new Tweet(Integer.toString(i),cs, Timestamp.valueOf(LocalDateTime.now()),coordinates);
            tweetService.insertTweet(tweet);
            i++;
        }

        List<Tweet> tweets = tweetService.getAllTweets();
        for(Tweet tw : tweets){
            System.out.println(tw.toString());
        }

	}

}
