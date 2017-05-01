package net.cdmsoftware.jokes.backend;

import net.cdmsoftware.jokes.Joker;

import java.io.IOException;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {
    private String myData;

    public String getData() {
        Joker joker = new Joker();
        try {
            return joker.getJoke();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}