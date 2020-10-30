package com.androidunleashed.foss.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidunleashed.foss.PackUpdate.BusinessUpdate;
import com.androidunleashed.foss.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class BusinessProfile extends AppCompatActivity {

    TextView cmpName,cmpMAil,cmpLoc,updateP,signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        cmpName = findViewById(R.id.profileName);
        cmpMAil = findViewById(R.id.profileMAil1);
        cmpLoc = findViewById(R.id.profileLocation1);
        updateP = findViewById(R.id.profileUpdate) ;
        signout = findViewById(R.id.profileSignuOut);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = firestore.collection("Au-Business-Users").document(firebaseAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {

                String name = documentSnapshot.getString("name");
                String mail = documentSnapshot.getString("mail");
                String loc = documentSnapshot.getString("location");

                cmpName.setText(name);
                cmpMAil.setText(mail);
                cmpLoc.setText(loc);

            }
        });

        updateP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BusinessProfile.this, BusinessUpdate.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(BusinessProfile.this,"Logging Out...",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BusinessProfile.this, BusinessLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(BusinessProfile.this,BNewsFeed.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }
}
