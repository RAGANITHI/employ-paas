package com.insignia.employ.OnBoardScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


import com.google.android.material.tabs.TabLayout;
import com.insignia.employ.LoginandRegister.Login;
import com.insignia.employ.R;

import java.util.ArrayList;
import java.util.List;

public class onboardscreen extends AppCompatActivity {
    private ViewPager screenpager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabindicator;
    Button nextbutton,getstartbut;
    int position;
    Animation btnanime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboardscreen);
        screenpager=findViewById(R.id.onboardviewpager);
        final List<screenitem> mlist=new ArrayList<>();
        mlist.add(new screenitem("Home","Hello Kanyakumariians! \n" +
                "Here’s your one stop Junction to Services, Information & Entertainment! Ever wondered for a new-gen app specific to your town and desperately wanted to be a part of it ? \n" +
                "Come let’s get going!\n",R.drawable.employ));
        mlist.add(new screenitem("Explore","Search all your needs, from daily information crisis, to sourcing your services like electricians, painters and many more; from inspiring web content relevant to  Kanyakumari, to talent rooms & student programs all in just one application. Also, don’t miss out on exploring the feed for loads of entertainment!",R.drawable.employ));
        mlist.add(new screenitem("Shop","Want to grab the specials of the town in just a click ? Find organic stores, to florists and many more; we have it all covered!  ",R.drawable.employ));
        mlist.add(new screenitem("Report","Start before you’re ready’ said someone because we are not ready always; and so did we start. And here’s your part of feedback and suggestions that you want to give! Bring on all your senses together and give us your feedback here!",R.drawable.employ));
        mlist.add(new screenitem("People","Search for services/ Professionals you need!\n",R.drawable.employ));
        introViewPagerAdapter=new IntroViewPagerAdapter(this,mlist);
        screenpager.setAdapter(introViewPagerAdapter);
        tabindicator=findViewById(R.id.onboardtablayout);
        tabindicator.setupWithViewPager(screenpager);

        btnanime= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        nextbutton=findViewById(R.id.nextbut);
        getstartbut=findViewById(R.id.getstart);
        nextbutton.setOnClickListener(view -> {
            position=screenpager.getCurrentItem();
            if(position<mlist.size()){
                position++;
                screenpager.setCurrentItem(position);
            }
            if(position == mlist.size()-1){
                loadLastScreen();
            }

        });
        tabindicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==mlist.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void loadLastScreen() {
        getstartbut.setVisibility(View.VISIBLE);
        nextbutton.setVisibility(View.INVISIBLE);
        tabindicator.setVisibility(View.INVISIBLE);
        getstartbut.setAnimation(btnanime);
        getstartbut.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });
    }

    public void Skip(View view) {
        startActivity(new Intent(onboardscreen.this,Login.class));
        finish();
    }
}