package com.example.sysadmin.mytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
   static ArrayList<Object> list=new ArrayList<>();
  static   TextView textView;
  static ListView ls;
   static JSONArray jsonArray;
   static String arr[];
   static Context con;
   Button click,show;
   static Intent i;
   static ProgressBar progressBar;
    MyAsync myAsync=new MyAsync();
static char a[];



    static StringBuilder arg=new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        con=getApplication();
//        textView=findViewById(R.id.txt);
//        Controller controller = new Controller();
//        controller.start();
        ls=findViewById(R.id.list_view);
        progressBar=findViewById(R.id.progress);
        click=findViewById(R.id.click);
        show=findViewById(R.id.show);

    }


//  static   void show()
//    {
//        StringBuilder sc=new StringBuilder();
////       for (int i=0;i<list.size();i++)
////       {
////           sc=sc.append(" \n\n  "+list.get(i));
////
////       }
////      textView.setText(arg);
//    }




    public void ClickedMe(View view) {
        myAsync.execute();
        show.setVisibility(View.VISIBLE);
        click.setVisibility(View.INVISIBLE);
    }

    public void Show(View view) {
        ArrayAdapter a=new ArrayAdapter(this,R.layout.view_item,R.id.txt,arr);
        ls.setAdapter(a);
        ls.setOnItemClickListener(new mylistner());

    }

    class mylistner implements  AdapterView.OnItemClickListener {
        mylistner()
        {

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.con, "Data clicked : "+MainActivity.arr[position], Toast.LENGTH_SHORT).show();
           i=new Intent(MainActivity.con,Second.class);
            i.putExtra("Name" , MainActivity.arr[position]);
            startActivity(i);
            finish();


        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}




