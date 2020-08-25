package com.example.mysql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class RegisterEmploy extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    AlertDialog alertDialog;
    String[] colName = {"Name", "Surname", "Age", "Username", "Password", "Confirm Password"};
    Button logout;
    EditText[] reg_fields = new EditText[6];

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_employ);
        reg_fields[0] = (EditText) findViewById(R.id.Name);
        reg_fields[1] = (EditText) findViewById(R.id.Surname);
        reg_fields[2] = (EditText) findViewById(R.id.Age);
        reg_fields[3] = (EditText) findViewById(R.id.Username);
        reg_fields[4] = (EditText) findViewById(R.id.Password);
        reg_fields[5] = (EditText) findViewById(R.id.ConfirmPassword);

        logout = (Button) findViewById(R.id.logout);

        googleSignInOptions = new  GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        googleApiClient = new  GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient)
                        .setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if(status.isSuccess()){
                                    startActivity(new Intent(RegisterEmploy.this,MainActivity.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(RegisterEmploy.this, "Could not log out.\nPlease try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    public void doRegister(View view){

        String[] editText = new String[6];
        boolean empty = false,isCorrect = true;
        alertDialog = new AlertDialog.Builder(this).create();
        for(int i =0;i<editText.length;i++){
            editText[i] = reg_fields[i].getText().toString();
            String vaild = regexCheck(editText[i], i);
            if(TextUtils.isEmpty(editText[i])) {
                reg_fields[i].setError("This field cant be left empty");
                empty = true;
            }
            else if(!(vaild.equals("Ok"))){
                reg_fields[i].setError(vaild);
                isCorrect = false;
            }
        }
        if (editText[4].equals(editText[5]) && !empty && isCorrect){
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute("showData");
            BackgroundMongo backgroundMongo = new BackgroundMongo(this);
            backgroundMongo.execute("register",editText[0],editText[1],editText[2],editText[3],editText[4]);
            Toast.makeText(RegisterEmploy.this,
                    "Please wait",
                    Toast.LENGTH_LONG).show();
        }
        else if (!editText[4].equals(editText[5])){
            reg_fields[5].setError("Password & Confirm Password should match");
        }
    }

    public void allData(View view){
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute("showData");
        BackgroundMongo backgroundMongo = new BackgroundMongo(this);
        backgroundMongo.execute("showData");
    }

    public String regexCheck(String val, int id){
        try {
            switch (id) {
                case 0:
                    if (!val.matches("[A-Z][a-z]+"))
                        return "Case: "+(id)+"\nName must start with a Capital Letter.\nAnd must only contain letters.";
                    break;
                case 1:
                    if (!val.matches("[A-Z]+([a-zA-Z]+)*"))
                        return "Case: "+(id)+"\nSurname must start with a Capital Letter.\nAnd must only contain letters or '-'.";
                    break;
                case 2:
                    if (!val.matches("[0-9]+"))
                        return "Case: "+(id)+"\nAge must only contain digits.";
                    else
                        if (!(Integer.parseInt(val) > 0 && Integer.parseInt(val) <= 200))
                            return "Case: "+(id)+"\nAge must be between 0 & 200";
                    break;
                case 3:
                    if (!val.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,20}\\b"))
                        return "Case: "+(id)+"\nUsername must be between 3 to 20 characters long.\n" +
                                "Valid Characters:\n" +
                                "Alphabets.\nDigits.\nSpecial Characters: '._-')";
                    break;
                case 4:
                    if (!val.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$"))
                        return "Case: "+(id)+"\nUsername must be between 8 to 20 characters long.\n" +
                                "Atleast one Uppercase & one Lowercase Letter.\n Atleast one Digit.\n" +
                                "Valid Characters:\n" +
                                "\nSpecial Characters: \"!@#$%&*()-+=^\".\nNo white spaces.)";
                    break;
                case 5:
                    if (!val.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$"))
                        return "Case: "+(id)+"\nUsername must be between 8 to 20 characters long.\n" +
                                "Atleast one Uppercase & one Lowercase Letter.\n Atleast one Digit.\n" +
                                "Valid Characters:\n" +
                                "\nSpecial Characters: \"!@#$%&*()-+=^\".\nNo white spaces.)";
                    break;
            }
        } catch (Exception e){
            alertDialog.setTitle("Exception");
            alertDialog.setMessage(e.toString());
            alertDialog.setCancelable(true);
            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            e.printStackTrace();
        }
        return "Ok";
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();

        }
    }
}