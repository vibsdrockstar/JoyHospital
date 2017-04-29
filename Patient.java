package com.example.vibhor.hospitalmanagementsystem;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Info extends AppCompatActivity {

    EditText firstname,lastname,age,city;
    TextView textView;
    DB_Controller controller;
    List<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initCities();
        firstname = (EditText)findViewById(R.id.et_f);
        lastname = (EditText)findViewById(R.id.et_l);
        age = (EditText)findViewById(R.id.age);
        city = (EditText) findViewById(R.id.et_city);
        textView = (TextView)findViewById(R.id.textView2);
        controller = new DB_Controller(this,"",null,1);
    }

    private void initCities(){
        cities = new ArrayList<>();
        cities.add("kolkata");
        cities.add("new delhi");
        cities.add("bombay");
        cities.add("chandigarh");
        cities.add("bengaluru");
        // add all the desired cities here
    }

    private void emptyEditTexts(){
        firstname.setText(null);
        lastname.setText(null);
        age.setText(null);
        city.setText(null);
    }

    private boolean isValidCity(){
        if(city.getText().toString().isEmpty() || !cities.contains(city.getText().toString().toLowerCase()))
            return false;
        return true;
    }

    public void btn_click(View view) {
        switch (view.getId()){
            case R.id.b_add:
                try {
                    if(!isValidCity())
                        return;
                    controller.insert_patient(firstname.getText().toString(),lastname.getText().toString(),age.getText().toString(),city.getText().toString());
                    Toast.makeText(this, "Patient added successfully!", Toast.LENGTH_SHORT).show();
                    emptyEditTexts();
                }catch (SQLiteException e){
                    Toast.makeText(Info.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.b_delete:
                controller.delete_patient(firstname.getText().toString());
                break;
            case R.id.b_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Info.this);
                dialog.setTitle("ENTER NEW NAME");
                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public  void onClick(DialogInterface dialogInterface,int i){
                        controller.update_patient(firstname.getText().toString(),new_firstname.getText().toString());

                }
                });
                dialog.show();
                break;
            case R.id.b_list:
                controller.list_all_patients(textView);
                break;

        }
    }
}
