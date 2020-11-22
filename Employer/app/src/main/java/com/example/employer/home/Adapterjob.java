package com.example.employer.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employer.R;
import com.squareup.picasso.Picasso;

import java.util.ConcurrentModificationException;
import java.util.List;

public class Adapterjob extends RecyclerView.Adapter<Adapterjob.MyHolder> {

    Context context;
    List<Modeljob> modeljobList;

    public Adapterjob(Context context, List<Modeljob> modeljobList) {
        this.context = context;
        this.modeljobList = modeljobList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.jobhomelayout,parent,false);
        return new Adapterjob.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
String name=modeljobList.get(position).getCompanyname();
    String logo=modeljobList.get(position).getCompanylogo();
    String kenb=modeljobList.get(position).getKenburnlogo();
    String wages=modeljobList.get(position).getFee();
    String location=modeljobList.get(position).getLocation();
    String work=modeljobList.get(position).getWork();

    holder.name.setText(name);
    holder.work.setText(work);
    holder.fee.setText(wages);
    try {
        Picasso.get().load(logo).placeholder(R.drawable.employ).into(holder.logoimg);
        Picasso.get().load(kenb).placeholder(R.drawable.employ).into(holder.kenburnimg);
    }
    catch (Exception e){

    }
    }

    @Override
    public int getItemCount() {
        return modeljobList.size();
    }

    public  class MyHolder extends RecyclerView.ViewHolder{
        ImageView logoimg,kenburnimg;
        TextView work,fee,name;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            logoimg=itemView.findViewById(R.id.img);
            kenburnimg=itemView.findViewById(R.id.mainimg);
            work=itemView.findViewById(R.id.worktxt);
            fee=itemView.findViewById(R.id.feetext);
            name=itemView.findViewById(R.id.txtcompany);

        }
    }
}
