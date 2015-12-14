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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    Film buffer;
    private ArrayList<Film> list=new ArrayList<Film>();
    public String title,title2;
    LinearLayout linearLayout;
    ViewGroup.LayoutParams linLayoutParams;
    int flag=0;
    TextView tv;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linLayoutParams =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(linearLayout, linLayoutParams);
        ViewGroup.LayoutParams lpView =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
         tv= new TextView(this);
        tv.setText("Wait");
        button =new Button(this);
        button.setText("отобразить список");
        button.setClickable(true);

        button.setOnClickListener(this);
        linearLayout.addView(tv);
        linearLayout.addView(button);
        MyTask mt = new MyTask();
        mt.execute();
        Log.d("Fak","Fuck");

        Log.d("Fak","Fuck");


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

    @Override
    public void onClick(View v) {
       // LinearLayout linearLayout1= new LinearLayout(this);
       // linearLayout1.setOrientation(LinearLayout.VERTICAL);
       // ViewGroup.LayoutParams linLayoutParams1 =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //setContentView(linearLayout1, linLayoutParams1);
        //ViewGroup.LayoutParams lpView =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //TextView tv1= new TextView(this);
        //tv1.setText(buffer.getName()+"\n"+buffer.getJanere()+"\n"+"Премьера: "+buffer.getPremiere()+"\n"+"Описание: "+buffer.getText()+"\n");


            for(int i=0;i<list.size();i++)
            {Log.d("Fak","Fuck");
                TextView text = new TextView(this);text.setText(list.get(i).getName() + "\n");
                text.setClickable(true);
                text.setOnClickListener(new MyClick(linearLayout,linLayoutParams,list.get(i),this));


                linearLayout.addView(text);





            }
        list.removeAll(list);
        flag=0;
       // tv1.setClickable(true);
        //tv1.setOnClickListener(this);
   //     linearLayout1.addView(tv1);
    }
    // public void ClickMe(View v)
   // {
   //     textView.setText("Wait");
   //     MyTask mt = new MyTask();


    //    mt.execute();


   // }
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
                Elements hrefs= doc.select("a.event__link");
                Elements spans=doc.select("h2.event__name") ;
                title="Выберите фильм";
                title2="";
                for (int i=0; i<hrefs.size()-3;i++)
                {


                        Film flms=new Film();
                        Element element = hrefs.get(i);
                        Element name = spans.get(i);
                        flms.setName(name.text());
                        Log.d("Text", flms.getName());
                        String text;

                        text = element.attr("href") + " ";
                        String href = new String();
                        for (int j = 8; j < 32; j++) {
                            href = href + text.charAt(j);

                        }
                        title2+=href+"\n";
                        Log.d("Text",href);
                       title2= "https://afisha.yandex.by/events?city=minsk&source=menu&tag=cinema&preset=today&eventId="+href+"&schedule-preset=today";
                        Document doc2=null;
                        try {

                            doc2 = Jsoup.connect(title2).get();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Element element2=doc2.select("p").first();
                        flms.setText(element2.text());
                        Log.d("Text", flms.getText());
                        // element2=doc2.select("h1.event-heading__title").first();
                       // flms.setName(element2.text());
                       // Log.d("Text", flms.getName());
                    Log.d("Fak","Fuck1");

                        flms.setJanere(element.text());
                    Log.d("text", element.text());
                        list.add(flms);

                    if(i==9)
                        flag=1;



                }
               /* String href= new String();
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
                title =title+"Прьемера "+element2.text();*/

            }

            else
                title = "Отсутствует интернет проверьте подклучено ли соединение";
            //Тут пишем основной код

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tv.setText(title);

            //Тут выводим итоговые данные
        }
    }

}
