package com.example.ben.game;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;

public class GameList extends Activity {
String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        getUsername();
    }
    public void getUsername(){
        SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
        username=sp.getString("name","");
    }
}
