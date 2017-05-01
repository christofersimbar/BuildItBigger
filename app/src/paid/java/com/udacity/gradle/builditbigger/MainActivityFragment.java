package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.cdmsoftware.jokes.JokeActivity;

import java.util.concurrent.ExecutionException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    static String EXTRA_JOKE_TEXT = "jokeText";
    private ProgressBar spinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        spinner = (ProgressBar) root.findViewById(R.id.progressBar);

        Button button = (Button) root.findViewById(R.id.tell_joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJokeActivity();
            }
        });
        return root;
    }

    private void loadJokeActivity() {
        String jokeText = "";
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        try {
            spinner.setVisibility(View.VISIBLE);
            jokeText = new EndpointsAsyncTask().execute().get();
            spinner.setVisibility(View.GONE);

            if (jokeText != null) {
                intent.putExtra(EXTRA_JOKE_TEXT, jokeText);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), R.string.joke_load_failed, Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
