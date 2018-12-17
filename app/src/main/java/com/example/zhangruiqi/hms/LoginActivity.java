package com.example.zhangruiqi.hms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PathEffect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends Activity {
    EditText username, password;
    Button login;
    String Username, Password;
    String ID = null, USERNAME=null, PASSWORD=null, NAME=null, GENDER=null;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.etusername);
        password = (EditText) findViewById(R.id.etpassword);
        login = (Button) findViewById(R.id.blogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Password = password.getText().toString();
                BackGround b = new BackGround();
                b.execute(Username, Password);
            }
        });

    }
    class BackGround extends AsyncTask<String, String, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            String username = params[0];
//            String password = params[1];
//            String data="";
//            int tmp;
//            try {
//                URL url = new URL("http://172.20.10.9:8000/android_connect/login.php?");
//                String urlParams = "username="+username+"&password="+password;
//
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setDoOutput(true);
//                OutputStream os = httpURLConnection.getOutputStream();
//                os.write(urlParams.getBytes());
//                os.flush();
//                os.close();
//
//                InputStream is = httpURLConnection.getInputStream();
//                while((tmp=is.read())!=-1){
//                    data+= (char)tmp;
//                }
//                is.close();
//                httpURLConnection.disconnect();
//
//                return data;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return "Exception: "+e.getMessage();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "Exception: "+e.getMessage();
//            }
//        }
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String data="";
            int tmp;
            InputStream is = null;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = "username="+username+"&password="+password;
                String url = "http://xxxx/android_connect/login.php?" + paramString; // local host address here

                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();

                return data;
            } catch (MalformedURLException e) {
                return "Exception:" + e.getMessage();
            } catch (ClientProtocolException e) {
                return "Exception:" + e.getMessage();
            } catch (IOException e) {
                return "Exception:" + e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {
                Log.i("JSON Parser", s);

                if (s.equals(("{\"user_data\":[]}"))) {
                    Toast.makeText(LoginActivity.this,"Wrong password, or register",Toast.LENGTH_LONG).show();
                } else {
                    JSONObject root = new JSONObject(s);
                    JSONObject user_data = root.getJSONObject("user_data");
                    ID = user_data.getString("id");
                    USERNAME = user_data.getString("username");
                    PASSWORD = user_data.getString("password");
                    NAME = user_data.getString("name");
                    GENDER = user_data.getString("gender");

                    Intent i = new Intent(LoginActivity.this, Patient.class);
                    i.putExtra("id",ID);
                    i.putExtra("username", USERNAME);
                    i.putExtra("password", PASSWORD);
                    i.putExtra("name", NAME);
                    i.putExtra("gender", GENDER);
                    i.putExtra("err", err);
                    startActivity(i);

                }

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }
        }
    }
}