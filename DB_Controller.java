package com.example.vibhor.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by vibhor on 3/26/2017.
 */
public class DB_Controller extends SQLiteOpenHelper {


    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "HOSPITAL.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE PATIENTS( ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT, LASTNAME TEXT, AGE TEXT, CITY TEXT);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PATIENTS");
        onCreate(db);

    }
    public void  insert_patient(String firstname,String lastname,String age,String city){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        contentValues.put("AGE",age);
        contentValues.put("CITY",city);
        this.getWritableDatabase().insertOrThrow("PATIENTS","",contentValues);
    }
    public void delete_patient(String firstname){
        this.getWritableDatabase().delete("PATIENTS","FIRSTNAME='"+firstname+"'",null);
    }
    public void update_patient(String old_firstname, String new_firstname){
        this.getWritableDatabase().execSQL("UPDATE PATIENTS SET FIRSTNAME='"+new_firstname+"'WHERE FIRSTNAME='"+old_firstname+"'");
    }
    public void list_all_patients(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PATIENTS",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append("Firstname : "+cursor.getString(1)+" Lastname : "+cursor.getString(2)+" Age : "+cursor.getString(3)+" City : "+cursor.getString(4)+"\n");
        }
    }
}
