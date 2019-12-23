package com.WarNewsFeed.wnf.backend.dao.impl;

import com.WarNewsFeed.wnf.backend.dao.TweetDao;
import com.WarNewsFeed.wnf.backend.model.Tweet;
import com.WarNewsFeed.wnf.helpers.Tuple;
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

    private List<Tuple<String,String>> result = new ArrayList<>();

    @Override
    public List<Tuple<String,String>> showCountOfEveryCountry(){
        String sql = "SELECT TWEET.COUNTRY, COUNT(*) AS CONFLICTS FROM TWEET GROUP BY COUNTRY" ;
        assert getJdbcTemplate() != null;

        List <Map<String,Object>> queryRow = getJdbcTemplate().queryForList(sql);
        for(Map<String,Object> row : queryRow){
            Tuple tuple = new Tuple<>(row.get("country"),(row.get("conflicts")));
            result.add(tuple);
        }

        return result;
    }


}
