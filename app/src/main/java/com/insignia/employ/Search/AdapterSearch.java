package com.insignia.employ.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.insignia.employ.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {
Context context;
List<ModelSearch> modelSearches;

    public AdapterSearch(Context context, List<ModelSearch> modelSearches) {
        this.context = context;
        this.modelSearches = modelSearches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_category_container,parent,false);
        return new AdapterSearch.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String imagelink=modelSearches.get(position).getImagelink();
        String name=modelSearches.get(position).getName();

        holder.imagetext.setText(name);
        try {
            Picasso.get().load(imagelink).placeholder(R.drawable.employ).into(holder.imagesearch);
        }
        catch (Exception e){

        }



    }

    @Override
    public int getItemCount() {
        return modelSearches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagesearch;
        TextView imagetext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagesearch=itemView.findViewById(R.id.searchcategoryimage);
            imagetext=itemView.findViewById(R.id.searchcategorytext);
        }
    }
}
