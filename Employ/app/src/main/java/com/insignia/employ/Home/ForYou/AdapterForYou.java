package com.insignia.employ.Home.ForYou;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.insignia.employ.Home.REcommendations.ModelRecommendation;
import com.insignia.employ.R;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class AdapterForYou extends RecyclerView.Adapter<AdapterForYou.ViewHolder> {
    Context context;
    List<ModelForYou> modelRecommendationList;
    TextToSpeech textToSpeech;

    public AdapterForYou(Context context, List<ModelForYou> modelRecommendationList, TextToSpeech textToSpeech) {
        this.context = context;
        this.modelRecommendationList = modelRecommendationList;
        this.textToSpeech = textToSpeech;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_foryou_container,parent,false);
        return new AdapterForYou.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String companylogo =modelRecommendationList.get(position).getCompanylogo();
        String companyname=modelRecommendationList.get(position).getCompanyname();
        String work=modelRecommendationList.get(position).getWork();
        String fee=modelRecommendationList.get(position).getFee();
        String location=modelRecommendationList.get(position).getLocation();
        String kenburnimg=modelRecommendationList.get(position).getKenburnlogo();
        String transdata="your preferred job of +"+work+"at"+companyname+"with estimated amount of"+fee+" at location"+location+"is available to take up";
        String trans2="आपके"+work+"की पसंदीदा नौकरी"+companyname+"पर"+fee+"की अनुमानित राशि के साथ, स्थान"+location+ "लेने के लिए उपलब्ध है";
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
        holder.trans.setOnClickListener(v -> {


         textToSpeech.speak(trans2, TextToSpeech.QUEUE_FLUSH, null);


        });

    }

    @Override
    public int getItemCount() {
        return modelRecommendationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView complogo,trans;
        ImageView kenburnlogo;
        TextView work,fee,location,compname;
        Button applynow,jobinfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            complogo=itemView.findViewById(R.id.foryouimage);
            kenburnlogo=itemView.findViewById(R.id.kernburnforyou);
            compname=itemView.findViewById(R.id.foryoucompanyname);
            work =itemView.findViewById(R.id.foryouprofession);
            fee=itemView.findViewById(R.id.foryoucomission);
            location=itemView.findViewById(R.id.foryoulocation);
            applynow=itemView.findViewById(R.id.foryouapplynow);
            jobinfo=itemView.findViewById(R.id.jobinfo);
            trans=itemView.findViewById(R.id.translatetext);

        }
    }

    }

