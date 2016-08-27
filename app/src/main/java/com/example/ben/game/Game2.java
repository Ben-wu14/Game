package com.example.ben.game;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Game2 extends Activity {
    int last_id=0;
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
                final TextView textView=new TextView(this);
                textView.setText(""+a[i][j]);
                textView.setId(i*10+j);
                textView.setWidth(width);
                textView.setHeight(width);
                textView.setGravity(0x11);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setBackgroundColor(Color.parseColor("#B3E5FC"));
                        int co=textView.getDrawingCacheBackgroundColor();
                        TextView pre=(TextView)findViewById(last_id);
                        pre.setBackgroundColor(co);
                        last_id=textView.getId();
                    }
                });
                textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                });
                tableRow.addView(textView);
            }
            layout.addView(tableRow);
        }
    }
}
