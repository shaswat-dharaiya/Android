package com.example.mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    AlertDialog alertDialog;
    String[] colName = {"Name", "Surname", "Age", "Username", "Password", "Confirm Password"};
    EditText[] reg_fields = new EditText[6];
    Button verify,update;
    EditText usr_name;
    String username;
    Boolean enabled = true;
    TextView userVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        reg_fields[0] = (EditText) findViewById(R.id.name);
        reg_fields[1] = (EditText) findViewById(R.id.surname);
        reg_fields[2] = (EditText) findViewById(R.id.age);
        reg_fields[3] = (EditText) findViewById(R.id.username);
        reg_fields[4] = (EditText) findViewById(R.id.password);
        reg_fields[5] = (EditText) findViewById(R.id.confirmPassword);
        usr_name = (EditText) findViewById(R.id.username2);
        verify = (Button) findViewById(R.id.button6);
        update = (Button) findViewById(R.id.button8);

        userVerify = (TextView) findViewById(R.id.textView5);

        for(int i=0; i<reg_fields.length;i++){
            reg_fields[i].setEnabled(!enabled);
            reg_fields[i].setHint("");
        }

        update.setEnabled(!enabled);
        checking();
    }

    public void checking(){
        try {
            Intent intent = new Intent();
            intent = getIntent();
            String verified = "";
            String[] data = intent.getStringExtra("result").split("\n");
            verified = data[0];
            username = data[1];

            if(verified.equals("Verified")){
                update.setEnabled(enabled);
                verify.setEnabled(!enabled);
                usr_name.setEnabled(!enabled);
                usr_name.setHint("");
                userVerify.setText("User verified. Please update the details.");
                for(int i=0; i<reg_fields.length;i++){
                    reg_fields[i].setEnabled(enabled);
                    reg_fields[i].setHint(colName[i]);
                }
            }
        }
        catch (Exception e){
//            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void doUpdate(View view){
        RegisterEmploy re = new RegisterEmploy();
        String[] editText = new String[6];
        boolean empty = false,isCorrect = true;
        alertDialog = new AlertDialog.Builder(this).create();
        for(int i =0;i<editText.length;i++) {
            editText[i] = reg_fields[i].getText().toString();
            String vaild = re.regexCheck(editText[i], i);
            if(!(vaild.equals("Ok"))){
                reg_fields[i].setError(vaild);
                isCorrect = false;
            }
        }
        for(int i =4;i<editText.length;i++) {
            if(TextUtils.isEmpty(editText[i])) {
                reg_fields[i].setError("This field cant be left empty");
                empty = true;
            }
        }

        if (editText[4].equals(editText[5]) && !empty && isCorrect){
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute("showData");
            BackgroundMongo backgroundMongo = new BackgroundMongo(this);
            backgroundMongo.execute("update","true",username,editText[0],editText[1],editText[2],editText[3],editText[4]);
        }
        else if (!editText[4].equals(editText[5])){
            reg_fields[5].setError("Password & Confirm Password should match");
        }
    }

    public void verifyUser(View view){

        String username = usr_name.getText().toString();
        if(TextUtils.isEmpty(username)) {
            usr_name.setError("This field cant be left empty");
        }
        else{
            BackgroundMongo backgroundMongo = new BackgroundMongo(this);
            backgroundMongo.execute("update","false",username);
        }
        usr_name.setText("");

    }
}