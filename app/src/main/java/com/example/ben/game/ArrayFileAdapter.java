package com.example.ben.game;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ben on 2016/10/3.
 */
public class ArrayFileAdapter extends ArrayAdapter<ArrayFile> {
    public ArrayFileAdapter(Activity context,ArrayList<ArrayFile> arrayls){
        super(context,0,arrayls);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem =convertView;
        if(listItem==null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        ArrayFile arrayFile=getItem(position);
        TextView time=(TextView)listItem.findViewById(R.id.Time);
        time.setText(String.format("%d:%02d", arrayFile.getMin(), arrayFile.getSec()));
        TextView diff=(TextView)listItem.findViewById(R.id.text_difficulty);
        diff.setText(""+arrayFile.getDifficulty());
        TextView com=(TextView)listItem.findViewById(R.id.complete);
        com.setText(""+arrayFile.getTotal_blank());
        return listItem;
    }
}
