package com.example.intent_ex_im;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openOne(View view){
        Intent intent = new Intent(this,ActvtOne.class);
        startActivity(intent);
    }

    public void openTwo(View view){
        Intent intent = new Intent(this,App2.class);
        startActivity(intent);
    }
}