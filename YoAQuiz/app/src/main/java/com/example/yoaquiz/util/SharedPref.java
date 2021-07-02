package com.example.yoaquiz.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static SharedPreferences myPreferences;
    private static final String MESSAGE_ID = "Shared Pref Data Stored";


    public SharedPref(Activity activity) {
        this.myPreferences=activity.getSharedPreferences(MESSAGE_ID, activity.MODE_PRIVATE);
    }

    public void saveQuestion(int currQuestion){
        myPreferences.edit().putInt("currQuestion", currQuestion).apply();
    }

    public void saveHighestScore(int yourScoreVal){
        int check = myPreferences.getInt("myHighestScore",0);
        if(check<yourScoreVal) {
            myPreferences.edit().putInt("myHighestScore", yourScoreVal).apply();
        }
    }
    public static int getHighestScore(){
        return myPreferences.getInt("myHighestScore",0);
    }
}
