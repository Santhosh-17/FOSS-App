package com.androidunleashed.foss.PackUpdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidunleashed.foss.R;
import com.androidunleashed.foss.UI.BusinessLogin;
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

public class BusinessUpdate extends AppCompatActivity {

    private Button updateB,CancelB;
    private EditText name,loc;
    private TextView mail;
    String mails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_update);

        updateB = findViewById(R.id.updateButtonB);
        CancelB = findViewById(R.id.cancelButtonUB);
        name = findViewById(R.id.uprofileName);
        loc = findViewById(R.id.uprofileLocation1);
        mail = findViewById(R.id.uprofileMAil1);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = firestore.collection("Au-Business-Users").document(firebaseAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {

                String names = documentSnapshot.getString("name");
                mails = documentSnapshot.getString("mail");
                String locs = documentSnapshot.getString("location");

                name.setText(names);
                mail.setText(mails);
                loc.setText(locs);

            }
        });

        updateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tempName = name.getText().toString();
                String tempLoc = loc.getText().toString();

                DocumentReference documentReference1 = firestore.collection("Au-Business-Users").document(firebaseAuth.getCurrentUser().getUid());

                Map<String,Object> user1 = new HashMap<>();
                user1.put("name",tempName);
                user1.put("mail",mails);
                user1.put("location", tempLoc);

                documentReference1.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(BusinessUpdate.this,"Updated!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BusinessUpdate.this, BusinessProfile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });

        CancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessUpdate.this, BusinessProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BusinessUpdate.this, BusinessProfile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
