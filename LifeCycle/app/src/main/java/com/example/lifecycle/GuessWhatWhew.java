package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GuessWhatWhew extends AppCompatActivity {

    private TextView showReceivedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_what_whew);

        showReceivedText=findViewById(R.id.recievedText);

        Bundle allExtras = getIntent().getExtras();

        if(!allExtras.isEmpty()){
            showReceivedText.setText(allExtras.getString("guessText"));
            Log.d("MyName","My name Called by Bundle "+allExtras.getString("name"));
        }

        showReceivedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("messageBack","This is from second Activity");
                setResult(RESULT_OK,intent);
                finish();
            }
        });


//        if(getIntent().getStringExtra("guessText")!="null")
//            showReceivedText.setText(getIntent().getStringExtra("guessText"));
    }
}