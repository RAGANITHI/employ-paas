package com.insignia.employ.LoginandRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.insignia.employ.MainActivity;
import com.insignia.employ.R;

public class Login extends AppCompatActivity {

    private EditText phone_login;
    private Button phone_login_button,loginemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone_login=findViewById(R.id.loginphone);


        phone_login_button=findViewById(R.id.phoneloginbutton);

        phone_login_button.setOnClickListener(view -> {
            String mobile = phone_login.getText().toString().trim();

            if(mobile.isEmpty() || mobile.length() < 10){
                phone_login.setError("Enter a valid mobile");
                phone_login.requestFocus();
                return;
            }


            Intent intent = new Intent(Login.this, Login_Verify.class);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
        });
    }

    public void phoneloginback(View view) {
        onBackPressed();
        finish();
    }
}