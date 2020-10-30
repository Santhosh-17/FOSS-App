package com.androidunleashed.foss;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidunleashed.foss.UI.BNewsFeed;
import com.androidunleashed.foss.UI.UserLogin;
import com.androidunleashed.foss.UserFiles.confirmOrder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MenuAdapter extends FirestoreRecyclerAdapter<MenuModel, MenuAdapter.MenuHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MenuAdapter(@NonNull FirestoreRecyclerOptions<MenuModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MenuHolder menuHolder, int i, @NonNull MenuModel menuModel) {
        menuHolder.itemName.setText(menuModel.getItemName());
        menuHolder.itemQuantity.setText("Quantity: "+menuModel.getItemQ());
        menuHolder.itemPrice.setText("Rs. "+menuModel.getItemPrice());


    }



    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);

        return new MenuHolder(v);
    }

    class MenuHolder extends RecyclerView.ViewHolder{

        TextView itemName , itemPrice, itemQuantity;

        public MenuHolder(@NonNull final View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ItemNameView);
            itemPrice = itemView.findViewById(R.id.itemPriceView);
            itemQuantity = itemView.findViewById(R.id.itemQuantityView);





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(Check.val.equals("Customer")){

                        String iname = itemName.getText().toString();
                        String iquantity = itemQuantity.getText().toString();
                        String iprice = itemPrice.getText().toString();

                        Intent intent = new Intent(itemView.getContext(), confirmOrder.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("iname",iname);
                        intent.putExtra("iprice",iprice);
                        intent.putExtra("iquantity",iquantity);
                        itemView.getContext().startActivity(intent);

                    }

                }
            });




        }
    }


}
