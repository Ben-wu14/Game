package com.example.ben.game;

/**
 * Created by ben on 2016/9/17.
 */
public class User {
    String name;
    String password;

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
        return (name==((User)o).name)&&(password==((User)o).password);
    }
}
