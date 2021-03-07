package com.kerelos.databaseexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Date;

public class MyHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Jesus.db";
    public static final String TABLE_NAME = "MyClass";
    public static final Integer DATABASE_VERSION = 1;
        // define table columns
    public static final String NAME = "Name", ADDRESS = "Address", PHONE ="phone",
                DATE_OF_Birth ="Date_of_birth",SCHOOL_GRADE="School_grade",MYCLASS_NAME ="MyClass_Name", NOTES = "Notes";

  //  private static final String SQL_CREATE_ENTRIES ="Create table student (id Integer autoincrement,name Varchar(50) )" ;

    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ NAME +" TEXT, "+ADDRESS+" TEXT," +
                    " "+ PHONE +" TEXT, "+DATE_OF_Birth+" TEXT, "+ SCHOOL_GRADE +" TEXT,"+ MYCLASS_NAME +" TEXT," +
                    " "+NOTES+" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String name,String address,String phone, String DOB, String School_grade, String class_Name, String Notes ){

        SQLiteDatabase db =  this.getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(NAME,name);
        row.put(ADDRESS,address);
        row.put(PHONE,phone);
        row.put(DATE_OF_Birth,DOB);
        row.put(SCHOOL_GRADE,School_grade);
        row.put(MYCLASS_NAME,class_Name);
        row.put(NOTES,Notes);

        long result = db.insert(TABLE_NAME,null,row);

        if (result == -1) {
            return false;
        }else
            return true;
    }

    public Cursor getAllData (){

        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+ TABLE_NAME,null);
        return  res ;
    }

}
