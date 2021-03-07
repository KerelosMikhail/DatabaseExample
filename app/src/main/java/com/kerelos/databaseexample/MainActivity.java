package com.kerelos.databaseexample;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.kerelos.databaseexample.database.MyHelper;


public class MainActivity extends AppCompatActivity {

    private MyHelper dbHelper;
    private SQLiteDatabase db;
    private EditText etAfName, etAFAddress, etAFPhone, etGrade, etAFClass, etAFNotes;
    private Button btnAFBD, btnAddDb;

    // for test2 button
    String[] listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link to xml
        etAfName = findViewById(R.id.etAfName);
        etAFAddress = findViewById(R.id.etAfAddress);
        etAFPhone = findViewById(R.id.etPhone);
        etGrade = findViewById(R.id.etGrade);
        etAFClass = findViewById(R.id.etAfChclass);
        etAFNotes = findViewById(R.id.etAfNotes);
        btnAFBD = findViewById(R.id.btnAFBD);
        btnAddDb = findViewById(R.id.btnAddDb);

        Button btnTest = findViewById(R.id.btnTest);
        final Button btnTest2 = findViewById(R.id.btnTest2);

        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] fonts2 = {"Small", "Medium", "Large", "Huge"};

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle("Choose an item");

                mBuilder.setSingleChoiceItems(fonts2, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(MainActivity.this, "You selected\n"+ fonts2[i], Toast.LENGTH_SHORT).show();

                        btnTest2.setText(fonts2[i]);
                        //    mResult.setText(listItems[i]);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });



        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] fonts = {"Small", "Medium", "Large", "Huge"};

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Select a text size");
                builder.setItems(fonts, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("Small".equals(fonts[which])) {
                            Toast.makeText(MainActivity.this,"you nailed it", Toast.LENGTH_SHORT).show();
                        }
                        else if ("Medium".equals(fonts[which])) {
                            Toast.makeText(MainActivity.this,"you cracked it", Toast.LENGTH_SHORT).show();
                        }
                        else if ("Large".equals(fonts[which])){
                            Toast.makeText(MainActivity.this,"you hacked it", Toast.LENGTH_SHORT).show();
                        }
                        else if ("Huge".equals(fonts[which])){
                            Toast.makeText(MainActivity.this,"you digged it", Toast.LENGTH_SHORT).show();
                        }
                        // the user clicked on colors[which]
                    }
                });
                builder.show();
            }
        });

        // important for database
        dbHelper = new MyHelper(this);
        db = dbHelper.getWritableDatabase();


        // for date button
        btnAFBD.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // for Date
                DatePickerDialog dpd;

                Calendar calendar = Calendar.getInstance();

                // the day, month and year of birth.
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                        // MainActivity and getContext()
                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mmonth, int mdayOfMonth) {
                        // for button text
                        btnAFBD.setText(((mmonth + 1) + "/" + mdayOfMonth + "/" + myear));
                    }
                }, day, month, year);
                dpd.show();
            }
        });
        Adddata();
         //
       // if (btnAFBD.getText().toString()= ;
    }

    public void Adddata(){

        btnAddDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button click here
                String tDate = btnAFBD.getText().toString();

                // insert data into DB
           //     boolean isInserted = db.insertData("mina","25151 bfsabdbs","252555","12/5/1980","Third","Malak","He is good student");
               boolean isInserted = dbHelper.insertData(etAfName.getText().toString(),etAFAddress.getText().toString(),
                       etAFPhone.getText().toString(),tDate,etGrade.getText().toString(),etAFClass.getText().toString(),
                       etAFNotes.getText().toString());

                if (isInserted = true )
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not saved", Toast.LENGTH_LONG).show();

            }
        });
    }

}
