package com.mingle.photobrowser;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mingle.ui.PhotoViewActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PhotoViewActivity.class);
                JSONArray urls=new JSONArray();
                urls.put("http://tp.cdn.di5.net:8880/2013/mxy/0322/1/1.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/9.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/12.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/2.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/5.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/7.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/8.jpg.680.510.jpg");
                JSONArray titles=new JSONArray();
                titles.put("上位1");
                titles.put("上位2");
                titles.put("上位3");
                titles.put("上位4");
                titles.put("上位5");
                titles.put("上位6");
                titles.put("上位7");


                intent.putExtra(PhotoViewActivity.URL_LIST, urls.toString());
                intent.putExtra(PhotoViewActivity.URL_TITLES, titles.toString());
                intent.putExtra(PhotoViewActivity.INDEX,2);

                startActivity(intent);
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
}
