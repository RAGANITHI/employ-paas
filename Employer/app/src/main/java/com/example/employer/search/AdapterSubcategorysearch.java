package com.example.employer.search;

import android.content.Context;
import android.telephony.SignalStrength;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSubcategorysearch extends RecyclerView.Adapter<AdapterSubcategorysearch.MyViewholder> {
Context context;
List<Modelsubsearch> modelsubsearches;

    public AdapterSubcategorysearch(Context context, List<Modelsubsearch> modelsubsearches) {
        this.context = context;
        this.modelsubsearches = modelsubsearches;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.searchsubcategorycontainer,parent,false);

        return new AdapterSubcategorysearch.MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        String name=modelsubsearches.get(position).getName();
        String image=modelsubsearches.get(position).getImage();
        String wages=modelsubsearches.get(position).getWages();
        String work=modelsubsearches.get(position).getWork();


        holder.name.setText(name);
        holder.wages.setText(wages);
        holder.work.setText(work);

        try {
            Picasso.get().load(image).placeholder(R.drawable.employ).into(holder.imageView);

        }
        catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return modelsubsearches.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder{
ImageView imageView;
TextView name,wages,work;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.srcimg);
            name=itemView.findViewById(R.id.freelancername);
            wages=itemView.findViewById(R.id.freelancerwages);
            work=itemView.findViewById(R.id.freelancerwork);
        }
    }
}
