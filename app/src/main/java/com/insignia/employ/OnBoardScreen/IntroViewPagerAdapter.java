package com.insignia.employ.OnBoardScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.insignia.employ.R;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {
    Context mcontext;
    List<screenitem> mlistscreen;

    public IntroViewPagerAdapter(Context mcontext, List<screenitem> mlistscreen) {
        this.mcontext = mcontext;
        this.mlistscreen = mlistscreen;
    }

    @Override
    public int getCount() {
        return mlistscreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutscreen=inflater.inflate(R.layout.layout_screen,null);

        ImageView imgslide=layoutscreen.findViewById(R.id.screenimage);
        TextView Title=layoutscreen.findViewById(R.id.titletext);
        TextView DEscription=layoutscreen.findViewById(R.id.descriptiontext);

        Title.setText(mlistscreen.get(position).getTitle());
        DEscription.setText(mlistscreen.get(position).getDescription());
        imgslide.setImageResource(mlistscreen.get(position).getScreenimg());
        container.addView(layoutscreen);
        return layoutscreen;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
