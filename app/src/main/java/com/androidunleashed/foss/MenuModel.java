package com.androidunleashed.foss;

public class MenuModel {

    private String ItemName;
    private String ItemQ,ItemPrice;


    public MenuModel(String itemName, String itemQ, String itemPrice) {
        ItemName = itemName;
        ItemQ = itemQ;
        ItemPrice = itemPrice;
    }



    public MenuModel() {

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
