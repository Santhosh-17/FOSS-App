package com.androidunleashed.foss.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidunleashed.foss.MainActivity;
import com.androidunleashed.foss.MenuModel;
import com.androidunleashed.foss.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity {

    private Button saveButton,cancelButton;
    private EditText ItemName,ItemQuantity,ItemPrice;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        ItemName = findViewById(R.id.NameET);
        ItemPrice = findViewById(R.id.priceET);
        ItemQuantity = findViewById(R.id.QuantityET);

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItem.this,BNewsFeed.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        saveButton = findViewById(R.id.saveItem);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveItem();

            }
        });
    }

    private void saveItem() {

        String name = ItemName.getText().toString();
        String quantity = ItemQuantity.getText().toString();
        String Price = ItemPrice.getText().toString();

        if(name.trim().isEmpty() || quantity.trim().isEmpty() || Price.trim().isEmpty()){
            Toast.makeText(AddItem.this,"Fill All the Fields!",Toast.LENGTH_SHORT).show();

        }else{
//

//
//            DocumentReference documentReference = firestore.collection("foss").document("ID");
//            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//                @Override
//                public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
//
////                    String type = documentSnapshot.getString("userType");
//                    String num = documentSnapshot.getString("BusinessID");
//                     id = Integer.parseInt(num);
//                }
//            });

            CollectionReference collectionReference = FirebaseFirestore.getInstance()
                    .collection("Menu");
            collectionReference.add(new MenuModel(name,quantity,Price));

//            id = id+1;
//
//            DocumentReference documentReference2 = firestore.collection("foss").document("ID");
//            Map<String,Object> user = new HashMap<>();
//            user.put("BusinessID",id);
//
//            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    //
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(AddItem.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
//                }
//            });
//
            Toast.makeText(AddItem.this,"Item Added in Menu",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddItem.this,BNewsFeed.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }



    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddItem.this,BNewsFeed.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
