package com.example.employer.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.employer.R;
import com.example.employer.home.Adapterjob;
import com.example.employer.home.Modeljob;
import com.example.employer.search.Adaptersearch;
import com.example.employer.search.Modelsearch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

List<Modelsearch> modelsearches;
Adaptersearch adaptersearch;
RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);

        modelsearches=new ArrayList<>();
        recyclerView=view.findViewById(R.id.freelancerecycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        getrecyclerdata();

        return view;
    }

    private void getrecyclerdata() {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("searchemp").child("categoryemp");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelsearches.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    Modelsearch modelFlipper=ds.getValue(Modelsearch.class);
                    modelsearches.add(modelFlipper);
                    adaptersearch =new Adaptersearch(getActivity(),modelsearches);
                    recyclerView.setAdapter(adaptersearch);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}