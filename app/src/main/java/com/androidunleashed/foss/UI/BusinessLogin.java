package com.androidunleashed.foss.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidunleashed.foss.Check;
import com.androidunleashed.foss.MainActivity;
import com.androidunleashed.foss.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class BusinessLogin extends AppCompatActivity {

    private Button register,loginButton;
    private EditText userMail,password;
    private ProgressBar progressBar;

    FirebaseAuth auth;
    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);

        register = findViewById(R.id.registerButton);
        userMail = findViewById(R.id.loginMail);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressbar1);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessLogin.this,BusinessSignup.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = userMail.getText().toString();
                String pwd = password.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    userMail.setError("Please enter a valid email");
                    userMail.requestFocus();
                    return;
                }
                if(password.length()<6){
                    password.setError("Minimum Length of password should be 6");
                    password.requestFocus();
                    return;
                }

                if(mail.isEmpty()) {
                    userMail.setError("Email is required");
                    userMail.requestFocus();
                    return;
                }else if(pwd.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser(mail,pwd);
                }
            }
        });
    }

    private void loginUser(String mail, String pwd) {


        auth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(BusinessLogin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {


                    //  Toast.makeText(LoginScr.this, "Login Successful!.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BusinessLogin.this,BNewsFeed.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Log.w("Debug", "signInWithEmail:failure", task.getException());
                    Toast.makeText(BusinessLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    password.setText("");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BusinessLogin.this,UserLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }



}
