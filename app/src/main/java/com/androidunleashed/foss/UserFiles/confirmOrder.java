package com.androidunleashed.foss.UserFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidunleashed.foss.MainActivity;
import com.androidunleashed.foss.Model.CartModel;
import com.androidunleashed.foss.Model.UserModel;
import com.androidunleashed.foss.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class confirmOrder extends AppCompatActivity {

    Button confirmB,cancelB;
    String n,p,q;

    String CustomerName,CustomerLoc,CustomerPhone;

    FirebaseFirestore fstore;
    FirebaseAuth auth;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(confirmOrder.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        confirmB = findViewById(R.id.oConfirmButton);
        cancelB = findViewById(R.id.oCancelButton);


        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            n = bundle.getString("iname");
            p = bundle.getString("iprice");
            q = bundle.getString("iquantity");

            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Users").document(auth.getCurrentUser().getUid());
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                    CustomerName = documentSnapshot.getString("Name");
                    CustomerLoc = documentSnapshot.getString("Location");
                    CustomerPhone = documentSnapshot.getString("PhoneNo");


                }
            });

        }

        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp1 = auth.getCurrentUser().getUid();


                CollectionReference collectionReference = FirebaseFirestore.getInstance()
                        .collection("UserCart-"+temp1);
                collectionReference.add(new CartModel(n,q,p));


                CollectionReference collectionReference1 = FirebaseFirestore.getInstance()
                        .collection("Orders");
                collectionReference1.add(new UserModel(n,CustomerName,CustomerLoc,CustomerPhone,q));

                Toast.makeText(confirmOrder.this,"Order Confirmed",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(confirmOrder.this, MyCart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(confirmOrder.this,"Order Cancelled",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(confirmOrder.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });




    }
}
