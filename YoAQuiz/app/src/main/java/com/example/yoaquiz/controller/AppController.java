package com.example.yoaquiz.controller;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class AppController extends Application{

    private String TAG = AppController.class.getSimpleName();
    private static AppController firstInstance;
    private RequestQueue requestQueue;

    public static synchronized AppController getInstance() {
//        if (instance == null) { //Did that because once we connect our model to view
//            instance = new AppController();//it will automatically take care
//        }                       //of instantiating the class once
        return firstInstance;
    }

    @Override
    public void onCreate() {  //this is there instead of explicit
        super.onCreate(); //constructor which has to be executed
        firstInstance=this; //this will declare "this" activity
    }//as the firstInstance and will be created only once since its static

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req,String tag) {
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestQueue().add(req);
    }
    public void cancelAllPendingRequests(Object tag){
        if(requestQueue!=null){
            requestQueue.cancelAll(tag);
        }
    }
}
/*

    private String TAG = AppController.class.getSimpleName();

    private static AppController myOnlyInstance;
    private RequestQueue requestQueue;

    public static synchronized AppController getInstance() {
        if (myOnlyInstance == null) {
            myOnlyInstance = new AppController();
        }
        return myOnlyInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myOnlyInstance=this;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req,String tag){
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestQueue().add(req);
    }
    public void cancelAllPendingRequest(String tag){
        if(requestQueue!=null){
            requestQueue.cancelAll(tag);
        }
    }
///////////
 */