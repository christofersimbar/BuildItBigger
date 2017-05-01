package net.cdmsoftware.jokes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    static String EXTRA_JOKE_TEXT = "jokeText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getIntent() != null) {
            TextView textView = (TextView) findViewById(R.id.jokeTextView);
            textView.setText(Html.fromHtml(getIntent().getStringExtra(EXTRA_JOKE_TEXT)));
        }
    }
}
