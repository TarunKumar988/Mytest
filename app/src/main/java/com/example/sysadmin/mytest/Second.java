package com.example.sysadmin.mytest;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Second extends AppCompatActivity {
EditText email,pass;
TextView forgetPassword;
ImageView imageView;

    static String name;
    static String password;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pass=findViewById(R.id.password);
        email=findViewById(R.id.name);
        forgetPassword=findViewById(R.id.forgot_password);
        imageView=findViewById(R.id.imageView);

        name=getIntent().getExtras().getString("Name");


        email.setText((String)name);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),forget_password.class);
                i.putExtra("email",name);
                startActivity(i);

            }
        });



    }

    public void logIn(View view) {
        password= String.valueOf(pass.getText());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://tarunkm02.000webhostapp.com/login.php?E="+name+"&P="+password,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("*******","   "+response);
                            JSONObject jsonObject=new JSONObject(response);
//                            JSONArray jsonArray=jsonObject.getJSONArray("result");
                            int sucess=0;
                            String msg=".......";
                            for (int i=0;i<jsonObject.length();i++)
                            {
                                sucess= (Integer) jsonObject.opt("success");
                                msg= (String) jsonObject.opt("message");
                            }
                            if(sucess==1)
                            {
                                Toast.makeText(Second.this, ""+msg, Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(Second.this, "Something Error  ...  " + msg, Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Second.this, "Exception Inside the Connection "+error, Toast.LENGTH_SHORT).show();
            }
        }

        );

        RequestQueue resRequestQueue= Volley.newRequestQueue(this);
        resRequestQueue.add(stringRequest);
    }
}
