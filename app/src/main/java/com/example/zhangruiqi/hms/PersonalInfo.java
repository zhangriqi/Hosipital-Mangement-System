package com.example.zhangruiqi.hms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PersonalInfo extends Activity {

    String id,username,password,name, gender;
    TextView idTV, unameTV, passwordTV, nameTV, genderTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);

        Bundle bb = getIntent().getExtras();
        id = bb.getString("id");
        username = bb.getString("username");
        password = bb.getString("password");
        name = bb.getString("name");
        gender = bb.getString("gender");

        idTV = (TextView)findViewById(R.id.id);
        unameTV = (TextView) findViewById(R.id.username);
        genderTV = (TextView) findViewById(R.id.gender);
        nameTV = (TextView) findViewById(R.id.name);
        passwordTV = (TextView) findViewById(R.id.password);

        idTV.setText(id);
        unameTV.setText(username);
        passwordTV.setText(password);
        nameTV.setText(name);
        genderTV.setText(gender);

    }

    public void onClick(View view){
        Intent i;
        Bundle b = new Bundle();
        b.putString("id", id);
        b.putString("username",username);
        b.putString("password",password);
        b.putString("name",name);
        b.putString("gender", gender);

        i = new Intent(PersonalInfo.this, Update.class);
        i.putExtras(b);
        startActivity(i);
        finish();
    }
}
