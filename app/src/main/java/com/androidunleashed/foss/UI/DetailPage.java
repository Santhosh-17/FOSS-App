package com.androidunleashed.foss.UI;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetailPage extends AppCompatActivity {

    private EditText nameT,mailT,locT;
    private Button conButton;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String UserID,name,mail,loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        nameT = findViewById(R.id.UserName);
        mailT = findViewById(R.id.UserEmail);
        locT = findViewById(R.id.location);
        conButton = findViewById(R.id.continueButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        UserID = firebaseAuth.getCurrentUser().getUid();

        conButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String phone = firebaseAuth.getCurrentUser().getPhoneNumber().toString();
                name = nameT.getText().toString();
                mail = mailT.getText().toString();
                loc = locT.getText().toString();

                DocumentReference documentReference = firestore.collection("Users").document(UserID);
                Map<String,Object> user = new HashMap<>();
                user.put("Name",name);
                user.put("Email",mail);
                user.put("Location",loc);
                user.put("PhoneNo",phone);
                user.put("userType", "Customer");
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DetailPage.this,"Data Saved",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailPage.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailPage.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });

                DocumentReference documentReference2 = firestore.collection("ID").document(firebaseAuth.getCurrentUser().getUid());
                Map<String,Object> user2 = new HashMap<>();
                user2.put("type","Customer");
                Check.val = "Customer";
                documentReference2.set(user2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailPage.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }
}
