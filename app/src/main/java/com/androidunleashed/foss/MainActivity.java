package com.androidunleashed.foss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidunleashed.foss.UI.BNewsFeed;
import com.androidunleashed.foss.UI.BusinessLogin;
import com.androidunleashed.foss.UI.UserLogin;
import com.androidunleashed.foss.UserFiles.MyCart;
import com.androidunleashed.foss.UserFiles.UserPofile;
import com.androidunleashed.foss.UserFiles.confirmOrder;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
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
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();



    private CollectionReference collectionReference = db.collection("Menu");



    private MenuAdapter adapter;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.Utoolbar);
        setSupportActionBar(toolbar);

        setUpRecyclerView();


    }





    private void setUpRecyclerView() {

        Query query = collectionReference.orderBy("itemName",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<MenuModel> options = new FirestoreRecyclerOptions.Builder<MenuModel>()
                .setQuery(query,MenuModel.class)
                .build();

        adapter = new MenuAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.Urecycler);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profilemm1) {


            Intent intent = new Intent(MainActivity.this, UserPofile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();


        }

        if(id == R.id.cart){

            Intent intent = new Intent(MainActivity.this, MyCart.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}