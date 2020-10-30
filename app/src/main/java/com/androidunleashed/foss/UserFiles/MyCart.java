package com.androidunleashed.foss.UserFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.androidunleashed.foss.Adapter.CartAdapter;
import com.androidunleashed.foss.MainActivity;
import com.androidunleashed.foss.Model.CartModel;
import com.androidunleashed.foss.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MyCart extends AppCompatActivity {



    private FirebaseAuth auth1 = FirebaseAuth.getInstance();
    private FirebaseFirestore fstore1 = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser = auth1.getCurrentUser();

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private  String temp3 = firebaseUser.getUid();

    private CollectionReference collectionReference = firestore.collection("UserCart-"+temp3);

    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        
        setUpRecyclerView();


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyCart.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void setUpRecyclerView() {

        Query query = collectionReference.orderBy("itemName",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<CartModel> options = new FirestoreRecyclerOptions.Builder<CartModel>()
                .setQuery(query,CartModel.class)
                .build();

        adapter = new CartAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.cartRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
