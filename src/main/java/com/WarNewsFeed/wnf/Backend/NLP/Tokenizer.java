package com.WarNewsFeed.wnf.Backend.NLP;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Tokenizer {


    public String tokenize(String input) {
        return Arrays.toString(input.split("-|,|\\s"));
    }
}

