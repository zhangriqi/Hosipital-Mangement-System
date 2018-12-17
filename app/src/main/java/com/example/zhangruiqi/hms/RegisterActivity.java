package com.example.zhangruiqi.hms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends Activity{

    EditText username, password, name;
    String Username, Password, Name, Gender;
    Spinner gender;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = (EditText) findViewById(R.id.etusername);
        password = (EditText) findViewById(R.id.etpassword);
        name = (EditText) findViewById(R.id.etname);
        gender = (Spinner) findViewById(R.id.spinnergender);
        register = (Button) findViewById(R.id.bRegister);

        List<String> genderlist = new ArrayList<>();
        genderlist.add("M");
        genderlist.add("F");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Password = password.getText().toString();
                Name = name.getText().toString();
                Gender = gender.getSelectedItem().toString();

                BackGround b = new BackGround();
                b.execute(Username, Password, Name, Gender);
            }
        });
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String name = params[2];
            String gender = params[3];
            String data="";
            int tmp;
            try {
                URL url = new URL("http://xxxx/android_connect/register.php"); //put your local host address here

                String urlParams = "username="+username+"&password="+password+"&name="+name+"&gender="+gender;
//                String urlParams = "username="+username+"&password="+password+"&name="+name;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
//                    Log.i("Data:", data);
                }
                is.close();
                httpURLConnection.disconnect();
                Log.i("Data:", data);
                return data;


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Malformed URL Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "IO Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

}