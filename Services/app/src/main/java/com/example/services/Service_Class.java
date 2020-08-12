package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Service_Class extends Service {

    final class TheThread implements Runnable{
        int servId;
        TheThread(int servId){
            this.servId = servId;
        }

        @Override
        public void run() {
            synchronized (this){
                try {
                    wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopSelf(this.servId);
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(Service_Class.this,"Service has started",Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new TheThread(startId));
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(Service_Class.this,"Service has stoppped",Toast.LENGTH_SHORT).show();
    }
}
