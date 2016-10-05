package com.example.ben.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RenameActivity extends Activity {
    String new_name;
    String old_name;
    String old_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);



        ImageView im=(ImageView)findViewById(R.id.rename_commit);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed=(EditText)findViewById(R.id.rename_input);
                new_name=ed.getText().toString();

                SharedPreferences sp2 = getSharedPreferences("Login", MODE_APPEND);
                old_name=sp2.getString("name","");
                old_password=sp2.getString("password","");
                SharedPreferences.Editor editor2 = sp2.edit();
                editor2.remove(old_name);
                editor2.remove(old_name+"'s password");
                editor2.commit();

                SharedPreferences sp = getSharedPreferences("Login", MODE_APPEND);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(new_name, new_name);
                editor.putString(new_name + "'s password", old_password);
                editor.putString("name",new_name);
                editor.putString("password",old_password);
                editor.commit();
                Intent i=new Intent(RenameActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
