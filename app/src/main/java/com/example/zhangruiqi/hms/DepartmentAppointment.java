package com.example.zhangruiqi.hms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class DepartmentAppointment extends Activity {
    String ID,USERNAME, PASSWORD, DEP, SLT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department_appointment);

        Bundle bb = getIntent().getExtras();
        ID = bb.getString("id");
        USERNAME = bb.getString("username");
        PASSWORD = bb.getString("password");
        DEP = bb.getString("department");
        SLT = bb.getString("slot");
    }

    public void onClick (View view){
        Intent i;
        Bundle b = new Bundle();
        b.putString("username", USERNAME);
        b.putString("time", SLT);
        b.putString("department",DEP);
        b.putString("doctor", null);

        i = new Intent(DepartmentAppointment.this, ViewAppointment.class);
        i.putExtras(b);
        startActivity(i);
        finish();
    }


}
