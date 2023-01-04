package com.example.learnlink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_register = findViewById(R.id.btn_register);
        Button btn_login = findViewById(R.id.btn_login);
        Intent i = new Intent(this, RegisterActivity.class);
        Intent i2 = new Intent(this, MainActivity.class);
        btn_register.setOnClickListener(view -> {
            startActivity(i);
        });
        btn_login.setOnClickListener(view -> {
            startActivity(i2);
        });
    }
}