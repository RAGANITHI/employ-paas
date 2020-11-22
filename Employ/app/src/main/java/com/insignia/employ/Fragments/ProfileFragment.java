package com.insignia.employ.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.insignia.employ.Home.REcommendations.AdapterRecommendation;
import com.insignia.employ.Home.REcommendations.ModelRecommendation;
import com.insignia.employ.Profile.AdapterProfile;
import com.insignia.employ.Profile.ModelProfile;
import com.insignia.employ.R;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
List<ModelProfile> modelProfiles;
AdapterProfile adapterProfile;
RecyclerView recyclerViewjobhistory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        modelProfiles=new ArrayList<>();
        recyclerViewjobhistory=view.findViewById(R.id.recyclerjobhistory);
        recyclerViewjobhistory.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewjobhistory.setLayoutManager(linearLayoutManager);

        getRecycleruser();

        return view;
    }

    private void getRecycleruser() {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("home").child("recommendation");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelProfiles.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    ModelProfile modelFlipper=ds.getValue(ModelProfile.class);
                    modelProfiles.add(modelFlipper);
                    adapterProfile =new AdapterProfile(getActivity(),modelProfiles);
                    recyclerViewjobhistory.setAdapter(adapterProfile);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}