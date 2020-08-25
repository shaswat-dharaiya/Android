package com.example.mysql;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText username;
    EditText password;
    SignInButton signin;
    private GoogleApiClient googleApiClient;
    private static final int SING_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            username = (EditText) findViewById(R.id.editTextTextPersonName);
            password = (EditText) findViewById(R.id.editTextTextPassword);
            signin = (SignInButton)findViewById(R.id.sign_in_button);

            final GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.
                    Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail().build();

            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent, SING_IN);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void doLogin(View view) {
        String usr = username.getText().toString();
        String psd = password.getText().toString();

//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);;
//        backgroundWorker.execute("login", usr, psd);
        BackgroundMongo backgroundMongo = new BackgroundMongo(this);
        backgroundMongo.execute("login", usr, psd);

    }

    public void forgetPassword(View view){
        Intent intent = new Intent(".ForgetPassword");
        startActivity(intent);
    }

    /*public void googleSignIn(View view){
        signin = (SignInButton)findViewById(R.id.sign_in_button);
        try{
            final GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.
                    Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                    .requestEmail().build();

            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent, SING_IN);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SING_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                startActivity(new Intent(MainActivity.this,RegisterEmploy.class));
                finish();
            }
            else {
                Toast.makeText(this, "Login failed through Google", Toast.LENGTH_SHORT).show();
            }
        }
    }
}