package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appkinng.jokedisplayer.JokeDisplayerActivity;
import com.example.gunjit.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

public class MainActivity extends ActionBarActivity {

    Button getJokesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJokesBtn = (Button)  findViewById(R.id.btn);
        getJokesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getConnectivityStatus(MainActivity.this)!=-100)
                {
                    new EndpointsAsyncTask().execute(new Pair<Context, String>(MainActivity.this, "Get Jokes"));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getJokesBtn.setText("Loading...");
        }

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://builditbiggernew.appspot.com/_ah/api/");
                Log.e("-----", "1");
                myApiService = builder.build();
            }

            context = params[0].first;

            try {
                return myApiService.getJokes().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("-----", "2");
            getJokesBtn.setText("Get Joke");
            handleResponse(result);
            Intent i = new Intent(MainActivity.this, JokeDisplayerActivity.class);
            i.putExtra("joke", result);
            startActivity(i);
        }
    }

    /**
     * This is a method that will be overridden in order to test response.
     */
    @VisibleForTesting
    public void handleResponse(String apiResponse) {
        // handle login response here
        Log.e("-----", "55");

    }

    public int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return -100;
    }


}


