package com.example.signin_signup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteAnimal extends AppCompatActivity {

    private Button submit;
    private CheckBox dog, cat, cow;

    private Button submit1;
    private RadioGroup animal;
    private RadioButton horse, tiger, lion,selected;

    private static Button btn;
    private static TextView txt_v;
    private static RatingBar rtn_br;

    private static Button alt_btn;

    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_animal);
        onBtnClick();
        addListener();
        addRadioBtns();
        ratings();
        ratingsSubmit();
        log_Out();
    }

    public void addListener(){
        submit = (Button) findViewById(R.id.button4);
        dog = (CheckBox) findViewById(R.id.checkBox3);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())
                    Toast.makeText(FavoriteAnimal.this, "Dog is selected", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onBtnClick(){
        submit = (Button) findViewById(R.id.button4);
        dog = (CheckBox) findViewById(R.id.checkBox3);
        cat = (CheckBox) findViewById(R.id.checkBox);
        cow = (CheckBox) findViewById(R.id.checkBox2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer res = new StringBuffer();
                res.append("Dog : ").append(dog.isChecked());
                res.append("\nCat : ").append(cat.isChecked());
                res.append("\nCow : ").append(cow.isChecked());
                Toast.makeText(FavoriteAnimal.this,res.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addRadioBtns(){
        animal = (RadioGroup) findViewById(R.id.radioGroup);
        /*horse = (RadioButton) findViewById(R.id.radioButton);
        tiger = (RadioButton) findViewById(R.id.radioButton2);
        lion = (RadioButton) findViewById(R.id.radioButton3);*/
        submit1 = (Button) findViewById(R.id.button6);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_id = animal.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selected_id);
                Toast.makeText(FavoriteAnimal.this,
                        selected.getText(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    public void ratings(){
        rtn_br = (RatingBar) findViewById(R.id.ratingBar);
        txt_v = (TextView) findViewById(R.id.textView5);

        rtn_br.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txt_v.setText(String.valueOf(rating));
            }
        });

    }

    public void ratingsSubmit(){
        rtn_br = (RatingBar) findViewById(R.id.ratingBar);
        btn = (Button) findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FavoriteAnimal.this, String.valueOf(rtn_br.getRating()),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void log_Out() {
        alt_btn = (Button) findViewById(R.id.button5);
        alt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alrt = new AlertDialog.Builder(FavoriteAnimal.this);
                alrt.setMessage("Confirm Termination")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert_dialog = alrt.create();
                alert_dialog.setTitle("Alert");
                alert_dialog.show();
            }
        });
    }
}
