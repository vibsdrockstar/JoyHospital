package com.example.vibhor.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;


/**
 * Created by vibhor on 4/7/2017.
 */
public class DB2_Controller extends SQLiteOpenHelper {
    public DB2_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context,"HOSPITAL.db2",factory,version);
    }
    @Override
    public void obCreate(SQLiteDatabase db2) {
        db2.execSQL("CREATE TABLE DOCTORS(ID  INTEGER PRIMARY KEY AUTOINCREMENT ,FIRSTNAME TEXT,LASTNAME TEXT,AGE TEXT,SPECIALISATION TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion,int newVersion) {
        db2.execSQL("DROP TABLE IF EXISTS DOCTORS");
        onCreate(db2);
    }
    public void  insert_doctor(String firstname,String lastname,String age,String specialisation){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        contentValues.put("AGE",age);
        contentValues.put("SPECIALISATION",specialisation);
        this.getWritableDatabase().insertOrThrow("DOCTORS","",contentValues);
        
    }
    public void delete_doctor(String firstname){
        this.getWritableDatabase().delete("DOCTORS","FIRSTNAME='"+firstname+"'",null);
    }
    public void list_all_doctors(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM DOCTORS",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append("Firstname :"+cursor.getString(1)+"Lastname :"+cursor.getString(2)+" Age:"+cursor.getString(3)+"Specialisation:"+cursor.getString(4)+"\n");
        }
    }
}
