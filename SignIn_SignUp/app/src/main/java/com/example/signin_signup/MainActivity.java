package com.example.signin_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkLogin(View v){
        EditText e1 = (EditText) findViewById(R.id.editText5);
        EditText e2 = (EditText) findViewById(R.id.editText);
        String usr = e1.getText().toString();
        String psw = e2.getText().toString();
        Button btn_login = (Button) findViewById(R.id.button);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.editText5);
                EditText e2 = (EditText) findViewById(R.id.editText);
                String usr = e1.getText().toString();
                String psw = e2.getText().toString();
                if(usr.contentEquals("admin") && psw.contentEquals("admin")) {
                    Toast.makeText(
                            MainActivity.this,
                            "login successful",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                else{
                    Toast.makeText(
                            MainActivity.this,
                            "login unsuccessful",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
        if(usr.contentEquals("admin") && psw.contentEquals("admin"))
            System.out.println("login successful");
        else
            System.out.println("login unsuccessful");
    }
}
