package com.insignia.employ.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.insignia.employ.Home.ForYou.AdapterForYou;
import com.insignia.employ.Home.ForYou.ModelForYou;
import com.insignia.employ.R;
import com.insignia.employ.Search.AdapterSearch;
import com.insignia.employ.Search.ModelSearch;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    List<ModelSearch> modelSearches;
    AdapterSearch adapterSearch;
    RecyclerView recyclerViewsearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        recyclerViewsearch=view.findViewById(R.id.searchrecycler);
        modelSearches=new ArrayList<>();
        recyclerViewsearch.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewsearch.setLayoutManager(gridLayoutManager);

        getrecyclerUser();




        return view;

    }

    private void getrecyclerUser() {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("search").child("category");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelSearches.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    ModelSearch modelFlipper=ds.getValue(ModelSearch.class);
                    modelSearches.add(modelFlipper);
                    adapterSearch =new AdapterSearch(getActivity(),modelSearches);
                    recyclerViewsearch.setAdapter(adapterSearch);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}