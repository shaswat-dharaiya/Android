package com.example.mysql;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class AllData extends AppCompatActivity {

    BackgroundMongo backgroundMongo;
    AlertDialog alertDialog,alertDialog1;
    String rows_prev = "";
    ArrayList<Integer> rowsDeleted = new ArrayList<>();
    int textViewCount;
    Button delete;
    TableLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);

        delete = (Button) findViewById(R.id.button7);
        delete.setEnabled(false);
        ll = (TableLayout) findViewById(R.id.table2);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ResourceType")
    public void showData(View view){
        delete.setEnabled(true);
        Intent intent = getIntent();
        String rows = intent.getStringExtra("result");
        rows = ":Emp:Name:Surname:Age:Username:Password:Delete\n"+rows;
        if(!rows.equals(rows_prev)){
            rows_prev = rows;
            final String[] rowData = rows.split("\n");

            textViewCount = rowData.length;

            for(int i=0;i<textViewCount;i++){
                alertDialog1 = new AlertDialog.Builder(this).create();
                String[] data = rowData[i].split(":");
                int textViewCount_cols = data.length;
                TextView[] emp_data = new TextView[textViewCount_cols];
                final CheckBox checkBox = new CheckBox(this);
                TableRow row= new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                try {
                    for (int j = 0; j < textViewCount_cols; j++) {
                        {
                            emp_data[j] = new TextView(this);
                            emp_data[j].setTextAppearance(this, android.R.attr.textAppearanceLarge);
                            emp_data[j].setTextSize(20);
                            emp_data[j].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            emp_data[j].setPadding(0,7,15,7);
                            emp_data[j].setClickable(true);
                            emp_data[j].setTag(i);

                        }
                        emp_data[j].setText(data[j]);
                        row.addView(emp_data[j]);
                        if(i>0) {
                            emp_data[j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int rowId = (int) v.getTag();
                                    String[] colNames = "Emp:Name:Surname:Age:Username:Password".split(":");
                                    String[] finalSelectedRow = rowData[rowId].split(":");
                                    alertDialog1.setTitle("Employ ID:"+Integer.toString(rowId));
                                    String setMessage = "";
                                    for (int k=1;k<colNames.length;k++){
                                        setMessage += colNames[k]+": "+finalSelectedRow[k+1]+"\n";
                                    }
//                                    Toast.makeText(AllData.this, setMessage, Toast.LENGTH_SHORT).show();
                                    alertDialog1.setMessage(setMessage);
                                    alertDialog1.show();
                                }
                            });
                        }
                    }
                if(i>0){row.addView(checkBox);}
                ll.addView(row, i);
                {final int finalI = i-1;
                checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()){
                        rowsDeleted.add(finalI);
                        Collections.sort(rowsDeleted);
                    }
                    if (!checkBox.isChecked()){
                        int ind = rowsDeleted.indexOf(finalI);
                        rowsDeleted.remove(ind);
                    }
                }
                });}
                }catch (Exception e){
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
            }
        }
        else{
            Toast.makeText(this, "Data already displayed", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(View view){
        alertDialog = new AlertDialog.Builder(this).create();
        backgroundMongo = new BackgroundMongo(this);
        if (!rowsDeleted.isEmpty()){
            try {
                alertDialog.setTitle("Data of following emplpoys will be deleted: ");
                String rows2bDelted = "",rows = "";
                for(int i=0;i<rowsDeleted.size();i++){
                    rows2bDelted+=rowsDeleted.get(i)+":";
                    rows+="Employ "+(rowsDeleted.get(i)+1)+"\n";
                }
                alertDialog.setMessage(rows);
                final String finalRows2bDelted = rows2bDelted;
                alertDialog.setButton(-2, "Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { backgroundMongo.execute("delete", finalRows2bDelted);                   }
                });
                alertDialog.setButton(-1, "Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            for (Integer i : rowsDeleted) {
                                TableRow row = (TableRow) ll.getChildAt(i + 1);
                                final CheckBox checkBox = new CheckBox(AllData.this);
                                checkBox.setChecked(false);
                                {
                                    final int finalI = i;
                                    checkBox.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (checkBox.isChecked()) {
                                                rowsDeleted.add(finalI);
                                                Collections.sort(rowsDeleted);
                                            }
                                            if (!checkBox.isChecked()) {
                                                int ind = rowsDeleted.indexOf(finalI);
                                                rowsDeleted.remove(ind);
                                            }
                                        }
                                    });
                                }
                                row.removeViewAt(row.getVirtualChildCount() - 1);
                                ll.removeViewAt(i + 1);
                                row.addView(checkBox);
                                ll.addView(row, i + 1);
                            }
                            rowsDeleted.clear();
                        }
                        catch (Exception e) {
                            alertDialog.setTitle("Exception");
                            alertDialog.setMessage(e.toString());
                            alertDialog.setCancelable(true);
                            alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            e.printStackTrace();
                        }}
                });
                alertDialog.show();
            }catch (Exception e){
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
        }
        else if (rowsDeleted.isEmpty()){
            alertDialog.setTitle("No Employ data selected");
            alertDialog.setMessage("Please select Employ data");
            alertDialog.show();
        }
    }

}