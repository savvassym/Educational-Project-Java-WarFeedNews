package com.WarNewsFeed.wnf.Backend.NLP;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Tokenizer {


    public String tokenize(String input) {
//        List<String> list = null;
//        for(int i =0;i<input.size();i++){
//           list = Arrays.asList(input.get(i).split("-|,|\\s"));
//        }
//        return String.valueOf(list);
        return Arrays.toString(input.split("-|,|\\s"));
    }
}

