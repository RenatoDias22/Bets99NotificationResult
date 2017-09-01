package com.example.renatodias.bets99notificationresult.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.renatodias.bets99notificationresult.Global.GlobalJogo;
import com.example.renatodias.bets99notificationresult.R;
import com.example.renatodias.bets99notificationresult.model.JogoResponse;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by renatodias on 01/09/17.
 */

public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);
        new HttpAsyncTask().execute("http://api.bets99.com/index.php/apimobile/listNoResultGames");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public String GET(String url){
        InputStream inputStream = null;
        String stringJson = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null) {
                stringJson = convertInputStreamToString(inputStream);
                Gson gson = new Gson();
                JogoResponse matches = gson.fromJson(stringJson, JogoResponse.class);
                GlobalJogo.resultadosPendentes = matches.getMatches();
//                recyclerView.setVisibility(View.VISIBLE);
//                progress.setVisibility(View.GONE);
//                adapter.notifyDataSetChanged();

            } else
                stringJson = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return stringJson;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private class  HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }
    }

}
