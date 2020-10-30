package com.androidunleashed.foss.Model;

public class CartModel {

    private String ItemName;
    private String ItemQ,ItemPrice;


    public CartModel(String itemName, String itemQ, String itemPrice) {
        ItemName = itemName;
        ItemQ = itemQ;
        ItemPrice = itemPrice;
    }



    public CartModel() {

        //empty
    }


    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemQ() {
        return ItemQ;
    }

    public void setItemQ(String itemQ) {
        ItemQ = itemQ;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }


}
