package com.insignia.employ.Home.Flipper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.insignia.employ.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFormFlipper extends RecyclerView.Adapter<AdapterFormFlipper.MyFlipperPeoplHolder>{
    Context context;
    List<ModelFormFlipper> flipperpeoplelist;
    ViewPager2 peopleviewPager2;

    public AdapterFormFlipper(Context context, List<ModelFormFlipper> flipperpeoplelist, ViewPager2 peopleviewPager2) {
        this.context = context;
        this.flipperpeoplelist = flipperpeoplelist;
        this.peopleviewPager2 = peopleviewPager2;
    }

    @NonNull
    @Override
    public MyFlipperPeoplHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_flipper_container,parent,false);

        return new MyFlipperPeoplHolder(view);    }


    @Override
    public void onBindViewHolder(@NonNull MyFlipperPeoplHolder holder, int position) {

        String flipperimageurl=flipperpeoplelist.get(position).getImageurl();

holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.recycler_fade_animation));

        try{


           // Glide.with(context)
            //        .load(flipperimageurl).thumbnail(0.5f)
            //        .apply(new RequestOptions()
           //                 .diskCacheStrategy(DiskCacheStrategy.NONE)
            //                .skipMemoryCache(true).dontAnimate())
            //        .dontTransform()
            //        .placeholder(R.drawable.loading)
            //        .into(holder.flipper_people_image);
            Picasso.get().load(flipperimageurl).fit().placeholder(R.drawable.employ).into(holder.flipper_people_image);



        }
        catch (Exception e){

        }
        if(position == flipperpeoplelist.size() - 2){
            peopleviewPager2.post(runnable);

        }

    }

    @Override
    public int getItemCount() {
        return flipperpeoplelist.size();
    }

    class MyFlipperPeoplHolder extends RecyclerView.ViewHolder{
        ImageView flipper_people_image;

        LinearLayout linearLayout;


        public MyFlipperPeoplHolder(@NonNull View itemView) {
            super(itemView);

            flipper_people_image=itemView.findViewById(R.id.homeflipperimage);

            linearLayout=itemView.findViewById(R.id.linearform);
        }
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            flipperpeoplelist.addAll(flipperpeoplelist);
            notifyDataSetChanged();
        }
    };
}
