package Backend.API;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;



public class TwitterApi {

    public Twitter twitterConnection() throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ItDPkkepxkQkURBA25cWSsXyN")
                .setOAuthConsumerSecret("1Pf8wcBaQcjIWmOLPZKwX44NWdOahfItZBDl05wWD9P60G831u")
                .setOAuthAccessToken("1186991541312770050-zwZ4K57FkbvvmgNiuJ5ld9oQSbtgu2")
                .setOAuthAccessTokenSecret("T8MFqBElhtxZ4KcelRdj90yFVXnRi2DCgOoo2fCNchgAu");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();
        return twitter;
    }

    public List<Status> getData() throws TwitterException {
        Twitter twitter = twitterConnection();
        List<Status> status = twitter.getHomeTimeline();
        return status;
    }

}
