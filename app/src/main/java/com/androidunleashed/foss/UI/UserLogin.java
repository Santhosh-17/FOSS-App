package com.androidunleashed.foss.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidunleashed.foss.Check;
import com.androidunleashed.foss.MainActivity;
import com.androidunleashed.foss.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserLogin extends AppCompatActivity {

    private Button BusinessButton,sendotp;
    private EditText phoneNumber;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;


    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//
//        if(firebaseUser!=null ){
//
//
//            DocumentReference documentReference = firestore.collection("ID").document(firebaseAuth.getCurrentUser().getUid());
//            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//                @Override
//                public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
//
//                    String utype = documentSnapshot.getString("type");
//                    if(utype.equals("Business")){
//
//                        Check.val = "Business";
//                        Intent intent = new Intent(UserLogin.this, BNewsFeed.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                    else {
//
//                        Check.val = "Customer";
//                        Intent intent = new Intent(UserLogin.this, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            });

//            DocumentReference documentReference = firestore.collection("Login").document("userType");
//            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//                @Override
//                public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
//
//                    String type = documentSnapshot.getString("ID");
//                    if(type.equals("1")){
//
//                        Intent intent = new Intent(UserLogin.this, BNewsFeed.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                    else{
//                        Check.val = 0;
//                        Intent intent = new Intent(UserLogin.this, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            });


//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        BusinessButton = findViewById(R.id.fossBusinessButton);
        sendotp = findViewById(R.id.sendOtpButton);
        phoneNumber = findViewById(R.id.phoneNumberU);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();


        BusinessButton.setVisibility(View.INVISIBLE);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = phoneNumber.getText().toString();
                if(temp.length()>9){
                    Intent intent = new Intent(UserLogin.this, UserSignup.class);
                    intent.putExtra("phoneNo",temp);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    phoneNumber.setError("Invalid Phone Number!");
                }
            }
        });



        BusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, BusinessLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


    }

}
