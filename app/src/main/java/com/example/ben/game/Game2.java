package com.example.ben.game;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Game2 extends Activity {
    int last_id=0;//the id of the latest clicked view
    int a[][]=new int[9][9];//question array
    int userAnswer[][]=new int[9][9];//answer array
    int difficulty;//the difficulty of the game
    ArrayData data;//object that generate the array
    int changed=0;//identify whether the hint position is changed
    int froze=0;//used when the mistake occur and need to froze the screen

    int total_blank;

    String hintSwitch="off";
    ViewGroup vs;
    LinearLayout layout_hint;
    ImageView bulb;
    ImageView number_hint;
    int number_of_hint=5;
    LinearLayout whole;

    TextView timerTextView;
    long startTime =System.currentTimeMillis();;
    Handler timerHandler = new Handler();
    //runs without a timer by reposting this handler at the end of the runnable
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            if(last_id!=0&&changed!=0)layout_hint.setEnabled(true);
            else layout_hint.setEnabled(false);

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 500);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        vs=(ViewGroup)findViewById(R.id.layout_whole);
        bulb=(ImageView)findViewById(R.id.bulb);
        number_hint=(ImageView)findViewById(R.id.number_hint);
        layout_hint=(LinearLayout)findViewById(R.id.layout_hint);
        layout_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final Handler handler = new Handler();
                bulb.setBackgroundResource(R.drawable.light_bulb_on);
                hintSwitch="on";
                layout_hint.setEnabled(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bulb.setBackgroundResource(R.drawable.light_bulb_off);
                        hintSwitch="off";
                        layout_hint.setEnabled(true);
                    }
                }, 1000);*/
               /* if (hintSwitch=="off"){
                    bulb.setBackgroundResource(R.drawable.light_bulb_on);
                    hintSwitch="on";
                }else{
                    bulb.setBackgroundResource(R.drawable.light_bulb_off);
                    hintSwitch="off";
                }*/
                if (number_of_hint >= 1&&changed==1) {
                    TextView pre = (TextView) findViewById(last_id);
                    int answer=data.getAnser(last_id/10,last_id%10);
                    changed=0;
                    pre.setText(""+answer);
                    if(userAnswer[last_id/10][last_id%10]==0){
                        total_blank--;
                    }
                    userAnswer[last_id/10][last_id%10]=answer;
                    checkMistake(answer);
                    froze=0;
                    number_of_hint--;
                    switch (number_of_hint) {
                        case 4:
                            number_hint.setBackgroundResource(R.drawable.four);
                            break;
                        case 3:
                            number_hint.setBackgroundResource(R.drawable.three);
                            break;
                        case 2:
                            number_hint.setBackgroundResource(R.drawable.two);
                            break;
                        case 1:
                            number_hint.setBackgroundResource(R.drawable.one);
                            break;
                        case 0:
                            number_hint.setBackgroundResource(R.drawable.number);
                            break;
                    }
                }
            }
        });

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerHandler.postDelayed(timerRunnable, 0);


        int i,j;

        data=new ArrayData();//create a class to create new data
        data.Inital();//Initial the answer
        SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);//get the doc
        difficulty=sp.getInt("difficulty",1);//get the difficulty number from Activity Difficulty,if(nothing)then return 1
        data.SetDifficulty(difficulty);//Initial the question
        total_blank=9*(difficulty+1);
        a=data.getQuestion();//get the question
        for (i=0;i<9;i++){
            for(j=0;j<9;j++){
                userAnswer[i][j]=a[i][j];
            }
        }//copy the question to the user answer array
        //intial places >>>>>>>>>>>>>>>>>
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
                textView.setTextColor(Color.GRAY);
                textView.setTextSize(width / 5);
                if(a[i][j]==0)
                    textView.setTextColor(Color.parseColor("#000000"));
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
                        if(a[textView.getId()/10][textView.getId()%10]==0&&froze==0) {
                            textView.setBackgroundResource(R.drawable.color_bacgr_border);
                            TextView pre = (TextView) findViewById(last_id);
                            if (pre != textView) {
                                changed=1;
                                int i = last_id / 10, j = last_id % 10;
                                changed=1;
                                if (i == 2 || i == 5) {
                                    if (j == 2 || j == 5) {
                                        pre.setBackgroundResource(R.drawable.add_down_right);
                                    } else
                                        pre.setBackgroundResource(R.drawable.add_down);
                                } else {
                                    if (j == 2 || j == 5)
                                        pre.setBackgroundResource(R.drawable.add_right);
                                    else
                                        pre.setBackgroundResource(R.drawable.add_border);
                                }
                            }else changed=0;
                            last_id = textView.getId();
                        }
                    }
                });
                tableRow.addView(textView);
            }
            layout.addView(tableRow);
        }
    }
    public void buttonP1(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("1")))){
            t.setText("1");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=1;
            checkMistake(-1);
            froze=0;
            checkMistake(1);
        }

    }
    public void buttonP2(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("2")))){
            t.setText("2");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=2;
            checkMistake(-1);
            froze=0;
            checkMistake(2);
        };
    }
    public void buttonP3(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("3")))){
            t.setText("3");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=3;
            checkMistake(-1);
            froze=0;
            checkMistake(3);
        }
    }
    public void buttonP4(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("4")))){
            t.setText("4");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=4;
            checkMistake(-1);
            froze=0;
            checkMistake(4);
        }
    }
    public void buttonP5(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("5")))){
            t.setText("5");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=5;
            checkMistake(-1);
            froze=0;
            checkMistake(5);
        }
    }
    public void buttonP6(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("6")))){
            t.setText("6");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=6;
            checkMistake(-1);
            froze=0;
            checkMistake(6);
        }
    }
    public void buttonP7(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("7")))){
            t.setText("7");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=7;
            checkMistake(-1);
            froze=0;
            checkMistake(7);
        }
    }
    public void buttonP8(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("8")))){
            t.setText("8");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=8;
            checkMistake(-1);
            froze=0;
            checkMistake(8);
        }
    }
    public void buttonP9(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0&&(!(t.getText().toString().equals("9")))){
            t.setText("9");
            if(userAnswer[i][j]==0){
                total_blank--;
            }
            userAnswer[i][j]=9;
            checkMistake(-1);
            froze=0;
            checkMistake(9);
        }
    }
    public void buttonPc(View view){
        TextView t=(TextView)findViewById(last_id);
        int i=last_id/10,j=last_id%10;
        if(a[i][j]==0){
            t.setText("");
            total_blank++;
            userAnswer[i][j]=0;
            froze=0;
            checkMistake(-1);
        }
    }
    public void checkMistake(int number){
        int i=last_id/10;
        int j=last_id%10;
        int k;
        int w;
        for(k=0;k<9;k++){
            TextView test=(TextView)findViewById(i*10+k);
            if(userAnswer[i][k]==number&&k!=j){
                test.setTextColor(Color.RED);
                froze=1;
            }else if(a[i][k]==0) test.setTextColor(Color.BLACK);
            else test.setTextColor(Color.GRAY);
        }
        for(k=0;k<9;k++){
            TextView test=(TextView)findViewById(k*10+j);
            if(userAnswer[k][j]==number&&k!=i){
                test.setTextColor(Color.RED);
                froze=1;
            }else  if(a[k][j]==0) test.setTextColor(Color.BLACK);
            else test.setTextColor(Color.GRAY);
        }
        for (k=j/3*3;k<j/3*3+3;k++){
            for (w=i/3*3;w<i/3*3+3;w++){
                TextView test=(TextView)findViewById(w*10+k);
                if(userAnswer[w][k]==number&&(k!=j||w!=i)){
                    test.setTextColor(Color.RED);
                    froze=1;
                }else  if(a[w][k]==0) test.setTextColor(Color.BLACK);
                else test.setTextColor(Color.GRAY);
            }
        }
        TextView test=(TextView)findViewById(last_id);
        if(froze==1){
            test.setTextColor(Color.RED);
        }else test.setTextColor(Color.BLACK);
    }
    public boolean complete(){
        return froze==0&&total_blank==0;
    }
}
