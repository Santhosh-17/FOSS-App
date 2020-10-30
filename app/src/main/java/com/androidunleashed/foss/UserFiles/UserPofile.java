package com.androidunleashed.foss.UserFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidunleashed.foss.MainActivity;
import com.androidunleashed.foss.PackUpdate.BusinessUpdate;
import com.androidunleashed.foss.R;
import com.androidunleashed.foss.UI.BNewsFeed;
import com.androidunleashed.foss.UI.BusinessLogin;
import com.androidunleashed.foss.UI.BusinessProfile;
import com.androidunleashed.foss.UI.UserLogin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserPofile extends AppCompatActivity {

    private TextView usrName, usrMail, usrPhone, usrLoc, update, Signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pofile);

        usrName = findViewById(R.id.userEnterProfileName);
        usrMail = findViewById(R.id.userProfileMAil1);
        usrPhone = findViewById(R.id.userProfileNum1);
        usrLoc = findViewById(R.id.userProfileLocation1);
        update = findViewById(R.id.userProfileUpdate);
        Signout = findViewById(R.id.userProfileSignOut);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = firestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {

                String uname = documentSnapshot.getString("Name");
                String umail = documentSnapshot.getString("Email");
                String uloc = documentSnapshot.getString("Location");
                String uphone = documentSnapshot.getString("PhoneNo");

                usrName.setText(uname);
                usrMail.setText(umail);
                usrLoc.setText(uloc);
                usrPhone.setText(uphone);


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserPofile.this, UserUpdate.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserPofile.this, "Logging Out...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UserPofile.this, UserLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(UserPofile.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }
}