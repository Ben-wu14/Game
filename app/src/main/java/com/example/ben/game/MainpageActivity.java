package com.example.ben.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainpageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        final ArrayList<User>user_data=new ArrayList<>();
        user_data.add(new User("Ben", "ben1417"));
        user_data.add(new User("Jerry", "Jerry"));
        user_data.add(new User("Ten","Ten"));
        nam=(EditText)findViewById(R.id.edit);
        pas=(EditText)findViewById(R.id.password);
        Button button_log=(Button)findViewById(R.id.button_log);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_data.contains(new User(nam.getText().toString(),pas.getText().toString()))){
                    SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", nam.getText().toString());
                    editor.putString("password", pas.getText().toString());
                    editor.commit();
                    Intent i = new Intent(MainpageActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else Toast.makeText(MainpageActivity.this,"Your account is not exist or Incorrect passwords",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private EditText nam;
    private EditText pas;
}
