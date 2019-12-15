package com.WarNewsFeed.wnf.backend.nlp;

import com.WarNewsFeed.wnf.helpers.Tuple;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4ClassRunner.class)
public class NlpTest {

    @Test
    public void testThatGreeceIsRecognisedAsACountryAndAthensAsACity() {
        List<Tuple<String,String>> actual;
        Nlp nlp = new Nlp();
        actual = nlp.analyzer("Greece Athens");
        Tuple<String,String> tp = new Tuple<>("Greece","COUNTRY");
        Tuple<String,String> tp2 = new Tuple<>("Athens","CITY");
        List<Tuple<String,String>> expected = new ArrayList<>();
        expected.add(tp);
        expected.add(tp2);
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void TestThatCanHandleEmptyString(){
        List<Tuple<String,String>> actual;
        Nlp nlp = new Nlp();
        Tuple<String,String> tp = new Tuple<>("","");
        actual = nlp.analyzer("");
        List<Tuple<String,String>> expected = new ArrayList<>();
        expected.add(tp);
        Assert.assertEquals(expected,actual);
    }

}