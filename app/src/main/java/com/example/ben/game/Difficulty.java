package com.example.ben.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Difficulty extends Activity {
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        editor = sp.edit();

        SharedPreferences sp2=getSharedPreferences("filePath",MODE_APPEND);
        SharedPreferences.Editor editor2=sp2.edit();
        editor2.putString("sender","Difficulty");
        editor2.commit();

        Button easy=(Button)findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Difficulty.this,Game2.class);
                difficulty=1;
                editor.putInt("difficulty",difficulty);
                editor.commit();
                startActivityForResult(i,1);

            }
        });
        Button middle=(Button)findViewById(R.id.middle);
        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Difficulty.this,Game2.class);
                difficulty=2;
                editor.putInt("difficulty",difficulty);
                editor.commit();
                startActivityForResult(i,1);

            }
        });
        Button diff=(Button)findViewById(R.id.difficult);
        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Difficulty.this,Game2.class);
                difficulty=3;
                editor.putInt("difficulty",difficulty);
                editor.commit();
                startActivityForResult(i,1);

            }
        });
    }
    public int difficulty=1;

}
