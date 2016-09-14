package com.example.ben.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Difficulty extends Activity {
    Intent i=new Intent(Difficulty.this,Game2.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        Button easy=(Button)findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
                difficulty=1;
            }
        });
        Button middle=(Button)findViewById(R.id.middle);
        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
                difficulty=2;
            }
        });
        Button diff=(Button)findViewById(R.id.difficult);
        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
                difficulty=3;
            }
        });
    }
    public int difficulty=1;
}
