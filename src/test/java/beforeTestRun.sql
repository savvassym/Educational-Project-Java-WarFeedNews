DROP TABLE IF EXISTS tweet;

CREATE TABLE tweet (
    tid varchar(10) not null,
    country varchar (40) not null,
    time_stamp timestamp,
    coordinates varchar(100)
);

DROP TABLE IF EXISTS TWEET_TEXT;

CREATE TABLE tweet_text(
    tw_text text
);