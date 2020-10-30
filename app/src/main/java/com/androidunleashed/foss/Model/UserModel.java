package com.androidunleashed.foss.Model;

public class UserModel {

    private String ItemName,cName,cLoc,cPhone,ItemQ;

    public UserModel(String itemName, String cName, String cLoc, String cPhone, String itemQ) {
        ItemName = itemName;
        this.cName = cName;
        this.cLoc = cLoc;
        this.cPhone = cPhone;
        ItemQ = itemQ;
    }

    public UserModel() {

        //
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcLoc() {
        return cLoc;
    }

    public void setcLoc(String cLoc) {
        this.cLoc = cLoc;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getItemQ() {
        return ItemQ;
    }

    public void setItemQ(String itemQ) {
        ItemQ = itemQ;
    }


}
