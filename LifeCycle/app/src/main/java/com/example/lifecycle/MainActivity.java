package com.example.lifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button guessButton;
    private EditText enteredText;
    private final int REQUEST_CODE = 5555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LifeCycle Create", "onStart: onCreate() Called");
        Toast.makeText(MainActivity.this,"onCreate() called",Toast
        .LENGTH_SHORT).show();

        guessButton=findViewById(R.id.guessButton);
        enteredText=findViewById(R.id.enterText);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getText = enteredText.getText().toString().trim();

                if(!getText.isEmpty()){
                    Intent intent = new Intent(MainActivity.this,GuessWhatWhew.class);
                    intent.putExtra("guessText",getText);
                    intent.putExtra("name","LK");
                    startActivityForResult(intent,REQUEST_CODE);
                }else{
                    Toast.makeText(MainActivity.this,"Enter Something -_-",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                assert data != null;
                String msg = data.getStringExtra("messageBack");
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        }
    }
    //
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d("LifeCycle Start", "onStart: onStart() Called");
//        Toast.makeText(MainActivity.this,"onCreate() called",Toast
//        .LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d("LifeCycle Resume", "onResume: onResume() Called");
//        Toast.makeText(MainActivity.this,"onResume() called",Toast
//                .LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d("LifeCycle Pause", "onPause: onPause() Called");
//        Toast.makeText(MainActivity.this,"onPause() called",Toast
//                .LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d("LifeCycle Stop", "onStop: onStop() Called");
//        Toast.makeText(MainActivity.this,"onStop() called",Toast
//                .LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d("LifeCycle Destroy", "onDestroy: onDestroy() Called");
//        Toast.makeText(MainActivity.this,"onDestroy() called",Toast
//                .LENGTH_SHORT).show();
//    }
}