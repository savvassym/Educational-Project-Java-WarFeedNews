package com.WarNewsFeed.wnf;

import Backend.API.TwitterApi;
import com.WarNewsFeed.wnf.NLP.Nlp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.sql.SQLException;
import java.util.*;

@SpringBootApplication
public class WnfApplication {

	public static void main(String[] args) throws TwitterException, SQLException, ClassNotFoundException {
		SpringApplication.run(WnfApplication.class, args);
		List<Status> status;
		TwitterApi twitterApi = new TwitterApi();
		twitterApi.twitterConnection();
		status = twitterApi.getData();
		for(Status st : status) {
			System.out.println(st.getUser().getName()+"========"+st.getText());
		}

		Nlp filter = new Nlp();
		Map<String, String> collect;
		collect = filter.analyzer("It takes a lifetime for someone to discover Greece, but it only takes an instance to fall in love with her.");
		collect.forEach((key,value)-> System.out.println(key + " : "+ value));




	}

}
