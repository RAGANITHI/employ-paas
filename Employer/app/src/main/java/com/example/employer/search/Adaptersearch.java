package com.example.employer.search;

import android.content.Context;
import android.content.Intent;
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

public class Adaptersearch extends RecyclerView.Adapter<Adaptersearch.MyViewHolder> {

    Context context;
    List<Modelsearch> modelsearches;


    public Adaptersearch(Context context, List<Modelsearch> modelsearches) {
        this.context = context;
        this.modelsearches = modelsearches;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.freelancelayout,parent,false);
        return new Adaptersearch.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String name=modelsearches.get(position).getName();
        String image=modelsearches.get(position).getImage();
        String treename=modelsearches.get(position).getTreename();

        holder.textView.setText(name);

        try {
            Picasso.get().load(image).placeholder(R.drawable.employ).into(holder.imageView);
        }
        catch (Exception e){

        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context,Searchsubcategory.class);
            intent.putExtra("treename",name);
            context.startActivity(intent);
        });



    }

    @Override
    public int getItemCount() {
        return modelsearches.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.freelancertext);
            imageView=itemView.findViewById(R.id.freelancerimg);
        }
    }
}
