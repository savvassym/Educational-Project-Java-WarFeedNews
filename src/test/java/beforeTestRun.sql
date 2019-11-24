DROP TABLE IF EXISTS tweet;

CREATE TABLE tweet (
    tid varchar(10) not null,
    country varchar (40) not null,
    time_stamp timestamp,
    coordinates varchar(100)
);

INSERT INTO tweet values('1','Greece','2019-11-21 14:19:30.554','1234');
INSERT INTO tweet values('5','Sweden','2019-11-21 14:19:30.554','1234');