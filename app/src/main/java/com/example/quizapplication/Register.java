package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    public static final String TAG ="TAG";


    EditText mFullname, mEmail, mPasswored, mPhone;

    Button mRegisterBtn;

    TextView mLoginBtn;

    ProgressBar progressBar;

    String UserId;

    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mFullname= findViewById(R.id.fullname);
        mEmail= findViewById(R.id.Email);
        mPasswored= findViewById(R.id.password);
        mPhone= findViewById(R.id.phone);
        mRegisterBtn= findViewById(R.id.registerBtn);
        mLoginBtn= findViewById(R.id.createText);
        progressBar= findViewById(R.id.progressbar);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if(fauth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString().trim();
                String password = mPasswored.getText().toString().trim();
                final String fullname = mFullname.getText().toString();
                final String phone = mPhone.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email Is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mEmail.setError("Password Is Required");
                    return;
                }
                if(password.length()<6)
                {
                    mPasswored.setError("Password must be >=6");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser fuser= fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Register.this, "Register Succesful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "OnFailure: Email Not Sent"+ e.getMessage());

                                }
                            });


                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            UserId = fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(UserId);
                            Map<String,Object> user= new HashMap<>();
                            user.put("fName",fullname);
                            user.put("email",email);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess:user profile is created for"+ UserId);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Log.d(TAG, "onfailure:"+e.toString() );

                                }
                            });


                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {

                            Toast.makeText(Register.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });



    }
}