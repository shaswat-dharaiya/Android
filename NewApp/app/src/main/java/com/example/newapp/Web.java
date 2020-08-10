package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Web extends AppCompatActivity {

    private static Button urlbtn;
    private static TextView editTxt;
    private static WebView web;
    private static Button next;
    private static Button previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        urlOpen();
        naviFor();
        naviBack();
    }
    public void urlOpen(){
        urlbtn = (Button) findViewById(R.id.urlbtn);
        editTxt = (EditText) findViewById(R.id.txt1);
        web = (WebView) findViewById(R.id.web);

        urlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = editTxt.getText().toString();
                web.getSettings().setLoadsImagesAutomatically(true);
                web.getSettings().setJavaScriptEnabled(true);
                web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                web.loadUrl(urlStr);
            }
        });
    }
    public void naviFor() {
        next = (Button) findViewById(R.id.next1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".Gesture");
                startActivity(intent);
            }
        });
    }
    public void naviBack(){
        previous = (Button) findViewById(R.id.prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".MainActivity");
                startActivity(intent);
            }
        });
    }
}