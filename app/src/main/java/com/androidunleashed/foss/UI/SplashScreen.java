package com.androidunleashed.foss.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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

public class SplashScreen extends AppCompatActivity {

    private String utype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        if (firebaseUser != null) {


            DocumentReference documentReference = firestore.collection("ID").document(firebaseAuth.getCurrentUser().getUid());
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {

                    utype = documentSnapshot.getString("type");

                }
            });
        }


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                   if(firebaseUser != null){

                       if (utype.equals("Business")) {

                            Check.val = "Business";
                            Intent intent = new Intent(SplashScreen.this, BNewsFeed.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        } else {

                            Check.val = "Customer";
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }

                    }else{
                        Intent intent = new Intent(SplashScreen.this, UserLogin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }


                }
            }, 4000);


    }
}
