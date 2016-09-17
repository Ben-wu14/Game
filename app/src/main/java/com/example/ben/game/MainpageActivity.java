package com.example.ben.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainpageActivity extends Activity {
    public String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        ArrayList<User>user_data=new ArrayList<>();
        user_data.add(new User("Ben", "ben1417"));
        user_data.add(new User("Jerry", "Jerry"));
        user_data.add(new User("Ten","Ten"));
        ed=(EditText)findViewById(R.id.edit);
        Button button_log=(Button)findViewById(R.id.button_log);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", ed.getText().toString());
                editor.commit();
                Intent i = new Intent(MainpageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    private EditText ed;
}
