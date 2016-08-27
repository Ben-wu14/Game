package com.example.ben.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by ben on 2016/8/26.
 */
public class Game extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);
        int i,j;
        int a[][]=new int[9][9];
        a[1][2]=6;
        a[8][8]=8;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels/9;
        TableLayout layout=(TableLayout)findViewById(R.id.tableLayout);
        for(i=0;i<9;i++)
        {
           TableRow tableRow=(TableRow)findViewById(R.id.tableRow);
           for (j=0;j<9;j++){
               TextView textView=new TextView(this);
               textView.setText(""+a[i][j]);
               textView.setWidth(width);
               textView.setHeight(width);
               tableRow.addView(textView);
           }
            layout.addView(tableRow);
        }
    }
}
