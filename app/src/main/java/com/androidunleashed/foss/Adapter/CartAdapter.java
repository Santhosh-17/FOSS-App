package com.androidunleashed.foss.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidunleashed.foss.Check;
import com.androidunleashed.foss.Model.CartModel;
import com.androidunleashed.foss.R;
import com.androidunleashed.foss.UserFiles.confirmOrder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class CartAdapter extends FirestoreRecyclerAdapter<CartModel, CartAdapter.CartHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CartAdapter(@NonNull FirestoreRecyclerOptions<CartModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CartAdapter.CartHolder CartHolder, int i, @NonNull CartModel CartModel) {
        CartHolder.itemName.setText(CartModel.getItemName());
        CartHolder.itemQuantity.setText(CartModel.getItemQ());
        CartHolder.itemPrice.setText(CartModel.getItemPrice());


    }



    @NonNull
    @Override
    public CartAdapter.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cart,parent,false);

        return new CartAdapter.CartHolder(v);
    }

    class CartHolder extends RecyclerView.ViewHolder{

        TextView itemName , itemPrice, itemQuantity;

        public CartHolder(@NonNull final View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ItemNameViewC);
            itemPrice = itemView.findViewById(R.id.itemPriceViewC);
            itemQuantity = itemView.findViewById(R.id.itemQuantityViewC);

            FirebaseFirestore firestore = FirebaseFirestore.getInstance();


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(Check.val == 0){
//
//                        String iname = itemName.getText().toString();
//                        String iquantity = itemQuantity.getText().toString();
//                        String iprice = itemPrice.getText().toString();
//
//                        Intent intent = new Intent(itemView.getContext(), confirmOrder.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.putExtra("iname",iname);
//                        intent.putExtra("iprice",iprice);
//                        intent.putExtra("iquantity",iquantity);
//                        itemView.getContext().startActivity(intent);
//
//                    }
//
//                }
//            });




        }
    }


}
