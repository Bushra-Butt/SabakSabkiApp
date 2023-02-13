package com.example.recordstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton login,signup;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            login = findViewById(R.id.loginButton);
            signup = findViewById(R.id.signupButton2);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent nextIntent = new Intent(getApplicationContext(), showRecordStudent.class);
                        nextIntent.putExtra("Variable",1);
                        startActivity(nextIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
//                     ToastHelper.MakeShortText("CLICK LOGIN BUTTON HAS SOME ISSUE");
                    }
                }
            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent nextIntent = new Intent(getApplicationContext(), showRecordStudent.class);
                        nextIntent.putExtra("Variable",2);
                        startActivity(nextIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
//                     ToastHelper.MakeShortText("CLICK LOGIN BUTTON HAS SOME ISSUE");
                    }
                }
            });
        }
}