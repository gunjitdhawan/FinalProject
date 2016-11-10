package com.example;

import java.util.ArrayList;
import java.util.Random;

public class JokeGenerator {

    public String generateJoke()
    {
        return "This is a joke";
    }
    ArrayList<String> jokeList = new ArrayList<>();


    public String getRandomJoke() {

        jokeList.add("Joke 11");
        jokeList.add("Joke 21");
        jokeList.add("Joke 31");
        jokeList.add("Joke 41");
        jokeList.add("Joke 51");
        jokeList.add("Joke 61");
        jokeList.add("Joke 71");
        jokeList.add("Joke 81");
        jokeList.add("Joke 91");
        jokeList.add("Joke 101");

        int index = new Random().nextInt(jokeList.size());
        String randomItem = jokeList.get(index);
        return randomItem;
    }

}
