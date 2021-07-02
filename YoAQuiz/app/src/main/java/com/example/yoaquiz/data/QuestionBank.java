package com.example.yoaquiz.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.yoaquiz.controller.AppController;
import com.example.yoaquiz.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    ArrayList<Question> allQuestions = new ArrayList<>();
    String TAG = QuestionBank.class.getSimpleName();
    private RequestQueue requestQueue;
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> questionList(final QuestionRetrieveAsyncResponse sendAllQuestions){
        requestQueue = AppController.getInstance().getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                (JSONArray) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<100;i++){
                    try {
                        String q = response.getJSONArray(i).get(0).toString();
                        boolean a = (boolean) response.getJSONArray(i).get(1);
                        allQuestions.add(new Question(q,a));
//                        Log.d(TAG, "onResponse: "+q);
//                        Log.d(TAG, "onResponse: "+a);
//                        Log.d(TAG, "onResponse: "+new Question(q,a));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                sendAllQuestions.processFinished(allQuestions);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return allQuestions;
    }

}
