package com.example.db_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DB_Helper db_helper;
    private Button addData, viewData,updateData,deleteData;

    private EditText db_name,db_surname,db_marks,db_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db_helper = new DB_Helper(this);

        addData = (Button) findViewById(R.id.button);
        viewData = (Button) findViewById(R.id.button2);
        updateData = (Button) findViewById(R.id.button3);
        deleteData = (Button) findViewById(R.id.button4);

        db_name = (EditText) findViewById(R.id.db_name);
        db_surname = (EditText) findViewById(R.id.db_surname);
        db_marks = (EditText) findViewById(R.id.db_marks);
        db_id = (EditText) findViewById(R.id.db_id);

        add_data();
        view_data();
        update_data();
        delete_data();
    }

    public void add_data(){
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = db_helper.insertData(db_name.getText().toString(),
                        db_surname.getText().toString(),
                        Integer.parseInt(db_marks.getText().toString()));

                if(isInserted == true)
                    Toast.makeText(MainActivity.this,
                            "Data inserted",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,
                            "Data not inserted",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void view_data(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db_helper.getAllData();
                if (res.getCount() == 0){
                    showMessage("Error", "No Data");
                    return;
                }
                StringBuffer data = new StringBuffer();
                while (res.moveToNext()){
                    data.append("ID: "+res.getString(0)+"\n");
                    data.append("ID: "+res.getString(1)+"\n");
                    data.append("ID: "+res.getString(2)+"\n");
                    data.append("ID: "+res.getInt(3)+"\n\n");
                }
                showMessage("Data", data.toString());
            }
        });
    }

    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

    public void update_data(){
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean updated = db_helper.updateData(db_id.getText().toString(),
                        db_name.getText().toString(),
                        db_surname.getText().toString(),
                        Integer.parseInt(db_marks.getText().toString()));

                if(updated == true)
                    Toast.makeText(MainActivity.this,
                            "Data updated",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,
                            "Data not updated",
                            Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void delete_data(){
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rows = db_helper.deleteData(db_id.getText().toString());
                if (rows>0)
                    Toast.makeText(MainActivity.this,
                            "Data deleted",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,
                            "Data not deleted",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

}