package com.insignia.employ.LoginandRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.insignia.employ.Dashboard.Dashboard;
import com.insignia.employ.R;

import java.util.HashMap;

public class Complete_Profile extends AppCompatActivity {

    EditText user_name,user_phonenumber,user_address,user_last,user_adhaar,user_pancard;
    Button save;
    String firstname,phone,address,lastname,adhaar,pancard;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete__profile);

        user_name=findViewById(R.id.usernamefirst);
        user_last=findViewById(R.id.usernamelast);
        user_phonenumber=findViewById(R.id.userphonenumber);
        user_address=findViewById(R.id.useraddress);
        user_adhaar=findViewById(R.id.useradhaar);
        user_pancard=findViewById(R.id.userpancard);
        pd=new ProgressDialog(Complete_Profile.this);
        pd.setMessage("Saving...");

        save=findViewById(R.id.usersavebutton);

        save.setOnClickListener(view -> {
            firstname=user_name.getText().toString().trim();
            lastname=user_last.getText().toString().trim();
            phone=user_phonenumber.getText().toString().trim();
            address=user_address.getText().toString().trim();
            pancard=user_pancard.getText().toString().trim();
            adhaar=user_adhaar.getText().toString().trim();
            if (TextUtils.isEmpty(user_name.getText().toString().trim())){
                user_name.setError("First name is muandatory");
                user_name.setFocusable(true);
            }
            else if (TextUtils.isEmpty(user_phonenumber.getText().toString().trim())){
                user_phonenumber.setError("Phone number is mandatory");
                user_phonenumber.setFocusable(true);
            }

            else if (TextUtils.isEmpty(user_last.getText().toString().trim())){
                user_last.setError("last name is mandatory");
                user_last.setFocusable(true);
            }
            else if (TextUtils.isEmpty(user_adhaar.getText().toString().trim())){
                user_adhaar.setError("Adhaar number is mandatory");
                user_adhaar.setFocusable(true);
            }
            else {
                if (address.isEmpty() || pancard.isEmpty()){
                    address="Address not updated";
                    pancard="Pan card not updated";

                }
                pd.show();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("appusers");
                FirebaseAuth auth=FirebaseAuth.getInstance();
                FirebaseUser firebaseUser= auth.getCurrentUser();
                HashMap<String,Object> results=new HashMap<>();
                results.put("firstname",firstname);
                results.put("lastname",lastname);
                results.put("phone",phone);
                results.put("address",address);
                results.put("adhaar",address);
                results.put("pancard",address);
                reference.child(firebaseUser.getUid()).child("userdetails").updateChildren(results).addOnSuccessListener(aVoid -> {
                    pd.dismiss();
                    Toast.makeText(Complete_Profile.this,"User details saved",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Complete_Profile.this, Dashboard.class));
                    finish();

                }).addOnFailureListener(e -> {
                    pd.dismiss();
                    Toast.makeText(Complete_Profile.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                });


            }
        });

    }
}