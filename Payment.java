package com.example.vibhor.hospitalmanagementsystem;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Patients extends AppCompatActivity {
    EditText patientname, epidemic, cure, fee, status;
    TextView textView;
    DB3_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        patientname = (EditText) findViewById(R.id.et_pname);
        epidemic = (EditText) findViewById(R.id.et_epi);
        cure = (EditText) findViewById(R.id.et_cure);
        fee = (EditText) findViewById(R.id.et_pay);
        status = (EditText) findViewById(R.id.et_paystatus);
        textView = (TextView) findViewById(R.id.tv_large);
        controller = new DB3_Controller(this, "", null, 1);

    }

    private void emptyEditTexts() {
        patientname.setText(null);
        epidemic.setText(null);
        cure.setText(null);
        fee.setText(null);
        status.setText(null);
    }

    public void btn_click(View view) {
        switch (view.getId()) {
            case R.id.b_add:
                try {
                    controller.insert_info(patientname.getText().toString(), epidemic.getText().toString(), cure.getText().toString(), fee.getText().toString(), status.getText().toString());
                    Toast.makeText(this, "Information added successfully!", Toast.LENGTH_SHORT).show();
                    emptyEditTexts();
                } catch (SQLiteException e) {
                    Toast.makeText(Patients.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.show_button:
                controller.list_all_info(textView);
                break;
        }
    }
}
