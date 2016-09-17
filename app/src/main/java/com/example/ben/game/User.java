package com.example.ben.game;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ben on 2016/9/17.
 */
public class User  {
    String name;
    String password;
    public User(String n,String p){
        name=n;
        password=p;
    }
    @Override
    public int hashCode() {
        char[]nc=name.toCharArray();
        char[]pc=password.toCharArray();
        int sum=0;
        int i;
        for (i=0;i<nc.length;i++)
            sum+=(int)nc[i];
        for (i=0;i<pc.length;i++)
            sum+=(int)pc[i];
        sum=sum/(nc.length+pc.length);
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        User obj=(User)o;
        return (name.equals(obj.name)&&password.equals(obj.password));
    }
}
