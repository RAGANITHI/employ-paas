package com.insignia.employ.LoginandRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.insignia.employ.R;

import java.util.Locale;

public class Choose_language extends AppCompatActivity {

    RadioButton english,tamil,malayalam,hindi,kannada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        LoadLocale();
        english=findViewById(R.id.english);

        english.setOnClickListener(view -> {
            english.setChecked(true);
            tamil.setChecked(false);
            malayalam.setChecked(false);
            kannada.setChecked(false);
            hindi.setChecked(false);
            setlangugae();
        });
        tamil=findViewById(R.id.tamil);
        tamil.setOnClickListener(view -> {
            tamil.setChecked(true);
            english.setChecked(false);
            malayalam.setChecked(false);
            hindi.setChecked(false);
            kannada.setChecked(false);
            setlangugae();
        });
        malayalam=findViewById(R.id.malayalam);
        malayalam.setOnClickListener(view -> {
            malayalam.setChecked(true);
            tamil.setChecked(false);
            english.setChecked(false);
            hindi.setChecked(false);
            kannada.setChecked(false);
            setlangugae();
        });
        hindi=findViewById(R.id.hindi);
        hindi.setOnClickListener(view -> {
            hindi.setChecked(true);
            tamil.setChecked(false);
            english.setChecked(false);
            malayalam.setChecked(false);
            kannada.setChecked(false);
            setlangugae();
        });
        kannada=findViewById(R.id.kannada);
        kannada.setOnClickListener(view -> {
            kannada.setChecked(true);
            tamil.setChecked(false);
            english.setChecked(false);
            malayalam.setChecked(false);
            hindi.setChecked(false);
            setlangugae();
        });




    }

    private void setlangugae() {
        if (english.isChecked()){
            setLocale("en");
            recreate();

        }
        else if (tamil.isChecked()){
            setLocale("ta");
            recreate();
        }
        else if (malayalam.isChecked()){
            setLocale("ml");
            recreate();
        }
        else if (kannada.isChecked()){
            setLocale("kn");
            recreate();
        }
        else if (hindi.isChecked()){
            setLocale("hi");
            recreate();
        }
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void LoadLocale(){
        SharedPreferences preferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=preferences.getString("My_Lang","");
        setLocale(language);
    }

    public void Continue(View view) {

        startActivity(new Intent(Choose_language.this, Login.class));
        finish();
    }

}