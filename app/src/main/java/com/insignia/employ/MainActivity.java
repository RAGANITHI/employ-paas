package com.insignia.employ;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.insignia.employ.Dashboard.Dashboard;
import com.insignia.employ.LoginandRegister.Choose_language;
import com.insignia.employ.LoginandRegister.Login;

public class MainActivity extends AppCompatActivity {

    private ImageView cloud;
    private FirebaseAuth mAuth;

    public static final String CHANNEL_ID="hello_kannyakumari";
    public static final String CHANNEL_NAME="Hello Kannyakumari";
    public static final String CHANNEL_DESC="Hello Kannyakumari Notification";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        cloud=findViewById(R.id.intro_cloud);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        final Animation vecl= (Animation) AnimationUtils.loadAnimation(MainActivity.this,R.anim.mytransition);


        cloud.setAnimation(vecl);



        Thread timer =new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    if(currentUser != null){
                        startActivity(new Intent(MainActivity.this, Dashboard.class));
                        finish();
                    }

                    else{
                        startActivity(new Intent(getApplicationContext(), Choose_language.class));
                        finish();

                    }

                }

            }
        };
        timer.start();
    }



}
