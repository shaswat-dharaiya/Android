package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view){
//        Uncomment the line in order to change from Intent to Service.
//        Intent intent = new Intent(this,Service_Class.class);
        Intent intent = new Intent(this,MyIntent.class);
        startService(intent);
    }

    public void stopService(View view){
//        Uncomment the line in order to change from Intent to Service.
//        Intent intent = new Intent(this,Service_Class.class);
        Intent intent = new Intent(this,MyIntent.class);
        stopService(intent);
    }

}