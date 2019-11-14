package com.WarNewsFeed.wnf;

import com.WarNewsFeed.wnf.Backend.API.TwitterApi;
import com.WarNewsFeed.wnf.Backend.NLP.Nlp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class WnfApplication {

	public static void main(String[] args) throws TwitterException, SQLException, ClassNotFoundException {
		SpringApplication.run(WnfApplication.class, args);
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
		collect.forEach((key,value)-> System.out.println(key + " : "+ value));

		long stopTime = System.currentTimeMillis();
		System.out.println(stopTime-startTime);



	}

}
