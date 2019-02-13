package com.example.sysadmin.mytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class forget_password extends AppCompatActivity {
EditText email,pass,cPass;
String eml,pss,Cpss;
Button btn;
    static String msg="";
    static int sucess=0;
static String emailadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        cPass=findViewById(R.id.conform_pass);
        btn=findViewById(R.id.reset);

        emailadd=getIntent().getExtras().getString("email");

            email.setText(emailadd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eml= String.valueOf(email.getText());
                pss= String.valueOf(pass.getText());
                Cpss= String.valueOf(cPass.getText());
                if(pss.equals(Cpss)&& pss.length()>6 )
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://tarunkm02.000webhostapp.com/UpdatePassword.php?P= "+pss +" & E="+emailadd,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject=new JSONObject(response);

                                        for (int i=0;i<=jsonObject.length();i++)
                                        {
                                            msg= (String) jsonObject.opt("message");
                                            sucess= (Integer) jsonObject.opt("Success_msg");
                                        }
                                        if (sucess==1)
                                        {
                                            Toast.makeText(forget_password.this, "Getting Successfull"+msg, Toast.LENGTH_SHORT).show();
                                            finish();

                                        }
                                        else
                                        {
                                            Toast.makeText(forget_password.this, "Something Error", Toast.LENGTH_SHORT).show();

                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }
                    );
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);


                }
                else
                {
                    Toast.makeText(forget_password.this, "Password Not matches, please enter same password in Both Fields or Fields not allow empty values ", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
