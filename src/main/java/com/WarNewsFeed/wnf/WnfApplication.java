package com.WarNewsFeed.wnf;

import com.WarNewsFeed.wnf.Backend.API.GeocodeApi;
import com.WarNewsFeed.wnf.Backend.Parsing.ReadingInput;
import com.WarNewsFeed.wnf.Backend.model.Tweet;
import com.WarNewsFeed.wnf.Backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import twitter4j.TwitterException;

import java.sql.SQLException;
import java.util.*;

@SpringBootApplication
public class WnfApplication {
	@Autowired
	TweetService tweetService;

	public static void main(String[] args) throws TwitterException, SQLException {
		ApplicationContext context = SpringApplication.run(WnfApplication.class, args);
		TweetService tweetService = context.getBean(TweetService.class);
		Date date = new Date();
		long time = date.getTime();
		List<Tweet> listok = new ArrayList<>();
		listok=tweetService.getTweetsByCountry("Greece");
		for(Tweet t: listok){
			System.out.println(t.toString());
		}

		List<String> clist = Arrays.asList("Greece","France","Germany","England");
//		Tweet tweet = new Tweet();
//		tweet.setTid("5");
//		tweet.setCountry("TestTimeStamp2");
//		tweet.setTime_stamp(Timestamp.valueOf("1983-07-12 21:30:55.888"));
//		tweet.setCoordinates("3428349238");

//		for(int i=0;i<=3;i++){
//			Tweet tweet1 = new Tweet();
//			tweet1.setTid(Integer.toString(i));
//			tweet1.setCountry(clist.get(i));
//			tweet1.setTime_stamp(new Timestamp(time));
//			tweet1.setCoordinates(Double.toString(Math.random()));
//			tweetService.insertTweet(tweet1);
//		}

//		Tweet tweet1 = new Tweet();
//		tweet1.setTid("5");
//		tweet1.setCountry("Turkey");
//		tweet1.setTime_stamp(new Timestamp(time));
//		tweet1.setCoordinates(Double.toString(Math.random()));
//
//
//		Tweet tweet2 = new Tweet();
//		tweet2.setTid("6");
//		tweet2.setCountry("Israel");
//		tweet2.setTime_stamp(new Timestamp(time));
//		tweet2.setCoordinates(Double.toString(Math.random()));
//
//		List<Tweet> l = new ArrayList<>();
//		l.add(tweet1);
//		l.add(tweet2);
//
//		int k[] = tweetService.insertTweets(l);
//
//		tweetService.getAllTweets();



//		tweetService.getAllTweets();

		String userInput;
		userInput = ReadingInput.GettingInput();
		tweetService.getConflictsByCountry(userInput);



		Map<Double,Double> test = new HashMap<>();
		//In country we put the userInput
		GeocodeApi geocodeApi = new GeocodeApi();
		test=geocodeApi.getCoordinates("greece");


//	    List<Status> status;
//		TwitterApi twitterApi = new TwitterApi();
//		status = twitterApi.getTwitterPosts();
//		for(Status st : status) {
//			System.out.println(st.getUser().getName()+"========"+st.getText());
//		}


//		Nlp filter = new Nlp();
//		Map<String, String> collect;
//		long startTime = System.currentTimeMillis();
//		collect = filter.analyzer("It takes a lifetime for someone to discover Greece, but it only takes an instance to fall in love with her.");
//		long stopTime = System.currentTimeMillis();
//		long ms = stopTime-startTime;
//		long sec = ms/1000;
//		collect.forEach((key,value)-> System.out.println(key + " : "+ value));
//		System.out.println(sec);
//		test.forEach((key,value)-> System.out.println(key + ":"+value));

	}

}
