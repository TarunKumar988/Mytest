package com.example.sysadmin.mytest;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by sysadmin on 11/2/19.
 */

public class Controller implements Callback<List<Change>> {

    static final String BASE_URL = "https://tarunkm02.000webhostapp.com/";
    static List<Change> changesList;
    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<Change>> call = gerritAPI.loadChanges("status:open");
        call.enqueue(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        if(response.isSuccessful()) {

            changesList = response.body();
                  for (int i = 0; i < changesList.size(); i++) {
                      Log.i("******",String.valueOf(changesList.get(i).subject));
                      MainActivity.list.add(changesList.get(i).subject);
                // changesList.forEach(change -> System.out.println("*************"+change.subject));
            }

        }
        else
        {
            System.out.println(response.errorBody());

        }

    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {
        t.printStackTrace();
    }
}
