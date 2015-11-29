package com.example.samsung.poster_films;

import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Samsung on 24.11.2015.
 */
public class Film {
    public int id;
    private String Name;
    private String Text;
    private String Premiere;
    private String Janere;

    Film()
    {
        id=0;
        Name=new String();
        Text=new String();
        Premiere=new String();
        Janere=new String();
     Log.d("Create object", "Created!!!");
    };




    String getName()
    {
        return Name;
    }

    String getText()
    {
        return Text;

    }
    String getPremiere()
    {
        return Premiere;
    }
  String  getJanere(){return Janere;}


    void setName(String s)
    {
        Name=s;
    }

    void setText(String s)
    {
        Text=s;
    }
    void setPremiere(String s)
    {
        Premiere=s;
    }
    void setJanere(String s)
    {
        Janere=s;
    }



}
