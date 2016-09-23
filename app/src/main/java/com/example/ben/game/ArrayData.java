package com.example.ben.game;

import java.util.Random;

/**
 * Created by ben on 2016/9/23.
 */
public class ArrayData {
    private int difficu;
    private int[][] a;
    private int[][] b;
    private int[] col={0,1,2,3,4,5,6,7,8,9};
    private int[] cur={0,1,2,3,4,5,6,7,8,9};
    private int[] visit = {0,0,0,0,0,0,0,0,0,0};
    private Random ra = new Random();
    public void Inital(){
        a=new int[10][10];//a数组用于初始化答案
        b=new int[10][10];//b数组用于显示
        for (int i=1;i<4;i++)
            for (int j=1;j<4;j++)
            {
                int ranum = ra.nextInt(9)+1;
                while (visit[ranum]==1){
                    ranum++;
                    if (ranum==10) ranum=1;
                }
                a[i][j]=ranum;
                visit[ranum]=1;
            }
        for (int i=1;i<4;i++)
            for (int j=4;j<7;j++)
            {
                if (i<3) a[i][j]=a[i+1][j-3];
                else a[i][j]=a[1][j-3];
            }
        for (int i=1;i<4;i++)
            for (int j=7;j<10;j++)
            {
                if (i<3) a[i][j]=a[i+1][j-3];
                else a[i][j]=a[1][j-3];
            }
        for (int i=4;i<7;i++)
            for (int j=1;j<4;j++)
            {
                if (j>1) a[i][j]=a[i-3][j-1];
                else a[i][j]=a[i-3][3];
            }
        for (int i=7;i<10;i++)
            for (int j=1;j<4;j++)
            {
                if (j>1) a[i][j]=a[i-3][j-1];
                else a[i][j]=a[i-3][3];
            }


        for (int i=4;i<7;i++)
            for (int j=4;j<7;j++)
            {
                if (i<6) a[i][j]=a[i+1][j-3];
                else a[i][j]=a[4][j-3];
            }
        for (int i=4;i<7;i++)
            for (int j=7;j<10;j++)
            {
                if (i<6) a[i][j]=a[i+1][j-3];
                else a[i][j]=a[4][j-3];
            }


        for (int i=7;i<10;i++)
            for (int j=4;j<7;j++)
            {
                if (i<9) a[i][j]=a[i+1][j-3];
                else a[i][j]=a[7][j-3];
            }
        for (int i=7;i<10;i++)
            for (int j=7;j<10;j++)
            {
                if (i<9) a[i][j]=a[i+1][j-3];
                else a[i][j]=a[7][j-3];
            }
        for (int k=0;k<10;k++){
            int ranum1 = ra.nextInt(9)+1;
            int ranum2 = ra.nextInt(9)+1;
            if (ranum1 == ranum2) {k--;continue ;}
            if ((ranum1<4&&ranum2<4)||(ranum1<7&&ranum1>3&&ranum2<7&&ranum2>3)||(ranum1<10&&ranum1>6&&ranum2<10&&ranum2>6))
            {
                int temp=col[ranum1];
                col[ranum1]=col[ranum2];
                col[ranum2]=temp;
            }
            else{
                k--;
            }

        }
        for (int k=0;k<10;k++){
            int ranum1 = ra.nextInt(9)+1;
            int ranum2 = ra.nextInt(9)+1;
            if (ranum1 == ranum2) {k--;continue ;}
            if ((ranum1<4&&ranum2<4)||(ranum1<7&&ranum1>3&&ranum2<7&&ranum2>3)||(ranum1<10&&ranum1>6&&ranum2<10&&ranum2>6))
            {
                int temp=cur[ranum1];
                cur[ranum1]=cur[ranum2];
                cur[ranum2]=temp;
            }
            else{
                k--;
            }
        }
    }
    public void SetDifficulty(int difficu){
        this.difficu=difficu;
        for (int i=1;i<10;i++)
            for (int j=1;j<10;j++)
                b[i][j]=a[cur[i]][col[j]];
        if (difficu<=3) {//每行随机去掉3个
            for (int i=1;i<10;i++){
                //System.out.print("Helloww\n");
                int cnt=3+difficu;
                for (int j=1;j<10;j++)
                    while (cnt>0){
                        //System.out.print("Helloww\n");
                        int ran = ra.nextInt(9)+1;
                        if (b[i][ran]!=0) {
                            b[i][ran]=0;cnt--;
                            System.out.print("ggw\n");
                        }
                    }
            }
        }
    }
    public int getAnser(int i,int j){
            return a[i-1][j-1];
        }
    public int[][] getQuestion(){
        int i,j;
        int [][] s=new int[9][9];
        for (i=1;i<10;i++){
            for (j=1;j<10;j++){
                s[i-1][j-1]=b[i][j];
            }
        }
        return s;
    }
}
