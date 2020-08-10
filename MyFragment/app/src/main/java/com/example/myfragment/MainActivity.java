package com.example.myfragment;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
    }

    public void chngFrag(View view){
        Fragment frag;

        if(view == findViewById(R.id.frag1)){
            frag = new FragmentOne();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frag1_new,frag);
            ft.commit();
        }
        if(view == findViewById(R.id.frag2)){
            frag = new FragmentTwo();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frag1_new,frag);
            ft.commit();
        }

    }
}