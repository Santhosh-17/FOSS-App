package com.androidunleashed.foss.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.androidunleashed.foss.Adapter.OrderAdapter;
import com.androidunleashed.foss.MainActivity;
import com.androidunleashed.foss.Model.UserModel;
import com.androidunleashed.foss.R;
import com.androidunleashed.foss.UserFiles.MyCart;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class OrderB extends AppCompatActivity {

    private FirebaseAuth auth1 = FirebaseAuth.getInstance();
    private FirebaseFirestore fstore1 = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser = auth1.getCurrentUser();

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private String temp2 = firebaseUser.getDisplayName();
    private  String temp3 = firebaseUser.getUid();

    private CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Orders");

    private OrderAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_b);
        setUpRecyclerView();


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderB.this, BNewsFeed.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void setUpRecyclerView() {

        Query query = collectionReference.orderBy("itemName",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query,UserModel.class)
                .build();

        adapter = new OrderAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.orderRecycler);
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
