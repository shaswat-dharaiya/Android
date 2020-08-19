package com.example.mysql;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;
    String type = "", post_data = "", rows="",dataRows = "";
    public String[] lines;

    BackgroundWorker (Context ctx){
        context = ctx;
    }

    String id = "",name = "", surname,age = "",username = "",password = "";
    BackgroundWorker (String id,String name,String surname,String age,String username,String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(String... voids) {
        type = voids[0];
//        String lg_url = "http://192.168.0.104/mysql";
            String lg_url = "http://10.0.2.2/mysql";

        try {
        if (type.equals("login")) {
            lg_url += "/login.php";
            String usr = "", psd = "";
            usr = voids[1];
            psd = voids[2];
            post_data = URLEncoder.encode("usr_name", "UTF-8") + "=" + URLEncoder.encode(usr, "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(psd, "UTF-8");
        }
        if (type.equals("register")) {
            lg_url += "/register.php";
            post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(voids[1],"UTF-8")+"&"
                    +URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(voids[2],"UTF-8")+"&"
                    +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(voids[3],"UTF-8")+"&"
                    +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(voids[4],"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(voids[5],"UTF-8");
        }
        if (type.equals("showData")){
            lg_url += "/showData.php";
        }
        {
            URL url = new URL(lg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

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
        }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (ProtocolException ex) {
            ex.printStackTrace();
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception e){
                e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(type+" Status");
    }

    @Override
    protected void onPostExecute(String result) {
        lines = result.split("\n\n");
//        alertDialog.setMessage(result);
//        alertDialog.show();

        if(lines[0].equals("Success") && type.equals("login")){
            Intent intent = new Intent(".RegisterEmploy");
            context.startActivity(intent);
        }
        if(type.equals("showData")){
            Intent intent = new Intent(context,AllData.class);
            intent.putExtra("result", result);
            context.startActivity(intent);


        }

    }
    public String returnRows(){
        return dataRows;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
