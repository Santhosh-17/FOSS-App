package com.androidunleashed.foss.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidunleashed.foss.Check;
import com.androidunleashed.foss.Model.UserModel;
import com.androidunleashed.foss.R;
import com.androidunleashed.foss.UserFiles.confirmOrder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrderAdapter extends FirestoreRecyclerAdapter<UserModel, OrderAdapter.OrderHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderAdapter.OrderHolder OrderHolder, int i, @NonNull UserModel UserModel) {
        OrderHolder.itemName.setText(UserModel.getItemName());
        OrderHolder.itemQuantity.setText(UserModel.getItemQ());
        OrderHolder.cusName.setText(UserModel.getcName());
        OrderHolder.cusLoc.setText(UserModel.getcLoc());
        OrderHolder.cusPhone.setText("Contact: "+UserModel.getcPhone());

    }



    @NonNull
    @Override
    public OrderAdapter.OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders,parent,false);

        return new OrderAdapter.OrderHolder(v);
    }

    class OrderHolder extends RecyclerView.ViewHolder{

        TextView itemName , itemQuantity, cusName,cusLoc,cusPhone;

        public OrderHolder(@NonNull final View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ItemNameViewO);
            itemQuantity = itemView.findViewById(R.id.itemQuantityViewO);
            cusName = itemView.findViewById(R.id.CustomerNames);
            cusLoc = itemView.findViewById(R.id.CustomerLocations);
            cusPhone = itemView.findViewById(R.id.CustomerPhones);


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
