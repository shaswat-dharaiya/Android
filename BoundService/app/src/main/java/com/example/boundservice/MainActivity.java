package com.example.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void getRandom(View view){
        TextView textView = (TextView) findViewById(R.id.editTextTextPersonName);
        textView.setText(Integer.toString(myService.getRandomMethod()));
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalClass binder = (MyService.LocalClass) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}