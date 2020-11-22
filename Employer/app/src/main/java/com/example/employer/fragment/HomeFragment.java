package com.example.employer.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.employer.AddJob;
import com.example.employer.R;
import com.example.employer.home.Adapterjob;
import com.example.employer.home.Modeljob;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


RecyclerView recyclerView;
List<Modeljob> modeljobList;
Adapterjob adapterjob;
RelativeLayout relativeLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        
        modeljobList=new ArrayList<>();
        recyclerView=view.findViewById(R.id.jobupdatesrecyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        relativeLayout=view.findViewById(R.id.relativeclick);
        relativeLayout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddJob.class));
        });
        getrecyclr();


        return view;
    }

    private void getrecyclr() {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("home").child("foryou");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modeljobList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    Modeljob modelFlipper=ds.getValue(Modeljob.class);
                    modeljobList.add(modelFlipper);
                    adapterjob =new Adapterjob(getActivity(),modeljobList);
                    recyclerView.setAdapter(adapterjob);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}