package com.example.ben.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class GameList extends Activity {
    String username;
    ArrayData data;
    int[][] userAnser;
    int difficulty;
    int total_blank;
    int min;
    int sec;
    ArrayList<ArrayFile> arrayFileArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        getUsername();

        SharedPreferences sp=getSharedPreferences("filePath",MODE_APPEND);
        String path=sp.getString("path","");

        arrayFileArrayList=new ArrayList<>();
        String filePath = path + username+"game.dat";
        File f=new File(filePath);
        if (!f.exists())
        {
            try
            {
                f.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try{
            ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)));
            Object object;
            while((object=input.readObject()) != null){
                data=(ArrayData)object;
                userAnser=(int[][])input.readObject();
                difficulty=input.readInt();
                total_blank=input.readInt();
                min=input.readInt();
                sec=input.readInt();
                arrayFileArrayList.add(new ArrayFile(data,userAnser,difficulty,total_blank,min,sec));
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        ListView listView=(ListView)findViewById(R.id.list);
        ArrayFileAdapter adapter=new ArrayFileAdapter(this,arrayFileArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // ArrayFile dataFile=(ArrayFile)parent.getItemAtPosition(position);
                Intent i=new Intent(GameList.this,Game2.class);
                i.putExtra("FileData",arrayFileArrayList);
                i.putExtra("Position",position);

                startActivityForResult(i,1);
            }
        });
    }
    public void getUsername(){
        SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
        username=sp.getString("name","");
    }
}
