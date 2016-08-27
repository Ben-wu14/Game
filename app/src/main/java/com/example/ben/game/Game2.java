package com.example.ben.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Game2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
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
            TableRow tableRow=new TableRow(this);
            for (j=0;j<9;j++){
                TextView textView=new TextView(this);
                textView.setText(""+a[i][j]);
                textView.setWidth(width);
                textView.setHeight(width);
                textView.setGravity(0x11);
                textView.setBackgroundResource(R.drawable.add_border);
                tableRow.addView(textView);
            }
            layout.addView(tableRow);
        }
    }
}
