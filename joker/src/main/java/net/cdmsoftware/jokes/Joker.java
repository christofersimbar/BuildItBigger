package net.cdmsoftware.jokes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.json.*;

import sun.rmi.runtime.Log;

import static java.awt.SystemColor.info;

public class Joker {
    static String JOKE_API_URL = "http://api.icndb.com/jokes/random/";

    public String getJoke() throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        Random randomNum = new Random();
        URL url = new URL(JOKE_API_URL);

        // Create the request to API, and open the connection
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.connect();

        // Read the input stream into a String
        InputStream inputStream = urlConnection.getInputStream();
        StringBuilder buffer = new StringBuilder();
        if (inputStream == null) {
            // Nothing to do.
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            // add newline to make debugging easier
            buffer.append(line).append("\n");
        }

        if (buffer.length() > 0) {
            JSONObject jsonObject = new JSONObject(buffer.toString());
            return jsonObject.getJSONObject("value").getString("joke");
        } else {
            return null;
        }
    }
}
