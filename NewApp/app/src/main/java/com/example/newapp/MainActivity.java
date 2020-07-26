package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static ListView lst_view;
    private static String[] Names= new String[]{"A", "B", "C", "D"};

    private static SeekBar skbar;
    private static SeekBar skbar1;

    private static TextView txtView;
    private static TextView txtView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView();
        skbarMethod();
    }

    public void listView(){
        lst_view = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adptr = new ArrayAdapter<String>(this,R.layout.name_list,Names);
        lst_view.setAdapter(adptr);
        lst_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = (String) lst_view.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,"Poistion: "+position+" Value: "+(val+1),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void skbarMethod(){
        skbar = (SeekBar) findViewById(R.id.seekBar);
        skbar1 = (SeekBar) findViewById(R.id.seekBar2);

        txtView = (TextView) findViewById(R.id.textView);
        txtView2 = (TextView) findViewById(R.id.textView3);

        txtView.setText("Progress: "+skbar.getProgress()+" / "+skbar.getMax());
        txtView2.setText("Progress: "+skbar1.getProgress()+" / "+skbar1.getMax());



        skbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progVal;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progVal = progress;
                txtView.setText("Progress: "+progress+" / "+skbar.getMax());
                Toast.makeText(MainActivity.this,"Seekbar in progress",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"Seekbar in tracking",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtView.setText("Progress: "+progVal+" / "+skbar.getMax());
                Toast.makeText(MainActivity.this,"Seekbar in stop",Toast.LENGTH_LONG).show();
            }
        });
        skbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progVal;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progVal = progress;
                txtView2.setText("Progress: "+progress+" / "+skbar1.getMax());
                Toast.makeText(MainActivity.this,"Seekbar in progress",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"Seekbar in tracking",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtView2.setText("Progress: "+progVal+" / "+skbar1.getMax());
                Toast.makeText(MainActivity.this,"Seekbar in stop",Toast.LENGTH_LONG).show();
            }
        });
    }
}