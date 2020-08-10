package com.example.newapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class Time_Picker extends AppCompatActivity {

    static final int DIALOG_ID =  0;
    int hour_x;
    int min_x;

    private TimePicker time_picker;
    private TimePicker time_picker2;
    private Button show_time;
    private Button show_time2;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        showTime();
        navFor();
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new TimePickerDialog(Time_Picker.this, kTimePickerListner, hour_x, min_x,false);
        return null;

    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            min_x = minute;
            Toast.makeText(Time_Picker.this,hour_x+":"+min_x,Toast.LENGTH_SHORT);
        }
    };

    public void showTime(){
        time_picker = (TimePicker) findViewById(R.id.simpleTimePicker);
        show_time = (Button) findViewById(R.id.showTime);
        show_time2 = (Button) findViewById(R.id.showTime2);

        show_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),time_picker.getCurrentHour()+":"+time_picker.getCurrentMinute(),Toast.LENGTH_SHORT).show();
            }
        });
        show_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    public void navFor(){
        next = (Button) findViewById(R.id.next1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".Date_Picker");
                startActivity(intent);
            }
        });
    }
}