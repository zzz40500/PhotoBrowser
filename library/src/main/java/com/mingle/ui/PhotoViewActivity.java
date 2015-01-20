package com.mingle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.mingle.library.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class PhotoViewActivity extends ActionBarActivity {

    private ViewPager vp;
    private TextView indexTV;
    private TextView indexTitleTV;


    /**
     * url 地址列表
     */
    public static String  URL_LIST="url_list";
    /**
     * url 的标题.
     */
    public static String  URL_TITLES="url_titles";


    public  static  String  INDEX="index";

    private List<String> imageUrls;
    private List<String> imageTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);


        vp = (ViewPager) findViewById(R.id.viewPager);
        indexTV= (TextView) findViewById(R.id.indexTV);
        indexTitleTV= (TextView) findViewById(R.id.indexTitleTV);

        Intent intent=getIntent();
        String listStr=intent.getStringExtra(URL_LIST);
        if(listStr==null){
            return ;
        }else{

            try {
                JSONArray jsonArray=new JSONArray(listStr);

                imageUrls=new ArrayList<String>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    imageUrls.add(jsonArray.getString(i));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        String titlesStr=intent.getStringExtra(URL_TITLES);


        if(titlesStr==null){
            indexTitleTV.setVisibility(View.GONE);
        }else{


            try {
                JSONArray jsonArray=new JSONArray(titlesStr);

                imageTitles=new ArrayList<String>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    imageTitles.add(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        setTitle("图片查看");

        getSupportActionBar().hide();

        List<Fragment> list=new ArrayList<>();
        for(int i=0;i<imageUrls.size();i++) {
            list.add(new PhotoViewFragment(imageUrls.get(i)));
        }

        ViewpagerAdapter vpAdapter=new ViewpagerAdapter(getSupportFragmentManager(),list,null);



        int index=getIntent().getIntExtra(INDEX,0);
        vp.setAdapter(vpAdapter);

        vp.setCurrentItem(index);
        updateStatus(index);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateStatus(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    public void updateStatus(int position){


        this.indexTV.setText(position+1+"/"+imageUrls.size());

        if(imageTitles!=null){

            this.indexTitleTV.setText(imageTitles.get(position));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
