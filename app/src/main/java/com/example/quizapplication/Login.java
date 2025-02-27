package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText mEmail,mPasswored;

    Button mLoginBtn;

    TextView mCreateBtn;


    ProgressBar progressBar;

    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEmail =findViewById(R.id.Email);
        mPasswored =findViewById(R.id.password);
        progressBar =findViewById(R.id.progressbar);
        mLoginBtn=findViewById(R.id.loginBtn);
        mCreateBtn=findViewById(R.id.createText);

        fauth= FirebaseAuth.getInstance();

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getApplicationContext(),Register.class));
                finish();
            }
        });


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = mEmail.getText().toString().trim();
                String password = mPasswored.getText().toString().trim();


                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    mEmail.setError("Password is required");
                    return;
                }

                if(password.length()<6){
                    mPasswored.setError("Password must be >=6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                        else
                        {
                            Toast.makeText(Login.this, "Error !"+ task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}