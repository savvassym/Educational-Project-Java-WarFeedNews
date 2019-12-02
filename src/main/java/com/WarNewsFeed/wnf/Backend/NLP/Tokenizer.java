package com.WarNewsFeed.wnf.Backend.NLP;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Tokenizer {


    public String tokenize(String input) {
        if(input!=null) {
            return Arrays.toString(input.split("-|,|\\s"));
        }
        else {
            return "Null String not allowed";
        }
    }
}
