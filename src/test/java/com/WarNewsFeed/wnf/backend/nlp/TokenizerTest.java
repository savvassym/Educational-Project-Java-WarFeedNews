package com.WarNewsFeed.wnf.backend.nlp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TokenizerTest {

    @Test
    public void TestThatTokenizerWorksWell() {
        String input = "Hello -world, -test";
        Tokenizer tokenizer = new Tokenizer();
        String actual = tokenizer.tokenize(input);
        String expected = Arrays.toString(new String[]{"Hello, , world, , , test"});
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void TestThatTokenizerCanHandleEmptyString(){
        String input = "";
        Tokenizer tokenizer = new Tokenizer();
        String actual = tokenizer.tokenize(input);
        String expected = Arrays.toString(new String[]{""});
        Assert.assertEquals(expected,actual);
    }

}