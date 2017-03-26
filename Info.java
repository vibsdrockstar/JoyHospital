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

public class Info extends AppCompatActivity {

    EditText firstname,lastname;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        firstname = (EditText)findViewById(R.id.et_f);
        lastname = (EditText)findViewById(R.id.et_l);
        textView = (TextView)findViewById(R.id.textView);
        controller = new DB_Controller(this,"",null,1);
    }

    public void btn_click(View view) {
        switch (view.getId()){
            case R.id.b_add:
                try {
                    controller.insert_patient(firstname.getText().toString(),lastname.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(Info.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.b_delete:
                controller.delete_patient(firstname.getText().toString());
                break;
            case R.id.b_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Info.this);
                dialog.setTitle("ENTER NEW FIRSTNAME");
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
