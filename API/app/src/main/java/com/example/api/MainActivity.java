package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

//    private RequestQueue requestQueue;
//    private RequestQueue rq;
      RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rq = Volley.newRequestQueue(this);
        queue = MySingleton.getInstance(this.getApplicationContext())
                .getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://official-joke-api.appspot.com/random_ten",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        String strQ = jsonObject.getString("setup");
                        String strA = jsonObject.getString("punchline");
                        Log.d("MyJoke->Q", i+1+" "+strQ);
                        Log.d("MyJoke->A", i+1+" "+strA);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Random JSON Error", "onErrorResponse: "+error);
            }
        });
        queue.add(jsonArrayRequest);
//        rq.add(jsonObjectRequest);

/*        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
      *//*                  try {
                            Log.d("Array Request", "onResponse: " + response.getJSONObject(1).getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*//*
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d("Each object Array Req", "onResponse: "+
                                        jsonObject.getInt("id")+" "+jsonObject.getString("title"));
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error on Array request", "onErrorResponse: "+error);
            }
        });
        requestQueue.add(jsonArrayRequest);*/

 /*       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos/1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Object Request", "onResponse: " + response.getString("title"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error on Object Request" , "onErrorResponse: "+error);
            }
        });
        requestQueue.add(jsonObjectRequest);*/
    }
}