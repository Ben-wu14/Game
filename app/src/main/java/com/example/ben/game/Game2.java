package com.example.ben.game;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
        int width = displaymetrics.widthPixels/9;//1/9width of the screen
        TableLayout layout=(TableLayout)findViewById(R.id.tableLayout);
        for(i=0;i<9;i++)
        {
            TableRow tableRow=new TableRow(this);
            for (j=0;j<9;j++){
                final TextView textView=new TextView(this);
                if(a[i][j]==0)textView.setText("");
                else textView.setText(""+a[i][j]);
                textView.setId(i * 10 + j);
                textView.setWidth(width);
                textView.setHeight(width);
                textView.setGravity(0x11);//center
                if(i==2||i==5)
                {
                    if(j==2||j==5)
                    {
                        textView.setBackgroundResource(R.drawable.add_down_right);
                    }else
                        textView.setBackgroundResource(R.drawable.add_down);
                }else
                {
                    if(j==2||j==5)
                        textView.setBackgroundResource(R.drawable.add_right);
                    else
                        textView.setBackgroundResource(R.drawable.add_border);
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setBackgroundColor(Color.parseColor("#B3E5FC"));
                        textView.setBackgroundResource(R.drawable.color_bacgr_border);
                        TextView pre=(TextView)findViewById(last_id);
                        if(pre!=textView){
                            int i=last_id/10,j=last_id%10;
                            if(i==2||i==5)
                            {
                                if(j==2||j==5)
                                {
                                    pre.setBackgroundResource(R.drawable.add_down_right);
                                }else
                                    pre.setBackgroundResource(R.drawable.add_down);
                            }else
                            {
                                if(j==2||j==5)
                                    pre.setBackgroundResource(R.drawable.add_right);
                                else
                                    pre.setBackgroundResource(R.drawable.add_border);
                            }
                        }
                        last_id=textView.getId();
                    }
                });
                tableRow.addView(textView);
            }
            layout.addView(tableRow);
        }
    }
}
