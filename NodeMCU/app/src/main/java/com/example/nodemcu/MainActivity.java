package com.example.nodemcu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public static TextView stateLED;
    Button switch1;

    Boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById( R.id.text);
        stateLED = (TextView) findViewById( R.id.textView);
        switch1 = (Button) findViewById( R.id.switch1);
        switch1.setBackgroundColor(Color.GRAY);
        switch1.setTextColor(Color.WHITE);

    }

    public void ledToggle(View view){
        if (!state){
            switch1.setBackgroundColor(Color.GREEN);
            switch1.setTextColor(Color.BLACK);
        }
        else{
            switch1.setBackgroundColor(Color.GRAY);
            switch1.setTextColor(Color.WHITE);
        }
        state = !state;
        Conntvt conntvt = new Conntvt(this);
        conntvt.execute(state);
    }
}