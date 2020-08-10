package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.view.MotionEvent;
import android.gesture.Gesture;
import android.widget.TextView;

import static android.view.GestureDetector.*;

public class GestureMotion extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static Button previous;
    private static TextView txt;
    private GestureDetectorCompat GestDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        txt = (TextView) findViewById(R.id.textView2);
        GestDetect = new GestureDetectorCompat(this,this);
        GestDetect.setOnDoubleTapListener(this);

        navigate();
    }

    public void navigate(){
        previous = (Button) findViewById(R.id.prev2);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".Web");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        txt.setText("onSingleTapConfirmed"+e.toString());
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        txt.setText("onDoubleTap"+e.toString());
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        txt.setText("onDoubleTapEvent"+e.toString());
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        txt.setText("onDown"+e.toString());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        txt.setText("onShowPress"+e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        txt.setText("onSingleTapUp"+e.toString());
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        txt.setText("onScroll"+ e1.toString()+e2.toString());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        txt.setText("onLongPress"+e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        txt.setText("onFling"+e1.toString()+e2.toString());;
        return false;
    }
}