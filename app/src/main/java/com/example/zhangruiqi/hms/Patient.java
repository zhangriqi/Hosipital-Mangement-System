package com.example.zhangruiqi.hms;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Patient extends Activity {

    String id, username, password, name, gender;
    TextView pname;
    ImageView p_info, p_newAppointment, p_viewAppointment, p_Bills;
    Button btninfo, btnnew, btnview, btnbills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);

        pname = (TextView) findViewById(R.id.tv_p_name);
        p_info = (ImageView)findViewById(R.id.im_pinfo);
        p_newAppointment = (ImageView) findViewById(R.id.im_newAppointment);
        p_viewAppointment = (ImageView) findViewById(R.id.im_viwAppointment);
        p_Bills = (ImageView) findViewById(R.id.im_bills);

        btninfo = (Button) findViewById(R.id.b_p_info);
        btnnew = (Button) findViewById(R.id.b_p_appointment);
        btnview = (Button) findViewById(R.id.b_p_v_appointment);
        btnbills = (Button) findViewById(R.id.b_p_bills);

        Bundle bb = getIntent().getExtras();
        id = bb.getString("id");
        username = bb.getString("username");
        password = bb.getString("password");
        name = bb.getString("name");
        gender = bb.getString("gender");

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Patient.this, PersonalInfo.class);
                i.putExtra("id",id);
                i.putExtra("username", username);
                i.putExtra("password", password);
                i.putExtra("name", name);
                i.putExtra("gender", gender);
                startActivity(i);
            }
        });


        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Patient.this, NewAppointment.class);
                i.putExtra("id",id);
                i.putExtra("username", username);
                i.putExtra("password", password);
                startActivity(i);
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnbills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void onClick(View view) {
        Intent i;
        Bundle b = new Bundle();
        b.putString("username", username);
        b.putString("password", password);

        switch (view.getId()) {
            case R.id.b_p_appointment:
                i = new Intent(Patient.this, NewAppointment.class);
                break;
            case R.id.b_p_v_appointment:
                i = new Intent(Patient.this, ViewAppointment.class);
                break;
            case R.id.b_p_bills:
                i = new Intent(Patient.this, Bills.class);
                break;
            default:
                i = new Intent(Patient.this, PersonalInfo.class);
                break;
        }
        i.putExtras(b);
        startActivity(i);
    }
}
