package com.example.ben.game;

import java.io.Serializable;

/**
 * Created by ben on 2016/10/3.
 */
public class ArrayFile implements Serializable{
    private ArrayData data;
    private int[][] userAnser;
    private int difficulty;
    private int total_blank;
    private int min;
    private int sec;
    public ArrayFile(ArrayData data,int[][]userAnser,int difficulty,int total_blank,int min,int sec){
        this.data=data;
        this.userAnser=userAnser;
        this.difficulty=difficulty;
        this.total_blank=total_blank;
        this.min=min;
        this.sec=sec;
    }
    public ArrayData getData(){
        return data;
    }
    public int[][] getUserAnser(){
        return userAnser;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public int getTotal_blank(){
        return total_blank;
    }
    public int getMin(){
        return min;
    }
    public int getSec(){
        return sec;
    }
}
