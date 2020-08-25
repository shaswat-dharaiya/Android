package com.example.mysql;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

public class BackgroundMongo extends AsyncTask<String, Void, String> {


    Context context;
    AlertDialog alertDialog;
    String type = "",username = "",bools="";
    public String[] lines;

    BackgroundMongo (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        type = voids[0];
//        String lg_url = "http://192.168.0.104/mongodb";
        String lg_url = "http://10.0.2.2/mongodb";
        String[] colName = {"name", "surname", "age", "username", "password"};
        String post_data = "";
        try {
            if (type.equals("login")) {
                String usr = "", psd = "";
                lg_url += "/login.php";
                usr = voids[1];
                psd = voids[2];
                post_data = URLEncoder.encode("usr_name", "UTF-8") + "=" + URLEncoder.encode(usr, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(psd, "UTF-8");
            }
            if (type.equals("register")) {
                lg_url += "/register.php";
//                Toast.makeText(context,"Registration process is on",Toast.LENGTH_SHORT).show();
                for(int i= 0; i<colName.length;i++){
                    post_data += URLEncoder.encode(colName[i],"UTF-8")+"="+URLEncoder.encode(voids[i+1],"UTF-8");
                    if(i<colName.length-1){
                        post_data += "&";
                    }
                }
//                Toast.makeText(context,"Please wait. Registration process is on",Toast.LENGTH_SHORT).show();
            }
            if (type.equals("showData")){
                lg_url += "/showData.php";
            }
            if (type.equals("update")){
                lg_url += "/verifyUser.php";
                bools = voids[1];
                username = voids[2];
                post_data += URLEncoder.encode("verUpd","UTF-8")+"="+URLEncoder.encode(bools,"UTF-8")+"&";
                post_data += URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                if(bools.equals("true")){
                    for(int i= 0; i<colName.length;i++) {
                        post_data += "&" + URLEncoder.encode(colName[i], "UTF-8") + "=" + URLEncoder.encode(voids[i + 3], "UTF-8");
                    }
                }
            }
            if (type.equals("delete")) {
                lg_url += "/delete.php";
                String userID = voids[1];
                post_data += URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8");
//                Toast.makeText(context,
//                        "Following Users will be affected"+username.replaceAll(":","\n"),
//                        Toast.LENGTH_SHORT).show();
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
            alertDialog.setTitle("UnsupportedEncodingException");
            alertDialog.setMessage(e.toString());
            alertDialog.setCancelable(true);
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            e.printStackTrace();
        }
        catch (ProtocolException ex) {
            alertDialog.setTitle("ProtocolException");
            alertDialog.setMessage(ex.toString());
            alertDialog.setCancelable(true);
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            ex.printStackTrace();
        }
        catch (MalformedURLException ex) {
            alertDialog.setTitle("MalformedURLException");
            alertDialog.setMessage(ex.toString());
            alertDialog.setCancelable(true);
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            ex.printStackTrace();
        }
        catch (IOException ex) {
            alertDialog.setTitle("IOException");
            alertDialog.setMessage(ex.toString());
            alertDialog.setCancelable(true);
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            ex.printStackTrace();
        }
        catch (Exception e){
            alertDialog.setTitle("Exception");
            alertDialog.setMessage(e.toString());
            alertDialog.setCancelable(true);
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle(type+" Status");
    }

    @Override
    protected void onPostExecute(final String result) {
        try {
            lines = result.split("\n\n");
            Intent intent;
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            if (type.equals("login")) {
                if(lines[0].equals("Success"))
                {
                    alertDialog.setTitle("Status: " + lines[0]);
                    alertDialog.setMessage(lines[1]);
                    alertDialog.setCancelable(true);
                    if (lines[0].equals("Success")) {
                        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(".RegisterEmploy");
                                context.startActivity(intent);
                            }
                        });
                    }
                    alertDialog.show();
                }
            }
            if (type.equals("showData")) {
                intent = new Intent(context, AllData.class);
                intent.putExtra("result", result);
                context.startActivity(intent);
            }
            if (type.equals("register")) {
                alertDialog.setTitle("Registration Status: " + lines[0]);
                alertDialog.setMessage(lines[1]);
                alertDialog.setCancelable(true);
                if (lines[0].equals("Success")) {
                    alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context, RegisterEmploy.class);
                            context.startActivity(intent);
                        }
                    });
                }
                alertDialog.show();
            }
            if (type.equals("update")) {
                if (bools.equals("false")) {
                    if (result.equals("Verified\n")) {

                        alertDialog.setTitle("Update Status: ");
                        alertDialog.setMessage(result);
                        alertDialog.setCancelable(true);
                        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, ForgetPassword.class);
                                intent.putExtra("result", result + username);
                                context.startActivity(intent);
                            }
                        });
                        alertDialog.show();
                    }
                } else if (bools.equals("true")) {
                    if (lines[0].equals("Success")) {
                        alertDialog.setTitle(lines[0]);
                        alertDialog.setMessage(lines[1]);
                        alertDialog.show();

                        intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                    if (lines[0].equals("Failed")) {
                        alertDialog.setTitle(lines[0]);
                        alertDialog.setMessage(lines[1]);
                        alertDialog.show();
                    } else {
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                    }

                }
            }
            if (type.equals("delete")) {

                alertDialog.setTitle("Deleted Account(s)");
                alertDialog.setMessage(lines[0]);
                alertDialog.setCancelable(true);
                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, AllData.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("result", lines[1]);
                        context.startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        }
        catch (Exception e){
            alertDialog.setTitle("Exception");
            alertDialog.setMessage(e.toString());
            alertDialog.setCancelable(true);
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            e.printStackTrace();
        }

    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
