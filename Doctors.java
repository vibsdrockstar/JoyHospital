package com.example.vibhor.hospitalmanagementsystem;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Doctors extends AppCompatActivity {
    EditText firstname,lastname,age,specialisation;
    TextView textView;
    DB2_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        firstname = (EditText) findViewById(R.id.et_f);
        lastname = (EditText) findViewById(R.id.et_l);
        age = (EditText) findViewById(R.id.age);
        specialisation = (EditText) findViewById(R.id.et_speci);
        textView = (TextView)findViewById(R.id.textView2);
        controller = new DB2_Controller(this,"",null,1);
    }
    private void emptyEditTexts() {
        firstname.setText(null);
        lastname.setText(null);
        age.setText(null);
        specialisation.setText(null);
    }
    public void btn_click(View view) {
        switch (view.getId()) {
            case R.id.b_add:
                try {
                    controller.insert_doctor(firstname.getText().toString(), lastname.getText().toString(), age.getText().toString(), specialisation.getText().toString());
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
