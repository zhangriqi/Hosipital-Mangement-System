package com.example.zhangruiqi.hms;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class ViewAppointment extends Activity{
    String time, department;
    TextView timeTV, deptTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_appointment);



    }
}
