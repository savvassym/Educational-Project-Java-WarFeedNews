package com.WarNewsFeed.wnf.Backend.NLP;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NlpTest {

    @Test
    public void testThatGreeceIsRecognisedAsACountryAndAthensAsACity() {
        Map<String, String> haslist;
        Map<String, String> expected = new HashMap<>();
        Nlp nlp = new Nlp();
        haslist = nlp.analyzer("Greece Athens");
        expected.put("Greece","COUNTRY");
        expected.put("Athens","CITY");
        assertEquals(haslist,expected);
    }


    @Test
    public void TestThatCanHandleEmptyString(){
        Map<String,String> actual = new HashMap<>();
        Map<String,String> expected = new HashMap<>();
        Nlp nlp = new Nlp();
        actual = nlp.analyzer("");
        assertEquals(expected,actual);
    }


    @Test
    public void testThatCountryIsRecognisedWhenMixedWithOtherWords(){
        Map<String, String> list;
        Map<String, String> expected = new HashMap<>();
        Nlp nlp = new Nlp();
        list = nlp.analyzer("It takes a lifetime for someone to discover Greece, but it only takes an instance to fall in love with her.");
        expected.put("but","O");
        expected.put("love","O");
        expected.put("a","O");
        expected.put("discover","O");
        expected.put("instance","O");
        expected.put("in","O");
        expected.put("lifetime","O");
        expected.put("for","O");
        expected.put("It","O");
        expected.put("it","O");
        expected.put(",","O");
        expected.put("an","O");
        expected.put(".","O");
        expected.put("Greece","COUNTRY");
        expected.put("with","O");
        expected.put("someone","O");
        expected.put("fall","O");
        expected.put("her","O");
        expected.put("only","O");
        expected.put("to","O");
        expected.put("takes","O");
        assertEquals(list,expected);
    }
}