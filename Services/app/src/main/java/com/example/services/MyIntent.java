package com.example.services;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyIntent extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntent() {
        super("Intent Service Thread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this){
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(MyIntent.this,"Service has started",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyIntent.this,"Service has stopped",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}
