package com.example.employer.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.employer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Searchsubcategory extends AppCompatActivity {
RecyclerView recyclerView;
List<Modelsubsearch> modelsubsearches;
AdapterSubcategorysearch adapterSubcategorysearch;
String treename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchsubcategory);
        Intent intent=getIntent();
        treename=intent.getStringExtra("treename");
        modelsubsearches=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclersubsearch);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Searchsubcategory.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        getrecycler();
    }

    private void getrecycler() {

        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("searchemp").child("subcategoryemp").child(treename);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelsubsearches.clear();
                for (DataSnapshot ds: snapshot.getChildren()){

                    Modelsubsearch modelFlipper=ds.getValue(Modelsubsearch.class);
                    modelsubsearches.add(modelFlipper);
                    adapterSubcategorysearch =new AdapterSubcategorysearch(Searchsubcategory.this,modelsubsearches);
                    recyclerView.setAdapter(adapterSubcategorysearch);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}