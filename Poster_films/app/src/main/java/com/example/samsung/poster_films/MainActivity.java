package com.example.samsung.poster_films;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    public String title,title2;
   // public String href=new String();
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =(Button)findViewById( R.id.button_1);
        textView=(TextView)findViewById(R.id.TextView_1);
        textView2=(TextView)findViewById(R.id.TextView_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void ClickMe(View v)
    {
        textView.setText("Wait");
        MyTask mt = new MyTask();


        mt.execute();


    }
    class MyTask extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... params) {

            Document doc = null;//Здесь хранится будет разобранный html документ
            try {
                //Считываем заглавную страницу http://harrix.org
                doc = Jsoup.connect("https://afisha.yandex.by/events?city=minsk&source=menu&tag=cinema&preset=today").get();
            } catch (IOException e) {
                //Если не получилось считать
                e.printStackTrace();
            }

            //Если всё считалось, что вытаскиваем из считанного html документа заголовок
            if (doc!=null) {
                Elements elemnts = doc.select("div.events-list__list");
                title = elemnts.html();
                doc = Jsoup.parse(title);
                Element element= doc.select("a").first();
                title= element.attr("href");
                String href= new String();
                for(int i=8;i<32;i++)
                {
                    href=href+title.charAt(i);

               }

title2="https://afisha.yandex.by/events?city=minsk&source=menu&tag=cinema&preset=today&eventId="+href+"&schedule-preset=today";
               // https://afisha.yandex.by/events?city=minsk&source=menu&tag=cinema&preset=today&eventId=564527987c65094b33cfd4d4
                Document doc2=null;
                try {

                  doc2 = Jsoup.connect(title2).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Element element2=doc2.select("p").first();
                title =element2.text();
                element2=doc2.select("div.event-attributes__category-value").first();
                title =title+"Прьемера "+element2.text();

            }

            else
                title = "Ошибка";
            //Тут пишем основной код

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            textView.setText(title);
           textView2.setText(title2);
            //Тут выводим итоговые данные
        }
    }

}
