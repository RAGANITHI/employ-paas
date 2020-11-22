package com.insignia.employ.Home.REcommendations;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.insignia.employ.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecommendation extends RecyclerView.Adapter<AdapterRecommendation.ViewHolder> {
    Context context;
    List<ModelRecommendation> modelRecommendationList;

    public AdapterRecommendation(Context context, List<ModelRecommendation> modelRecommendationList) {
        this.context = context;
        this.modelRecommendationList = modelRecommendationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_recommended_container,parent,false);
        return new AdapterRecommendation.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String companylogo =modelRecommendationList.get(position).getCompanylogo();
        String companyname=modelRecommendationList.get(position).getCompanyname();
        String work=modelRecommendationList.get(position).getWork();
        String fee=modelRecommendationList.get(position).getFee();
        String location=modelRecommendationList.get(position).getLocation();
        String kenburnimg=modelRecommendationList.get(position).getKenburnlogo();

        holder.location.setText(location);
        holder.fee.setText(fee);
        holder.work.setText(work);
        holder.compname.setText(companyname);

        try {
            Picasso.get().load(companylogo).placeholder(R.drawable.employ).into(holder.complogo);
            Picasso.get().load(kenburnimg).placeholder(R.drawable.employ).into(holder.kenburnlogo);
        }
        catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return modelRecommendationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView complogo;
        KenBurnsView kenburnlogo;
        TextView work,fee,location,compname;
        Button applynow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            complogo=itemView.findViewById(R.id.recommendationimage);
            kenburnlogo=itemView.findViewById(R.id.kernburnrecommendation);
            compname=itemView.findViewById(R.id.companyname);
            work =itemView.findViewById(R.id.profession);
            fee=itemView.findViewById(R.id.comission);
            location=itemView.findViewById(R.id.location);
            applynow=itemView.findViewById(R.id.applynow);

        }
    }
}
