package com.example.mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);
    }

    public void doLogin(View view) {
        String usr = username.getText().toString();
        String psd = password.getText().toString();

//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);;
//        backgroundWorker.execute("login", usr, psd);
        BackgroundMongo backgroundMongo = new BackgroundMongo(this);
        backgroundMongo.execute("login", usr, psd);

    }

    public void allData(View view) {
        Intent intent = new Intent(".AllData");
        startActivity(intent);
    }

    public void forgetPassword(View view){
        Intent intent = new Intent(".ForgetPassword");
        startActivity(intent);
    }
}