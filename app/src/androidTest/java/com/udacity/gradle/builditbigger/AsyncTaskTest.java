package com.udacity.gradle.builditbigger;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Christofer Simbar on 01/05/2017.
 */

public class AsyncTaskTest {
    @Test
    public void getJoke() throws ExecutionException, InterruptedException {
        String joke = new EndpointsAsyncTask().execute().get();
        assertEquals(joke.length() > 0, true);
    }
}
