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

import com.androidunleashed.foss.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.androidunleashed.foss.UI.Sample.inc;

public class BusinessSignup extends AppCompatActivity {

    ProgressBar progressBarb;
    Button signup,cancel;
    FirebaseAuth auth;
    EditText Cname,Cmail,pwd,cpwd,Cloc;
    FirebaseFirestore fstore;
    String name,loc;
    String maddress,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_signup);

        progressBarb = findViewById(R.id.progressbarB);
        Cname = findViewById(R.id.nameB);
        Cmail= findViewById(R.id.mailB);
        Cloc = findViewById(R.id.locationB);
        pwd = findViewById(R.id.passwd);
        cpwd = findViewById(R.id.cpasswd);
        signup = findViewById(R.id.signup);
        cancel = findViewById(R.id.Cancel);


        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessSignup.this, BusinessLogin.class));
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Cname.getText().toString();
                maddress = Cmail.getText().toString();
                loc = Cloc.getText().toString();
                password = pwd.getText().toString();
                String cpassword = cpwd.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(maddress).matches()){
                    Cmail.setError("Please enter a valid email");
                    Cmail.requestFocus();
                    return;
                }
                if(password.length()<6){
                    pwd.setError("Minimum Length of password should be 6");
                    pwd.requestFocus();
                    return;
                }

                if(maddress.isEmpty()) {
                    Cmail.setError("Email is required");
                    Cmail.requestFocus();
                    return;
                }else if(password.isEmpty()){
                    pwd.setError("Password is required");
                    pwd.requestFocus();
                    return;
                }else if(name.isEmpty()){
                    Cname.setError("UserName is required");
                    Cname.requestFocus();
                    return;
                }else if(cpassword.isEmpty()){
                    cpwd.setError("Please enter the password again");
                    cpwd.requestFocus();
                    return;
                }else if(!password.equals(cpassword))
                {
                    Toast.makeText(BusinessSignup.this, "InCorrect Passwords", Toast.LENGTH_SHORT).show();
                }else{
                    signUp();
                }
            }
        });

    }


    private void signUp() {

        progressBarb.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(maddress,password).addOnCompleteListener(BusinessSignup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBarb.setVisibility(View.GONE);
                if (task.isSuccessful()) {

                    FirebaseUser user = auth.getCurrentUser();

                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(BusinessSignup.this,"Verification Mail has sent",Toast.LENGTH_SHORT).show();

                        }
                    });


                    DocumentReference documentReference = fstore.collection("Au-Business-Users").document(user.getUid());

                    Map<String,Object> user1 = new HashMap<>();
                    user1.put("name",name);
                    user1.put("mail",maddress);
                    user1.put("location", loc);
                    user1.put("Password", password);
                    user1.put("userType", "Business");
                    user1.put("userid",user.getUid());


                    documentReference.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("Debug","User is added");
                        }
                    });


                    DocumentReference documentReference2 = fstore.collection("ID").document(auth.getCurrentUser().getUid());
                    Map<String,Object> user2 = new HashMap<>();
                    user2.put("type","Business");
                    documentReference2.set(user2).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
//                       //
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BusinessSignup.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }
                    });


//                    documentReference.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d("Debug","User is added");
//                        }
//                    });
                    Intent intent = new Intent(BusinessSignup.this,verify_mail.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


                } else {
                    Log.w("Debug", "signInWithEmail:failure", task.getException());
                    Toast.makeText(BusinessSignup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    pwd.setText("");
                    cpwd.setText("");
                }
            }
        });
    }

}
