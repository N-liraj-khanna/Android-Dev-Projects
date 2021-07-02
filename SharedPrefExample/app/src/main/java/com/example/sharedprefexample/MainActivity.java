package com.example.sharedprefexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "Message of Preferences";
    private EditText enteredText;
    private TextView setText;
    private Button pressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredText = findViewById(R.id.enterText);
        setText = findViewById(R.id.enteredText);
        pressButton = findViewById(R.id.button);

        pressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enterMessage = enteredText.getText().toString().trim();

                SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("message",enterMessage);

                editor.apply();
            }
        });

        SharedPreferences getDataFromSP = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        String text = getDataFromSP.getString("message","No Input!").toString().trim();

        setText.setText(text);

    }
}