package com.example.quizapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView question;
    private int currIndexQuestion=0;
    private LinearLayout mainScreen;

    private Questions[] questions = new Questions[]{
        new Questions(R.string.first,false),
        new Questions(R.string.second,false),
        new Questions(R.string.third,true),
        new Questions(R.string.fourth,true),
        new Questions(R.string.fifth,false),
        new Questions(R.string.sixth,false),
        new Questions(R.string.seventh,false),
        new Questions(R.string.eight,false),
        new Questions(R.string.ninth,true),
        new Questions(R.string.tenth,false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        prevButton=findViewById(R.id.prev_button);
        question=findViewById(R.id.question);
        mainScreen=findViewById(R.id.main_screen);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        mainScreen.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false); break;
            case R.id.next_button:
                currIndexQuestion=(currIndexQuestion+1)%questions.length;
//                currIndexQuestion++;
                question.setText(questions[currIndexQuestion].getQuestionId());
                break;
                case R.id.prev_button:
                    if(currIndexQuestion==0) currIndexQuestion=questions.length;
                currIndexQuestion--;
//                currIndexQuestion++;
                question.setText(questions[currIndexQuestion].getQuestionId());
                break;
            default:
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                mainScreen.setBackgroundColor(Color.rgb(r,g,b));
        }
    }
    private void checkAnswer(boolean userAnswer){
        boolean answer=questions[currIndexQuestion].isAnswer();
        int toastMessage;
        if(userAnswer==answer)
            toastMessage=R.string.correct_answer;
        else
            toastMessage=R.string.wrong_answer;
        Toast.makeText(MainActivity.this,toastMessage,Toast.LENGTH_SHORT).show();
    }
}