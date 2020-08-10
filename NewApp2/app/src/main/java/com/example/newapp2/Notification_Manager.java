package com.example.newapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notification_Manager extends AppCompatActivity {

    Button notiButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__manager);
        notiButton = (Button) findViewById(R.id.notify);
        notiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                PendingIntent pendingIntent = PendingIntent.getActivity(Notification_Manager.this,0,intent,0);
                Notification noti = new Notification.Builder(Notification_Manager.this)
                        .setTicker("TickerTitle")
                        .setContentText("Content Title")
                        .setContentText("Hello World This is a new Notification")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent).getNotification();

                noti.flags = Notification.FLAG_AUTO_CANCEL;
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0,noti);

            }
        });
    }
}