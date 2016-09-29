package com.example.ben.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends Activity {
    String userName;
    String userPass1;
    String userPass2;
    private EditText nam;
    private EditText pas1;
    private EditText pas2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button join= (Button)findViewById(R.id.button_join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nam=(EditText)findViewById(R.id.new_user_name);
                userName=nam.getText().toString();
                pas1=(EditText)findViewById(R.id.new_password);
                userPass1=pas1.getText().toString();
                pas2=(EditText)findViewById(R.id.new_password_2);
                userPass2=pas2.getText().toString();
                if (userPass1.equals(userPass2)) {
                    SharedPreferences sp = getSharedPreferences("Login", MODE_APPEND);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(userName, userName);
                    editor.putString(userName + "'s password", userPass1);
                    editor.commit();
                    Intent i=new Intent(Register_Activity.this,MainActivity.class);
                    startActivity(i);
                } else
                    Toast.makeText(Register_Activity.this, "Please try again the two input didn't match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
