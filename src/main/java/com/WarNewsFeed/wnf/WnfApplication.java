package com.WarNewsFeed.wnf;

import com.WarNewsFeed.wnf.backend.api.GeocodeApi;
import com.WarNewsFeed.wnf.backend.api.TwitterApi;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.nlp.Nlp;
import com.WarNewsFeed.wnf.backend.nlp.Tokenizer;
import com.WarNewsFeed.wnf.backend.service.TweetService;
import com.WarNewsFeed.wnf.backend.service.TwitterApiService;
import com.WarNewsFeed.wnf.helpers.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import twitter4j.TwitterException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class WnfApplication {
	@Autowired
	TweetService tweetService;
	@Autowired
    Nlp filter;


	public static void main(String[] args) throws ParseException {
		ApplicationContext context = SpringApplication.run(WnfApplication.class, args);
		TweetService tweetService = context.getBean(TweetService.class);
		Nlp filter  = context.getBean(Nlp.class);

        GeocodeApi geocodeApi = new GeocodeApi();
		String coordinates;
		Notification notification = new Notification();
		TwitterApi twitterApi = new TwitterApi();
        TwitterApiService twitterApiService = new TwitterApiService(twitterApi);
        List<String> tweetTexts = twitterApiService.getTweets();
        List<String> countries = new ArrayList<>();
        List<Tuple<String,String>> collection = new ArrayList<>();



        for (String st : tweetTexts){
            System.out.println(st);
            collection=filter.analyzer(st);
        }


        for(int i=0;i<collection.size();i++){
            int k = i+1;
            System.out.println(collection.get(i));
            if (collection.get(i).val2.equals("COUNTRY")){
                if(collection.get(k).val2.equals("COUNTRY")){
                    countries.add(collection.get(i).val1+" "+ collection.get(k).val1);
                    i=k;
                    continue;
                }
                countries.add(collection.get(i).val1);
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

       Map<Object,Object> count = tweetService.showCountOfEveryCountry();
        for (Object row : count.keySet()) {
            System.out.println( row +" , "+ count.get(row));
        }


        notification.notifyUser(tweetService,count);


    }


}
