package com.example.zhangruiqi.hms;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewAppointment extends Activity {
    String ID,USERNAME, PASSWORD, DEP, SLT, EXP;
    Spinner department, slot, expert;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_appointment);

        Bundle bb = getIntent().getExtras();
        ID = bb.getString("id");
        USERNAME = bb.getString("username");
        PASSWORD = bb.getString("password");

        department = (Spinner) findViewById(R.id.spinner_departments);
        slot = (Spinner) findViewById(R.id.spinner_slot);
        expert = (Spinner) findViewById(R.id.spinner_expert);
        next = (Button) findViewById(R.id.b_next);

        List<String> s_special = new ArrayList<>();
        s_special.add("Dental");
        s_special.add("Internal Medicine");
        s_special.add("Surgery");

        ArrayAdapter<String> adapter_special = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, s_special);
        adapter_special.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter_special);

        List<String> s_slot = new ArrayList<>();
        s_slot.add("Morning (9-12)");
        s_slot.add("Afternoon (1-4)");

        ArrayAdapter<String> adapter_slot = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, s_slot);
        adapter_slot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slot.setAdapter(adapter_slot);

        List<String> s_expert = new ArrayList<>();
        s_expert.add("Expert");
        s_expert.add("Department"); //general means 科室预约／普通门诊

        ArrayAdapter<String> adapter_expert = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, s_expert);
        adapter_expert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expert.setAdapter(adapter_expert);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLT = slot.getSelectedItem().toString();
                DEP = department.getSelectedItem().toString();
                EXP = expert.getSelectedItem().toString();

                BackGround b = new BackGround();
                b.execute(ID, DEP, SLT, EXP);
            }
        });
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String id = params[0];
            String department = params[1];
            String slot = params[2];
            String expert = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://172.20.10.9:8000/android_connect/newappointment.php");
                String urlParams = "id=" + id + "&department=" + department + "&a_time=" + slot + "&doctor=" + expert;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
//                    Log.i("Data:", data);
                }
                is.close();
                httpURLConnection.disconnect();
                Log.i("Data:", data);
                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Malformed URL Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "IO Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("")) {
                s = "Data saved successfully.";
            }
            Intent i;
            if (EXP == "Department") {
                //Go to Department appointment
                //DO SOMETHING HERE <- Add data to database, use AsnTack
                i = new Intent(NewAppointment.this, DepartmentAppointment.class);
                i.putExtra("id", ID);
                i.putExtra("username", USERNAME);
                i.putExtra("password", PASSWORD);
                i.putExtra("slot", SLT);
                i.putExtra("department", DEP);
                startActivity(i);
                Toast.makeText(NewAppointment.this, s, Toast.LENGTH_LONG).show();
//                finish();
            } else {
                //Go to Expert appointment
                i = new Intent(NewAppointment.this, ExpertAppointment.class);
                i.putExtra("id", ID);
                i.putExtra("username", USERNAME);
                i.putExtra("password", PASSWORD);
                i.putExtra("slot", SLT);
                i.putExtra("department", DEP);
//                Log.i("DEP: ", DEP);
                startActivity(i);
                Toast.makeText(NewAppointment.this, s, Toast.LENGTH_LONG).show();
            }
        }

    }
}
