package com.WarNewsFeed.wnf;

import com.WarNewsFeed.wnf.backend.controller.TweetController;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.service.TweetService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Notification {
    private Boolean notify = false;
    private Timestamp timestamp1,timestamp2;
    private Timestamp timestamp3;
    private int countInt;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    private void setTimestamp3() throws ParseException {
        Date date = sdf.parse("2019.0.1.00.00.00");
        Date date2= sdf.parse("2019.0.1.00.14.00");
        long time = date2.getTime()-date.getTime();
        timestamp3 = new Timestamp(time);
    }

    private void getTimestampsFromGivenCountry(TweetService tweetService, Object rowCountry)
    {
        List<Tweet> tweets = tweetService.getTweetsByCountry(rowCountry.toString());
        timestamp1 = tweets.get(0).getTime_stamp();
        timestamp2 = tweets.get(tweets.size()-1).getTime_stamp();
    }

    public Boolean notifyUser (TweetService tweetService, Map<Object,Object> count) throws ParseException {

        for (Object row : count.keySet()) {
            countInt = Integer.parseInt(count.get(row).toString());
            if (countInt>=16)
            {
                getTimestampsFromGivenCountry(tweetService,row);
                setTimestamp3();
                if (timestamp3.getTime()<(timestamp2.getTime()-timestamp1.getTime()))
                    return notify=true;
            }
        }
        return  notify;
    }
}
