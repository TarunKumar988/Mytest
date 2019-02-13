package com.example.sysadmin.mytest;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

/**
 * Created by sysadmin on 12/2/19.
 */

public class MyAsync extends AsyncTask<String,Integer,String> {
    @Override
    protected void onPreExecute() {
        Toast.makeText(MainActivity.con, "Starting.......", Toast.LENGTH_LONG).show();
        MainActivity.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
      MainActivity.progressBar.setProgress(values[0]);
    }
git
    @Override
    protected String doInBackground(String... strings) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://tarunkm02.000webhostapp.com/my.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            MainActivity.jsonArray=jsonObject.getJSONArray("result");
                            MainActivity.arr=new String[MainActivity.jsonArray.length()];
                            String name;
                            for (int i=0;i<MainActivity.jsonArray.length();i++)
                            {
                                name=String.valueOf(MainActivity.jsonArray.opt(i));
                                name=name.substring((name.indexOf(':')+2),name.lastIndexOf('"'));
                                Log.i("*******",""+name);
                                MainActivity.arr[i]= name;
                                publishProgress(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.con, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.con);
        requestQueue.add(stringRequest);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        try
        {
            Thread.sleep(3000);
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.con, "Exception "+e, Toast.LENGTH_SHORT).show();
        }
       MainActivity.ls.setVisibility(View.VISIBLE);
       MainActivity.progressBar.setVisibility(View.GONE);
        MainActivity.show.setVisibility(View.VISIBLE);

    }
}
