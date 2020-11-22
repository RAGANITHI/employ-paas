package com.example.employer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.employer.fragment.HomeFragment;
import com.example.employer.fragment.ProfileFragment;
import com.example.employer.fragment.SearchFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
AnimatedBottomBar animatedBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animatedBottomBar=findViewById(R.id.bottombar);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {

                Fragment selected_fragment=null;
                if (tab1.getTitle().equals("Home")){

                    selected_fragment=new HomeFragment();

                }
                else if(tab1.getTitle().equals("Search")){

                    selected_fragment=new SearchFragment();

                }

                else {
                    selected_fragment=new ProfileFragment();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selected_fragment).commit();

            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });
    }
}