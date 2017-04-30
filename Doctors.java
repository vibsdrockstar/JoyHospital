package com.example.vibhor.hospitalmanagementsystem;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Doctors extends AppCompatActivity {
    EditText firstname,lastname,age,field;
    TextView textView;
    DB2_Controller controller;
    List<String> specialisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        firstname = (EditText) findViewById(R.id.et_f);
        lastname = (EditText) findViewById(R.id.et_l);
        age = (EditText) findViewById(R.id.age);
        field= (EditText) findViewById(R.id.et_speci);
        textView = (TextView)findViewById(R.id.textView2);
        controller = new DB2_Controller(this,"",null,1);
    }

    private void initField(){
        specialisation= new ArrayList<>();
        specialisation.add("cardiologist");
        specialisation.add("pediatrician");
        specialisation.add("opthalmologist");
        specialisation.add("oncologist");
        specialisation.add("orthopedician");
        //add all desired specialisation
    }
    private void emptyEditTexts() {
        firstname.setText(null);
        lastname.setText(null);
        age.setText(null);
        field.setText(null);
    }
    private boolean isValidSpecialisation(){
        if(field.getText().toString().isEmpty() || !specialisation.contains(field.getText().toString().toLowerCase()))
            return false;
        return true;
    }
    public void btn_click(View view) {
        switch (view.getId()) {
            case R.id.b_add:
                try {
                    if (!isValidSpecialisation())
                        return;
                    controller.insert_doctor(firstname.getText().toString(), lastname.getText().toString(), age.getText().toString(), field.getText().toString());
                    Toast.makeText(this, "Doctor added successfully!", Toast.LENGTH_SHORT).show();
                    emptyEditTexts();
                } catch (SQLiteException e) {
                    Toast.makeText(Doctors.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.b_del:
                controller.delete_doctor(firstname.getText().toString());
                break;
            case R.id.b_list:
                controller.list_all_doctors(textView);
                break;

        }
        }
    }
