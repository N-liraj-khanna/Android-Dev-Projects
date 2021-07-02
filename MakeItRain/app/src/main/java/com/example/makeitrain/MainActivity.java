package com.example.makeitrain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

//    private Button moneyButton;
//    private Button tagButton;

    private TextView moneyText;
    private View background;
    private int moneyRain=0;
    private TextView message;
//    private Button anotherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.another_activity);
        setContentView(R.layout.activity_main);

//        anotherButton=findViewById(R.id.button);
//        anotherButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.activity_main);
//            }
//        });

        moneyText = findViewById(R.id.currMoney);
        background = findViewById(R.id.background);
        message = findViewById(R.id.message);

        /*
        moneyButton = findViewById(R.id.moneyButton);
        tagButton = findViewById(R.id.tagButton);

        moneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button Tag", "onClick: Button Clicked");
            }
        });*/
    }

    @SuppressLint("ResourceAsColor")
    public void moneyButton(View v){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        moneyRain+=100;
        moneyText.setText(String.valueOf(numberFormat.format(moneyRain)));
        if(moneyRain>=500){
            moneyText.setTextColor(getResources().getColor(R.color.myColor));
            message.setText("Whoa! You're becoming rich!");
            message.setTextColor(getResources().getColor(R.color.myColor));
        }
        Log.d("Make It Rain", "moneyButton =>Tapped " + moneyRain);
    }
    public void showTag(View v){
        Toast.makeText(getApplicationContext(),R.string.app_name,Toast.LENGTH_SHORT).show();
        Log.d("Tag Button", "showTag => Button Clicked ");
    }
}