package com.example.employer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddJob extends AppCompatActivity {

   private EditText user_name,user_last,user_phonenumber,user_address;
   ProgressDialog pd;
   Button save;
    String name,location,wages,work;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        user_name=findViewById(R.id.usernamefirst);
        user_last=findViewById(R.id.usernamelast);
        user_phonenumber=findViewById(R.id.userphonenumber);
        user_address=findViewById(R.id.useraddress);
        pd=new ProgressDialog(AddJob.this);
        pd.setMessage("Saving...");

        save=findViewById(R.id.usersavebutton);

        save.setOnClickListener(view -> {
            name=user_name.getText().toString().trim();
            location=user_last.getText().toString().trim();
            wages=user_phonenumber.getText().toString().trim();
            work=user_address.getText().toString().trim();
            if (TextUtils.isEmpty(user_name.getText().toString().trim())){
                user_name.setError("name is muandatory");
                user_name.setFocusable(true);
            }
            else if (TextUtils.isEmpty(user_phonenumber.getText().toString().trim())){
                user_phonenumber.setError("wages is mandatory");
                user_phonenumber.setFocusable(true);
            }

            else if (TextUtils.isEmpty(user_last.getText().toString().trim())){
                user_last.setError("work is mandatory");
                user_last.setFocusable(true);
            }
            else {

                pd.show();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("home");
                FirebaseAuth auth=FirebaseAuth.getInstance();
                FirebaseUser firebaseUser= auth.getCurrentUser();
                HashMap<String,Object> results=new HashMap<>();
                results.put("companyname",name);
                results.put("fee",wages);
                results.put("location",location);
                results.put("work",work);
                results.put("kenburnlogo","https://st.depositphotos.com/1032577/4771/i/950/depositphotos_47712203-stock-photo-were-hiring.jpg");
                results.put("companylogo","https://i.redd.it/cbj106i8yzb21.png");
                String has=reference.push().getKey();
                reference.child("foryou").child(has).updateChildren(results).addOnSuccessListener(aVoid -> {
                    pd.dismiss();
                    Toast.makeText(AddJob.this,"Your Job is Pos" +
                            "3ted",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddJob.this, MainActivity.class));
                    finish();

                }).addOnFailureListener(e -> {
                    pd.dismiss();
                    Toast.makeText(AddJob.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                });


            }
        });

    }
}