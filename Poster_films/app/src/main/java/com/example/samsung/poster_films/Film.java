package com.example.samsung.poster_films;

import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Samsung on 24.11.2015.
 */
public class Film {
    private String Url;
    private String Name;
    private String Text;
    private String Premiere;
    private String Janere;

    Film()
    {

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
