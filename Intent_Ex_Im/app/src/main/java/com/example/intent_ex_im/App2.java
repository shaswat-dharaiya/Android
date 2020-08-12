package com.example.intent_ex_im;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class App2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app2);
    }

    public void openNewApp(View view){
        Intent intent = new Intent("MyActionbar2.app.src.main.java.com.example.myactionbar.actvt1");
        this.startActivity(intent);
    }
}