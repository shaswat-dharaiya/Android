package com.example.nodemcu;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conntvt extends AsyncTask<Boolean,Void,String> {

    Context context;
    Conntvt (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(Boolean... voids) {
        Boolean state = voids[0];
        try {
//            Toast.makeText(context, state.toString(), Toast.LENGTH_SHORT).show();
            URL url = new URL("http://192.168.0.110/"+state.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line + "\n";
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        MainActivity.stateLED.setText(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
