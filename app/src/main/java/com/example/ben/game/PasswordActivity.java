package com.example.ben.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends Activity {
    String userName;
    String userPass1;
    String userPass2;
    String old_pass;
    String old;
    private EditText pas1;
    private EditText pas2;
    private EditText oldpas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Button join= (Button)findViewById(R.id.password_Confirm);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp2 = getSharedPreferences("Login", MODE_APPEND);
                old=sp2.getString("name","");
                oldpas = (EditText) findViewById(R.id.origin_pass_input);
                old_pass = oldpas.getText().toString();
                pas1 = (EditText) findViewById(R.id.change_password);
                userPass1 = pas1.getText().toString();
                pas2 = (EditText) findViewById(R.id.change_password_2);
                userPass2 = pas2.getText().toString();
                if (userPass1.equals(userPass2)&&old.equals(old_pass)) {
                    SharedPreferences sp = getSharedPreferences("Login", MODE_APPEND);
                    SharedPreferences.Editor editor = sp.edit();
                    userName=sp.getString("name","");
                    editor.putString(userName, userName);
                    editor.putString(userName + "'s password", userPass1);
                    editor.putString("password", userPass1);
                    editor.commit();
                    Intent i = new Intent(PasswordActivity.this, MainActivity.class);
                    startActivity(i);
                } else if((!userPass1.equals(userPass2))&&old.equals(old_pass))
                    Toast.makeText(PasswordActivity.this, "Please try again the two input didn't match", Toast.LENGTH_SHORT).show();
                else Toast.makeText(PasswordActivity.this, "Please try again ,wrong password", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
