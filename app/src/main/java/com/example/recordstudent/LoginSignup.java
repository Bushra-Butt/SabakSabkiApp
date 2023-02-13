package com.example.recordstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginSignup extends AppCompatActivity{
    EditText txtMail,Password;
    TextView Signup,signupText;
    Button previous,Ok;
    DbController dbcontroller;
    Intent intents1;
    Integer variable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        txtMail=findViewById(R.id.editTextTextEmailAddress);
        Password=findViewById(R.id.editTextNumberPassword);
        Ok=findViewById(R.id.Loginbutton);
        intents1=getIntent();
        variable=intents1.getIntExtra("Variable",0);
        previous=findViewById(R.id.buttonsignup);
        dbcontroller=new DbController(LoginSignup.this);
        previous=findViewById(R.id.buttonsignup);
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent nextIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(nextIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(view.getContext(), "Previous...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMail.getText().length()>0 && Password.getText().length()>0) {
                    if (variable == 1) { //LOgin
//                        if (dbcontroller.getVerification(txtMail.getText().toString(), Password.getText().toString())) {
                            try {
                                Intent nextIntent = new Intent(getApplicationContext(), showRecordStudent.class);
                                startActivity(nextIntent);
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(view.getContext(), "Not Login...", Toast.LENGTH_SHORT).show();
                            }
//                        } else {
//                              Toast.makeText(view.getContext(), "User is not verified", Toast.LENGTH_SHORT).show();
//                        }
                    }
                    else if (variable == 2) {
//                        if (dbcontroller.isUserUnique(txtMail.getText().toString()) == true)
//                        {
//                            Signup.setText("");
//                            User user = new User();
//                            user.setName(txtMail.getText().toString());
//                            user.setPassword(Password.getText().toString());
//                            dbcontroller.AddUser(user);
                            try {
                                Intent nextIntent = new Intent(getApplicationContext(), showRecordStudent.class);
                                startActivity(nextIntent);
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(view.getContext(), "Not Signup...", Toast.LENGTH_SHORT).show();
                            }

                        }
//                        else {
//                            Toast.makeText(view.getContext(), "UserName already exists", Toast.LENGTH_SHORT).show();
//                            txtMail.setHintTextColor(getResources().getColor(R.color.purple_500));
//                            Password.setHintTextColor(getResources().getColor(R.color.purple_500));
//                        }
                    }
                else
                {
                    txtMail.setHintTextColor(getResources().getColor(R.color.purple_200));
                    Password.setHintTextColor(getResources().getColor(R.color.purple_200));
                }
            }
        });
    }
}