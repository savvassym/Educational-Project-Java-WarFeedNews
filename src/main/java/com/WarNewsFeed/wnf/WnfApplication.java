package com.WarNewsFeed.wnf;

import Backend.API.TwitterApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

@SpringBootApplication
public class WnfApplication {

	public static void main(String[] args) throws TwitterException {
		//SpringApplication.run(WnfApplication.class, args);

		List<Status> status;
		TwitterApi twitterApi = new TwitterApi();

		twitterApi.twitterConnection();
		status = twitterApi.getData();
		for(Status st : status) {
			System.out.println(st.getUser().getName()+"========"+st.getText());
		}
	}

}
