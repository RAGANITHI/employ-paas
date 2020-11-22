package com.insignia.employ.Dashboard;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.insignia.employ.Chatapp.ChatActivity;
import com.insignia.employ.MapActivity;
import com.insignia.employ.navdrawer.DrawerAdapter;
import com.insignia.employ.navdrawer.DrawerItem;
import com.insignia.employ.Fragments.HomeFragment;
import com.insignia.employ.Fragments.ProfileFragment;
import com.insignia.employ.Fragments.SearchFragment;
import com.insignia.employ.Fragments.SkillFragment;
import com.insignia.employ.Fragments.WfhFragment;
import com.insignia.employ.R;
import com.insignia.employ.navdrawer.SimpleItem;
import com.insignia.employ.navdrawer.SpaceItem;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Dashboard extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    AnimatedBottomBar animatedBottomBar;
    private static final int POS_CLOSE=0;
    private static final int POS_DASHBOARD=1;
    private static final int POS_MY_PROFILE=2;
    private static final int POS_NEARBYRES=3;
    private static final int POS_SETTINGS=4;
    private static final int POS_ABOUTUS=5;
    private static final int POS_LOGOUT=6;
    NotificationManagerCompat notificationManager;

    public static final String CHANNEL_ID="employ";
    public static final String CHANNEL_NAME="Employ";
    public static final String CHANNEL_DESC="Employ Notification";
    private SlidingRootNav slidingRootNav;

    private String[] screenTiles;
    private Drawable[] screenIcons;
    ImageView imageView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar=findViewById(R.id.toolbar);
        imageView=findViewById(R.id.message);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitle("");
        toolbar.setSubtitle("");
        notificationManager= NotificationManagerCompat.from(this);
        slidingRootNav=new SlidingRootNavBuilder(Dashboard.this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();
        screenIcons=loadScreenIcons();
        screenTiles=loadScreenTiles();
        DrawerAdapter adapter=new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_MY_PROFILE),
                createItemFor(POS_NEARBYRES),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_ABOUTUS),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)

                ));
        adapter.setListener(this);

        RecyclerView list=findViewById(R.id.drawerList);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_DASHBOARD);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, ChatActivity.class));


            }
        });
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {

            String token=task.getResult().getToken();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("token");
            FirebaseAuth auth=FirebaseAuth.getInstance();
            FirebaseUser firebaseUser= auth.getCurrentUser();
            HashMap<String,Object> results=new HashMap<>();
            results.put(firebaseUser.getUid(),token);
            reference.updateChildren(results);
        });
        Notification notification= new NotificationCompat.Builder(Dashboard.this,
                CHANNEL_ID)
                .setSmallIcon(R.drawable.work)
                .setContentTitle("Job Notification")
                .setContentText("You have been selected!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Karthik woods have shortlisted you for the Helper role in bangalore!"))
                .build();
        notificationManager.notify(1,notification);
        animatedBottomBar=findViewById(R.id.bottombar);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {

                Fragment selected_fragment=null;
                if (tab1.getTitle().equals("Home")){

                    selected_fragment=new HomeFragment();

                }
                else if(tab1.getTitle().equals("Search")){

                    selected_fragment=new SearchFragment();

                }
                else if(tab1.getTitle().equals("Learn")){

                    selected_fragment=new SkillFragment();

                }
                else if(tab1.getTitle().equals("Work From Home")){

                    selected_fragment=new WfhFragment();

                }
                else {
                    selected_fragment=new ProfileFragment();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selected_fragment).commit();

            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcons[position],screenTiles[position])
                .withIconTint(color(R.color.pink))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.pink))
                .withSelectedTextTint(color(R.color.pink));
    }
    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this,res);
    }

    private String[] loadScreenTiles() {
        return getResources().getStringArray(R.array.id_arrayscreentiles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta=getResources().obtainTypedArray(R.array.id_arrayscreenicons);
   Drawable[] icons=new Drawable[ta.length()];
   for (int i=0;i<ta.length();i++){
       int id=ta.getResourceId(i,0);
       if(id!=0){
           icons[i] =ContextCompat.getDrawable(this,id);
       }
   }ta.recycle();
   return icons;
    }


    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        if (position == POS_DASHBOARD){


        }
        else if (position == POS_MY_PROFILE){

        }
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void Mapclick(View view) {
        startActivity(new Intent(Dashboard.this, MapActivity.class));
    }
}