package com.appkinng.jokedisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);

        String joke = getJoke();

        printJoke(joke);
    }

    public String getJoke() {

        Intent i = getIntent();
        return i.getStringExtra("joke");

    }

    private void printJoke(String joke) {
        if(joke!=null && joke.length()>0)
        {
            ((TextView)findViewById(R.id.joke_tv)).setText(joke);
        }
        else
        {
            ((TextView)findViewById(R.id.joke_tv)).setText("Error retrieving joke!");
        }
    }
}
