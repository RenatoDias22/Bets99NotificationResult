package com.example.renatodias.bets99notificationresult.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
//import com.hmkcode.android.vo.Person;

import com.example.renatodias.bets99notificationresult.Global.GlobalJogo;
import com.example.renatodias.bets99notificationresult.activitys.MainActivity;
import com.example.renatodias.bets99notificationresult.activitys.PartidaActivity;
import com.example.renatodias.bets99notificationresult.activitys.Splash;
import com.example.renatodias.bets99notificationresult.model.Jogo;
import com.example.renatodias.bets99notificationresult.R;
import com.example.renatodias.bets99notificationresult.model.JogoResponse;
import com.example.renatodias.bets99notificationresult.model.Person;

/**
 * Created by renatodias on 30/08/17.
 */

public class JogosAdapter extends  RecyclerView.Adapter<JogosAdapter.ViewHolder> {

    public static Context contextAdapter;

    public JogosAdapter(Context context){
        this.setContext(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTimecasa;
        public TextView itemTimeFora;
        public TextView dataJogo;
        public TextView idJogo;

        public ViewHolder(final View itemView) {
            super(itemView);

            itemTimecasa  = (TextView)itemView.findViewById(R.id.item_time_casa);
            itemTimeFora = (TextView) itemView.findViewById(R.id.item_time_Fora);
            dataJogo = (TextView) itemView.findViewById(R.id.data_jogo);
            idJogo = (TextView) itemView.findViewById(R.id.id_matche);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(getContext(),PartidaActivity.class);

                    Bundle bundle = new Bundle();

                    bundle.putString("id", (String) idJogo.getText());
                    bundle.putString("casa", (String) itemTimecasa.getText());
                    bundle.putString("fora", (String) itemTimeFora.getText());
                    bundle.putString("data", (String) dataJogo.getText());
                    intent.putExtras(bundle);

                    getContext().startActivity(intent);
                }
            });
        }

//        public String getResultadoJogo() {
//            return resultadoJogo.getText().toString();
//        }

//        private class HttpAsyncTask extends AsyncTask<String, Void, String> {
//            @Override
//            protected String doInBackground(String... urls) {
//
//                Person person = new Person();
//                person.setResultadoJogo(getResultadoJogo());
//                person.setId(id);
//
//                return POST(urls[0],person);
//            }
//
//        }

//        public String POST(String url, Person person){
//            InputStream inputStream = null;
//            String result = "";
//            try {
//
//                // 1. create HttpClient
//                HttpClient httpclient = new DefaultHttpClient();
//
//                // 2. make POST request to the given URL
//                HttpPost httpPost = new HttpPost(url);
//
//                String json = "";
//
//                // 3. build jsonObject
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.accumulate("id", person.getId());
//                jsonObject.accumulate("result", person.getResultadoJogo());
//
//                // 4. convert JSONObject to JSON to String
//                json = jsonObject.toString();
//
//                // ** Alternative way to convert Person object to JSON string usin Jackson Lib
//                // ObjectMapper mapper = new ObjectMapper();
//                // json = mapper.writeValueAsString(person);
//
//                // 5. set json to StringEntity
//                StringEntity se = new StringEntity(json);
//
//                // 6. set httpPost Entity
//                httpPost.setEntity(se);
//
//                // 7. Set some headers to inform server about the type of the content
//                httpPost.setHeader("Accept", "application/json");
//                httpPost.setHeader("Content-type", "application/json");
//
//                // 8. Execute POST request to the given URL
//                HttpResponse httpResponse = httpclient.execute(httpPost);
//
//                // 9. receive response as inputStream
//                inputStream = httpResponse.getEntity().getContent();
//
//                // 10. convert inputstream to string
//                if(inputStream != null)
//                    result = convertInputStreamToString(inputStream);
//                else
//                    result = "Did not work!";
//
//            } catch (Exception e) {
//                Log.d("InputStream", e.getLocalizedMessage());
//            }
//
//            // 11. return result
//            return result;
//        }
//
//        private boolean validate(){
//            if(getResultadoJogo().trim().equals(""))
//                return false;
//            else
//                return true;
//        }
//        private String convertInputStreamToString(InputStream inputStream) throws IOException{
//            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//            String line = "";
//            String result = "";
//            while((line = bufferedReader.readLine()) != null)
//                result += line;
//
//            inputStream.close();
//            return result;
//
//        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTimecasa.setText(GlobalJogo.resultadosPendentes.get(i).getNameHomeTeam());
        viewHolder.itemTimeFora.setText(GlobalJogo.resultadosPendentes.get(i).getNameFgTeam());
        viewHolder.dataJogo.setText(GlobalJogo.resultadosPendentes.get(i).getStartDate());
        viewHolder.idJogo.setText("(" + GlobalJogo.resultadosPendentes.get(i).getId() + ")");
    }

    @Override
    public int getItemCount() {
        return GlobalJogo.resultadosPendentes.size();
    }

    public void setContext(Context context) {
        this.contextAdapter = context;
    }

    public Context getContext() {
        return contextAdapter;
    }
}
