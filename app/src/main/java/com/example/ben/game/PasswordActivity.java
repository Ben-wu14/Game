package com.example.ben.game;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.*;
import java.util.Scanner;

public class PasswordActivity extends Activity {
    String userName;
    String userPass1;
    String userPass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Button join= (Button)findViewById(R.id.button_join);
        EditText us=(EditText)findViewById(R.id.new_user_name);
        userName=us.getText().toString();
        us=(EditText)findViewById(R.id.new_password);
        userPass1=us.getText().toString();
        us=(EditText)findViewById(R.id.new_password_2);
        userPass2=us.getText().toString();
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (userPass1.equals(userPass2)){
                    SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(userName, userName);
                    editor.putString(userName+"'s password",userPass1);
                    editor.commit();
                }else
                    Toast.makeText(PasswordActivity.this,"Please try again the two input didn't match",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
