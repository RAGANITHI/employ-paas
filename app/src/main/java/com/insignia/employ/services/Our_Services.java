package com.insignia.employ.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.insignia.employ.LoginandRegister.Login;
import com.insignia.employ.R;


public class Our_Services extends AppCompatActivity {
    LinearLayout email,insta,fb,twitter;
    ImageView imageView;
    TextView textView;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our__services);

        email=findViewById(R.id.emaillinear);
        insta=findViewById(R.id.instalinear);
        fb=findViewById(R.id.fblinear);
        twitter=findViewById(R.id.twitterlinear);
        imageView=findViewById(R.id.servicelogo);
        textView=findViewById(R.id.servicetext);
        cardView=findViewById(R.id.servicecard);

        email.setOnClickListener(view -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@20thsensebusiness.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Query about Hello Kanyakumari Application");
            email.putExtra(Intent.EXTRA_TEXT, "");

            //need this to prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        });

        insta.setOnClickListener(view -> {
            Intent intent=new Intent(this, Login.class);
            intent.putExtra("link","https://instagram.com/insigniaencorp");
            startActivity(intent);
        });
        fb.setOnClickListener(view -> {
            Intent intent=new Intent(this, Login.class);
            intent.putExtra("link","https://instagram.com/insigniaencorp");
            startActivity(intent);
        });
        twitter.setOnClickListener(view -> {
            Intent intent=new Intent(this, Login.class);
            intent.putExtra("link","https://instagram.com/insigniaencorp");
            startActivity(intent);
        });
    }

    public void serviceback(View view) {

        onBackPressed();
        finish();
    }
}