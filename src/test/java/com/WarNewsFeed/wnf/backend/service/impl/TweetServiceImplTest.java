package com.WarNewsFeed.wnf.backend.service.impl;

import com.WarNewsFeed.wnf.WnfApplication;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.model.TweetText;
import com.WarNewsFeed.wnf.backend.service.TweetService;
import com.WarNewsFeed.wnf.helpers.Triplet;
import com.WarNewsFeed.wnf.helpers.Tuple;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class TweetServiceImplTest{
   // @Autowired
    TweetService service;
    private ApplicationContext context;

    @Before
    public void setUp()
    {
        context = SpringApplication.run(WnfApplication.class);
        service = context.getBean(TweetService.class);
    }


    @Test
    public void TestThatICanInsertTweetOnDB(){
        Tweet tweet = new Tweet();
        tweet.setTid("2");
        tweet.setCountry("Germany");
        tweet.setTime_stamp(Timestamp.valueOf("2019-11-21 14:19:30.554"));
        tweet.setCoordinates("2393281");
        int actual = service.insertTweet(tweet);
        int expected =1;
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void TestThatICanInsertListOfTweetsOnDB() {
        Tweet tweet = new Tweet();
        tweet.setTid("3");
        tweet.setCountry("TestCountry3");
        tweet.setTime_stamp(Timestamp.valueOf("2019-11-21 14:41:20.55"));
        tweet.setCoordinates("1234");
        Tweet tweet1 = new Tweet();
        tweet1.setTid("4");
        tweet1.setCountry("TestCountry4");
        tweet1.setTime_stamp(Timestamp.valueOf("2019-11-21 14:41:20.55"));
        tweet1.setCoordinates("4567");
        List<Tweet> list = new ArrayList<>();
        list.add(tweet);
        list.add(tweet1);
        int[] actual = service.insertTweets(list);
        int[] expected = {1,1};
        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void TestThatDatabaseResponseToGetAllTweetsQuery() {
        Tweet tweet = new Tweet("1","Greece",Timestamp.valueOf("2019-11-21 14:41:20.55"),"3283812");
        service.insertTweet(tweet);
        List<Tweet> actual = service.getAllTweets();
        Assert.assertTrue(actual.size()>0);
    }

    @Test
    public void TestThatDatabaseResponseToGetTweetByID() {
        Tweet tweet = new Tweet("5","France",Timestamp.valueOf("2017-11-21 14:41:20.55"),"2131231");
        service.insertTweet(tweet);
        Tweet response = service.getTweetById("5");
        Assert.assertNotNull(response);
    }

    @Test
    public void TestThatDatabaseResponseToGetTweetsByCountry() {
        Tweet tweet = new Tweet("6","Germany",Timestamp.valueOf("2019-11-21 14:41:20.55"),"3283812");
        service.insertTweet(tweet);
        String input = "Germany";
        List<Tweet> response = service.getTweetsByCountry(input.toLowerCase());
        Assert.assertTrue(response.size()>0);
    }

    @Test
    public void TestThatDbResponseToQueryFindConflictsByTimestamp() {
        Tweet tweet = new Tweet("10","Germany",Timestamp.valueOf("2019-11-21 14:19:10.554"),"129843");
        service.insertTweet(tweet);
        String string ="germany";
        int response = service.findConflictsByTime(string.toLowerCase(), Timestamp.valueOf("2019-11-21 14:19:10.554"), Timestamp.valueOf("2019-11-25 22:42:48.000000000"));
        Assert.assertTrue(response>0);
    }

    @Test
    public void sortByCountry() {
        Tweet tweet = new Tweet("7","Germany",Timestamp.valueOf("2019-11-21 14:41:20.55"),"3283812");
        Tweet tweet1 = new Tweet("8","France",Timestamp.valueOf("2017-11-21 14:41:20.55"),"2131231");
        service.insertTweets(List.of(tweet,tweet));
        List<Tweet> response = service.sortByCountry();
        Assert.assertTrue(response.size()>0);
    }

    @Test
    public void getConflictsByCountryTest() {
        Tweet tweet = new Tweet("9","Greece",Timestamp.valueOf("2019-11-21 14:41:20.55"),"3283812");
        service.insertTweet(tweet);
        String inp = "greece";
        int response = service.getConflictsByCountry(inp.toLowerCase());
        Assert.assertEquals(response>0, response>0);
    }

    @Test
    public void showCountOfEveryCountryTest(){
        Tweet tweet = new Tweet("10","Lithuania",Timestamp.valueOf("2019-12-22 16:16:24.54"),"38.9597594  34.9249653");
        Tweet tweet1 = new Tweet("11","Iraq",Timestamp.valueOf("2019-12-22 16:16:24.54"),"33.0955793  44.1749775");
        service.insertTweets(List.of(tweet,tweet1));
        Map<Object,Object> result = service.showCountOfEveryCountry();
        Assert.assertTrue(result.size()>0);
    }


    @Test
    public void getAllTweetText() {
        TweetText text = new TweetText("tweet text inserting.");
        service.insertTweetText(text);
        List<TweetText> tweetTextList = service.getAllTweetText();
        Assert.assertTrue(tweetTextList.size()>0);
    }

    @Test
    public void insertTweetText() {
        TweetText text = new TweetText("Tweet text test insert.");
        int expected = 1;
        int actual = service.insertTweetText(text);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void insertTweetsTexts() {
        TweetText text1 = new TweetText("Tweet text insert test as list test1");
        TweetText text2 = new TweetText("Tweet text insert test as list test2");
        int[] expected = {1,1};
        int[] actual = service.insertTweetsTexts(Arrays.asList(text1,text2));
        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void getConflictsWithCoordinate() {
        Tweet tweet = new Tweet("15","Lithuania",Timestamp.valueOf("2019-12-22 16:16:24.54"),"38.9597594  34.9249653");
        Tweet tweet1 = new Tweet("16","Iraq",Timestamp.valueOf("2019-12-22 16:16:24.54"),"33.0955793  44.1749775");
        service.insertTweets(List.of(tweet,tweet1));
        List<Triplet> result = service.getConflictsWithCoordinate();
        Assert.assertTrue(result.size()>0);
    }


    @After
    public void tearDown()
    {
        SpringApplication.exit(context, () -> 0);
    }


}