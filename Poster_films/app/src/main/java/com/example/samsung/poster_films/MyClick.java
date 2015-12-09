package com.example.samsung.poster_films;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Saxib on 29.11.2015.
 */
public class MyClick implements View.OnClickListener {


    int flag=0;
    LinearLayout linearLayout;
    ViewGroup.LayoutParams linLayoutParams;
    MainActivity mainActivity;
Film buffer;
    MyClick(LinearLayout linearLayout1,ViewGroup.LayoutParams linLayoutParams1 ,Film f,MainActivity m)
    {
        mainActivity=m;
        linearLayout=linearLayout1;
        linLayoutParams=linLayoutParams1;
        buffer=f;
    }
    @Override
    public void onClick(View v) {
        if(flag==0) {
            LinearLayout linearLayout1 = new LinearLayout(mainActivity);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams linLayoutParams1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mainActivity.setContentView(linearLayout1, linLayoutParams1);
            ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            TextView tv1 = new TextView(mainActivity);
            tv1.setText(buffer.getName() + "\n\n" + buffer.getJanere() + "\n\n"  + "\n\n" + "Описание:   " + buffer.getText() + "\n");
            tv1.setClickable(true);
            tv1.setOnClickListener(this);
            linearLayout1.addView(tv1);
            flag=1;
        }
        else
        {
            mainActivity.setContentView(linearLayout);
            flag=0;
        }



    }
}
