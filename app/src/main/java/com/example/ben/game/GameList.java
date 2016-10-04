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
    ArrayList<ArrayFile> arrayFileArrayList;
    ArrayFile dataFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        getUsername();

        SharedPreferences sp=getSharedPreferences("filePath",MODE_APPEND);
        String path=sp.getString("path","");

        SharedPreferences sp2=getSharedPreferences("filePath",MODE_APPEND);
        SharedPreferences.Editor editor2=sp2.edit();
        editor2.putString("sender","GameList");
        editor2.commit();

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
                ArrayFile array=(ArrayFile)object;
                arrayFileArrayList.add(array);
            }
            input.close();
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
                dataFile=(ArrayFile)parent.getItemAtPosition(position);
                Intent i=new Intent(GameList.this,Game2.class);
                Bundle bundle2=new Bundle();
                bundle2.putSerializable("FileData", arrayFileArrayList);
                i.putExtras(bundle2);
                Bundle bundle = new Bundle();
                bundle.putSerializable("position", dataFile);
                i.putExtras(bundle);

                startActivityForResult(i,1);
            }
        });
    }
    public void getUsername(){
        SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
        username=sp.getString("name","");
    }
}
