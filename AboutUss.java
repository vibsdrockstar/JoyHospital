package com.example.vibhor.hospitalmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }
    public void OpenInfo(View view) {
        switch (view.getId()){
            case R.id.patient:
                startActivity(new Intent(this, Info.class));
                break;
            case R.id.doctors:
                startActivity(new Intent(this, Doctors.class));
                break;
            case R.id.b_pay:
                startActivity(new Intent(this, Patients.class));
                break;

        }
    }

}
