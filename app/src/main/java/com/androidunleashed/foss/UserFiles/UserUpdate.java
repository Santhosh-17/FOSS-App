package com.androidunleashed.foss.UserFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidunleashed.foss.PackUpdate.BusinessUpdate;
import com.androidunleashed.foss.R;
import com.androidunleashed.foss.UI.BusinessProfile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class UserUpdate extends AppCompatActivity {

    private Button userCancelButton,userUpdateButton;
    private TextView userPhone;
    private EditText userName, userMail, userLocation;
    private String phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);


        userName = findViewById(R.id.userEnterUProfileName);
        userMail = findViewById(R.id.userUProfileMAil1);
        userPhone = findViewById(R.id.userUProfileNum1);
        userLocation = findViewById(R.id.userUProfileLocation1);
        userUpdateButton = findViewById(R.id.userUProfileUpdate);
        userCancelButton = findViewById(R.id.userUProfileCancel);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = firestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid());

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {

                String usrnames = documentSnapshot.getString("Name");
                String usrmails = documentSnapshot.getString("Email");
                phones = documentSnapshot.getString("PhoneNo");
                String usrlocs = documentSnapshot.getString("Location");

              userName.setText(usrnames);
              userMail.setText(usrmails);
              userPhone.setText(phones);
              userLocation.setText(usrlocs);

            }
        });

        userUpdateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String tempuserName = userName.getText().toString();
                String tempuserMail = userMail.getText().toString();
                String tempUserLoc = userLocation.getText().toString();

                DocumentReference documentReference1 = firestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid());
                Map<String,Object> user1 = new HashMap<>();
                user1.put("Name",tempuserName);
                user1.put("Email",tempuserMail);
                user1.put("Location", tempUserLoc);
                user1.put("PhoneNo",phones);
                documentReference1.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(UserUpdate.this,"Updated!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserUpdate.this, UserPofile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
            }

        });

        userCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserUpdate.this, UserPofile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserUpdate.this, UserPofile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }


}
