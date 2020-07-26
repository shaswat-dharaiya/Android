package com.example.signin_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText e1, e2;
    private Button btn_login;
    private int countr = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin();
    }

    public void checkLogin(){
        btn_login = (Button) findViewById(R.id.button);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1 = (EditText) findViewById(R.id.editText5);
                e2 = (EditText) findViewById(R.id.editText);
                String usr = e1.getText().toString();
                String psw = e2.getText().toString();
                if(usr.contentEquals("admin") && psw.contentEquals("admin")) {
                    Intent intent = new Intent(".FavoriteAnimal");
                    startActivity(intent);
                    countr = 5;
                }
                else{
                    if(countr>0){
                        Toast.makeText(
                                MainActivity.this,
                                "login unsuccessful. Attempts remaining: "+Integer.toString(countr--),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                    else{
                        btn_login.setEnabled(false);
                        Toast.makeText(
                                MainActivity.this,
                                "Login Attempts exhausted. Cannot Login",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }
        });
    }
}
