package com.example.renatodias.bets99notificationresult.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renatodias.bets99notificationresult.R;
import com.example.renatodias.bets99notificationresult.model.Jogo;
import com.example.renatodias.bets99notificationresult.model.Person;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PartidaActivity extends AppCompatActivity {

    TextView timeCasa;
    TextView timeFora;
    TextView dataPartida;
    EditText primeiroTempo;
    EditText segundoTempo;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        timeCasa = (TextView) findViewById(R.id.item_time_casa_partida);
        timeFora = (TextView) findViewById(R.id.item_time_Fora_partida);
        dataPartida = (TextView) findViewById(R.id.data_jogo_partida);
        primeiroTempo = (EditText) findViewById(R.id.valor_primeiro_tempo);
        segundoTempo = (EditText) findViewById(R.id.valor_segundo_tempo);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        id = bundle.getString("id");
        String casa = bundle.getString("casa");
        String fora = bundle.getString("fora");
        String data = bundle.getString("data");

        timeCasa.setText(casa);
        timeFora.setText(fora);
        dataPartida.setText(data);

    }

    public String getResultados(){
        return primeiroTempo.getText() + " , " + segundoTempo.getText();
    }

    public String getId() {
        return id;
    }

    public void PesquisarPartida(View view) {

        //pesquisar no google
        String tCasa = (String) timeCasa.getText();
        tCasa = tCasa.replace(" ", "+");

        String tFora = (String) timeFora.getText();
        tFora = tFora.replace(" ", "+");

        String pData = (String) dataPartida.getText();
        pData = pData.replace("/", "%2F");
        pData = pData.substring(0,14);

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q="+tCasa+"+vs+"+ tFora+ "+" + pData));
        startActivity(i);
    }

    public void buttonOk(View view) {
        new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {


            Person person = new Person();
            person.setResultadoJogo(getResultados());
            person.setId(getId());

            return POST(urls[0],person);
        }

    }

    public String POST(String url, Person person){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id", person.getId());
            jsonObject.accumulate("result", person.getResultadoJogo());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

//        private boolean validate(){
//            if(getResultadoJogo().trim().equals(""))
//                return false;
//            else
//                return true;
//        }
    private String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


}
