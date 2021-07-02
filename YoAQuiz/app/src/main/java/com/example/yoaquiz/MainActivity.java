package com.example.yoaquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoaquiz.data.QuestionBank;
import com.example.yoaquiz.data.QuestionRetrieveAsyncResponse;
import com.example.yoaquiz.model.Question;
import com.example.yoaquiz.util.SharedPref;

import java.text.MessageFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String MESSAGE_ID = "Shared Pref Data Stored";
    String TAG = MainActivity.class.getSimpleName();
    private TextView questionDisplay;
    private TextView scoreCounter;
    private TextView highestScoreText;
    private int highestScore=0;
    private Button trueButton;
    private Button falseButton;
    private Button resetButton;
    private ImageButton prevButton;
    private ImageButton nextButton;
    List<Question> questionsList;
    private TextView yourScore;
    private int yourScoreVal=0;
    private int currentQuestion =0;

    private SharedPref sharedPref;

    @Override
    protected void onPause() {
        super.onPause();
        sharedPref = new SharedPref(this);
        sharedPref.saveHighestScore(yourScoreVal);
        sharedPref.saveQuestion(currentQuestion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextButton:
                currentQuestion++;
                if(currentQuestion >questionsList.size()-1) currentQuestion = 0;
                nextOrPrev();
                break;
            case R.id.prevButton:
                currentQuestion--;
                if(currentQuestion <0) currentQuestion =questionsList.size()-1;
                    nextOrPrev();
                break;
            case R.id.trueButton:
                checkAnswer(true);
                break;
            case R.id.falseButton:
                checkAnswer(false);
                break;
            case R.id.resetButton:
                resetFunction();
                break;
        }
    }
    public void resetFunction(){
        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        sharedPreferences.edit().putInt("myHighestScore",0);
        yourScoreVal=0;
        yourScore.setText("Current Score: "+yourScoreVal);
        highestScore=0;
//        sharedPref.resetFucntion();
        highestScoreText.setText("Highest Score is => "+highestScore);    }

    public void nextOrPrev(){
        questionDisplay.setText(questionsList.get(currentQuestion).getQuestion());
        scoreCounter.setText(currentQuestion +1+" of "+questionsList.size());
    }

    private void checkAnswer(boolean ans){
        if(ans==questionsList.get(currentQuestion).isTrue()){
            fadeAnime();
            nextOrPrev();
            Toast.makeText(MainActivity.this,R.string.correctAnswer,Toast.LENGTH_SHORT).show();
            if(questionsList.get(currentQuestion).getFlag()==0) {
                yourScoreVal+=100;
                yourScore.setText("Current Score: "+yourScoreVal);
                questionsList.get(currentQuestion).setFlag(1);
            }
            nextButton.performClick();
        }else{
            shakeAnime();
            if(questionsList.get(currentQuestion).getFlag()==1) {
                yourScoreVal-=100;
                yourScore.setText("Current Score: "+yourScoreVal);
                questionsList.get(currentQuestion).setFlag(0);
            }
            nextOrPrev();
            Toast.makeText(MainActivity.this,R.string.wrongAnswer,Toast.LENGTH_SHORT).show();
            nextButton.performClick();
        }
    }

    public void fadeAnime(){
        CardView card = findViewById(R.id.cardView);
        AlphaAnimation alphaAnime = new AlphaAnimation(1.0f,0.0f);
        alphaAnime.setRepeatCount(1);
        alphaAnime.setRepeatMode(Animation.REVERSE);
        alphaAnime.setDuration(300);

        card.setAnimation(alphaAnime);

        alphaAnime.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                card.setCardBackgroundColor(Color.rgb(152,251,152));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void shakeAnime(){
        CardView card = findViewById(R.id.cardView);
        Animation anime = AnimationUtils.loadAnimation(MainActivity.this
                ,R.anim.shake_anime);
        card.setAnimation(anime);
        anime.setAnimationListener(new Animation.AnimationListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationStart(Animation animation) {
                card.setCardBackgroundColor(Color.rgb(225,111,124));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionDisplay = findViewById(R.id.questionDisplay);
        scoreCounter = findViewById(R.id.scoreText);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        resetButton = findViewById(R.id.resetButton);
        yourScore = findViewById(R.id.yourScore);
        highestScoreText = findViewById(R.id.highestScore);

        questionDisplay.setOnClickListener(this);
        scoreCounter.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        currentQuestion = sharedPreferences.getInt("currQuestion",0);
        highestScore = sharedPreferences.getInt("myHighestScore",0);

        highestScoreText.setText("Highest Score is => "+highestScore);

        QuestionBank qB = new QuestionBank();
        questionsList = qB.questionList(new QuestionRetrieveAsyncResponse() {
            @Override
            public void processFinished(List<Question> allQuestions) {
                scoreCounter.setText(currentQuestion +1+" of "+questionsList.size());
                yourScore.setText("Current Score: "+yourScoreVal);


                questionDisplay.setText(allQuestions.get(currentQuestion).getQuestion());
                Log.d(TAG, "processFinished: "+allQuestions);
            }
        });
    }
}