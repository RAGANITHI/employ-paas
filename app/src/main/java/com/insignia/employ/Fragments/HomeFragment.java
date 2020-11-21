package com.insignia.employ.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.insignia.employ.Home.Flipper.AdapterFormFlipper;
import com.insignia.employ.Home.Flipper.ModelFormFlipper;
import com.insignia.employ.Home.ForYou.AdapterForYou;
import com.insignia.employ.Home.ForYou.ModelForYou;
import com.insignia.employ.Home.REcommendations.AdapterRecommendation;
import com.insignia.employ.Home.REcommendations.ModelRecommendation;
import com.insignia.employ.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {

 private ViewPager2 viewPager2;
    List<ModelFormFlipper> flipperformlist;
    AdapterFormFlipper adapterStudentFlipper;
    private Handler sliderhandler=new Handler();


    List<ModelRecommendation> modelRecommendationList;
    AdapterRecommendation adapterRecommendation;
    RecyclerView recyclerViewrecomendation;



    List<ModelForYou> modelForYous;
    AdapterForYou adapterForYou;
    RecyclerView recyclerViewforyou;
    TextToSpeech textToSpeech;
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2=view.findViewById(R.id.viewpagerhome);
        flipperformlist=new ArrayList<>();
        modelRecommendationList=new ArrayList<>();
        recyclerViewrecomendation=view.findViewById(R.id.recommendationrecycler);
        recyclerViewrecomendation.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewrecomendation.setLayoutManager(linearLayoutManager);

        modelForYous=new ArrayList<>();
        recyclerViewforyou=view.findViewById(R.id.foryourecycler);
        recyclerViewforyou.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
        recyclerViewforyou.setLayoutManager(linearLayoutManager1);
        textToSpeech=new TextToSpeech(getActivity(), status -> {
            if (status==TextToSpeech.SUCCESS){
                textToSpeech.setLanguage(Locale.forLanguageTag("hi"));
            }
        });
        
        getRecommendation();

        getUser();

        getForYou();

        return view;
    }

    private void getForYou() {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("home").child("foryou");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelForYous.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    ModelForYou modelFlipper=ds.getValue(ModelForYou.class);
                    modelForYous.add(modelFlipper);
                    adapterForYou =new AdapterForYou(getActivity(),modelForYous,textToSpeech);
                    recyclerViewforyou.setAdapter(adapterForYou);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void getRecommendation() {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("home").child("recommendation");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelRecommendationList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    ModelRecommendation modelFlipper=ds.getValue(ModelRecommendation.class);
                    modelRecommendationList.add(modelFlipper);
                    adapterRecommendation =new AdapterRecommendation(getActivity(),modelRecommendationList);
                    recyclerViewrecomendation.setAdapter(adapterRecommendation);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getUser() {

        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("home").child("flipper");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                flipperformlist.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    ModelFormFlipper modelFlipper=ds.getValue(ModelFormFlipper.class);
                    flipperformlist.add(modelFlipper);
                    adapterStudentFlipper =new AdapterFormFlipper(getActivity(),flipperformlist,viewPager2);
                    viewPager2.setAdapter(adapterStudentFlipper);
                    viewPager2.setClipToPadding(false);
                    viewPager2.setClipChildren(false);
                    viewPager2.setOffscreenPageLimit(3);
                    viewPager2.setPadding(0,0,0,0);
                    viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                    CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
                    compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                    compositePageTransformer.addTransformer((page, position) -> {
                        float r=1-Math.abs(position);
                        page.setScaleY(0.85f+r*0.15f);
                    });
                    viewPager2.setPageTransformer(compositePageTransformer);
                    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            sliderhandler.removeCallbacks(sliderrunable);
                            sliderhandler.postDelayed(sliderrunable,10000);
                        }
                    });

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
    private Runnable sliderrunable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {

        super.onPause();
    }
}