package com.example.database.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.database.R;

public class DetailsPage extends AppCompatActivity {
    private TextView nameText;
    private TextView phoneNumText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameText=findViewById(R.id.nameDets);
        phoneNumText=findViewById(R.id.phoneNum);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String phoneNum = bundle.getString("phoneNum");

        nameText.setText(name);
        phoneNumText.setText(phoneNum);

    }
}
