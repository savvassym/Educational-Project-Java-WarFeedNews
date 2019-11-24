package com.WarNewsFeed.wnf.Backend.Parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadingInput {

    public static String GettingInput() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please write which COUNTRY you want to know how many conflicts had : ");
        String userInput = null;
        try {
            userInput = br.readLine().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInput;

    }
}
