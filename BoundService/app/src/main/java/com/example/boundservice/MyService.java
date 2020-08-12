package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {
    private final IBinder iBinder = new LocalClass();
    private final Random myGen = new Random();



    public class LocalClass extends Binder {

        public MyService getService(){
            return MyService.this;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public int getRandomMethod(){
        return myGen.nextInt(1000);
    }
}
