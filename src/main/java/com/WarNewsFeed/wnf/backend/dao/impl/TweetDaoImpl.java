package com.WarNewsFeed.wnf.backend.dao.impl;

import com.WarNewsFeed.wnf.backend.dao.TweetDao;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.backend.model.TweetText;
import com.WarNewsFeed.wnf.helpers.Triplet;
import com.WarNewsFeed.wnf.helpers.Tuple;
import edu.stanford.nlp.util.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO : change to UUID

@Repository
public class TweetDaoImpl extends JdbcDaoSupport implements TweetDao {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public int insertTweet(Tweet tweet) {
        String sql = "INSERT INTO TWEET "+"(TID, COUNTRY, TIME_STAMP, COORDINATES) VALUES (?, ?, ?, ?);";
        assert getJdbcTemplate() != null;
        int res = getJdbcTemplate().update(sql, tweet.getTid(), tweet.getCountry().toLowerCase(), tweet.getTime_stamp(), tweet.getCoordinates());
        return res;
    }

    @Override
    public int[] insertTweets(List<Tweet> tweets) {
        String sql = "INSERT INTO TWEET "+"(TID, COUNTRY, TIME_STAMP, COORDINATES) VALUES (?, ?, ?, ?)";
        assert getJdbcTemplate() != null;
        int[] k = getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Tweet tweet = tweets.get(i);
                preparedStatement.setString(1, tweet.getTid());
                preparedStatement.setString(2, tweet.getCountry().toLowerCase());
                preparedStatement.setTimestamp(3, tweet.getTime_stamp());
                preparedStatement.setString(4, tweet.getCoordinates());
            }

            @Override
            public int getBatchSize() {
                return tweets.size();
            }
        });
        return k;
    }

    @Override
    public List<Tweet> getAllTweets() {
        String sql = "SELECT * FROM tweet";
        assert getJdbcTemplate() != null;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Tweet> result = new ArrayList<Tweet>();
        for(Map<String, Object> row : rows){
            Tweet tweet = new Tweet();
            tweet.setTid((String) row.get("tid"));
            tweet.setCountry((String) row.get("country"));
            tweet.setTime_stamp((Timestamp) row.get("time_stamp"));
            tweet.setCoordinates((String) row.get("coordinates"));
            result.add(tweet);
        }
        return result;
    }

    @Override
    public Tweet getTweetById(String tweetId) {
        String sql = "SELECT * FROM tweet " +
                "WHERE tid = ?";
        assert getJdbcTemplate() != null;
        return getJdbcTemplate().queryForObject(sql, new Object[]{tweetId}, new RowMapper<Tweet>() {
            @Override
            public Tweet mapRow(ResultSet resultSet, int i) throws SQLException {
                Tweet tweet = new Tweet();
                tweet.setTid(resultSet.getString("tid"));
                tweet.setCountry(resultSet.getString("country"));
                tweet.setTime_stamp(resultSet.getTimestamp("time_stamp"));
                tweet.setCoordinates(resultSet.getString("coordinates"));
                return tweet;
            }
        });
    }

    @Override
    public List<Tweet> getTweetsByCountry(String country) {
        String sql = "SELECT * FROM tweet WHERE country = ?";
        assert getJdbcTemplate() != null;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, country);
        List<Tweet> res = new ArrayList<>();
        for(Map<String, Object> row : rows){
            Tweet tweet = new Tweet();
            tweet.setTid((String) row.get("tid"));
            tweet.setCountry((String) row.get("country"));
            tweet.setTime_stamp((Timestamp) row.get("time_stamp"));
            tweet.setCoordinates((String) row.get("coordinates"));
            res.add(tweet);
        }
        return res;
    }


    @Override
    public int getConflictsByCountry(String country) {
        String sql = "SELECT COUNT(COUNTRY) FROM TWEET WHERE COUNTRY ='"+country+"'";
        int count = getJdbcTemplate().queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public int findConflictsByTime(String country, Timestamp timeUp, Timestamp timeTo) {
        String sql = "SELECT COUNT(TIME_STAMP) FROM TWEET WHERE TIME_STAMP BETWEEN '"+timeUp+"' AND '"+timeTo+"' AND COUNTRY='"+country+"'";
        int countOfConflictsNamedAtUserInput = this.getJdbcTemplate().queryForObject(sql, Integer.class);
        return countOfConflictsNamedAtUserInput;
    }

    @Override
    public List<Tweet> sortByCountry() {
        String sql = "SELECT COUNTRY FROM TWEET ORDER BY COUNTRY ASC " ;
        assert getJdbcTemplate() != null;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Tweet> result = new ArrayList<Tweet>();
        for(Map<String, Object> row : rows){
            Tweet tweet = new Tweet();
            tweet.setCountry((String) row.get("country"));
            result.add(tweet);
        }
        return result;
    }

    @Override
    public Map<Object,Object> showCountOfEveryCountry(){
        String sql = "SELECT TWEET.COUNTRY, COUNT(*) AS CONFLICTS FROM TWEET GROUP BY COUNTRY" ;
        assert getJdbcTemplate() != null;
        List <Map<String,Object>> queryRow = getJdbcTemplate().queryForList(sql);
        Map<Object,Object> result = new HashMap<>();
        for(Map<String,Object> row : queryRow){
            Object country = row.get("country");
            Object count = row.get("conflicts");
            result.put(country , count);
        }

        return result;
    }

    @Override
    public List<TweetText> getAllTweetText() {
        String sql = "SELECT * FROM TWEET_TEXT";
        assert getJdbcTemplate() != null;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<TweetText> result = new ArrayList<TweetText>();
        for(Map<String, Object> row : rows){
            TweetText tweetText = new TweetText();
            tweetText.setText((String) row.get("tw_text"));
            result.add(tweetText);
        }
        return result;
    }

    @Override
    public int insertTweetText(TweetText text) {
        String sql = "INSERT INTO TWEET_TEXT "+"(TW_TEXT) VALUES (?);";
        assert getJdbcTemplate() != null;
        int res = getJdbcTemplate().update(sql, text.getText());
        return res;
    }

    @Override
    public int[] insertTweetsTexts(List<TweetText> tweetsTexts) {
        String sql = "INSERT INTO TWEET_TEXT "+"(TW_TEXT) VALUES (?)";
        assert getJdbcTemplate() != null;
        int[] k = getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                TweetText text = tweetsTexts.get(i);
                preparedStatement.setString(1, text.getText());
            }

            @Override
            public int getBatchSize() {
                return tweetsTexts.size();
            }
        });
        return k;
    }

    @Override
    public List<Triplet> getConflictsWithCoordinate() {
        String sql = "SELECT TWEET.COUNTRY,COORDINATES, COUNT(*) AS CONFLICTS FROM TWEET GROUP BY COUNTRY,COORDINATES" ;
        assert getJdbcTemplate() != null;
        List <Map<String,Object>> queryRow = getJdbcTemplate().queryForList(sql);
        List<Triplet> result = new ArrayList<>();
        for(Map<String,Object> row : queryRow){
            Triplet triplet = new Triplet(row.get("country"),row.get("conflicts"),row.get("coordinates"));
            result.add(triplet);
        }

        return result;
    }


}
