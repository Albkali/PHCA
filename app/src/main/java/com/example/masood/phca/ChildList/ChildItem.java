package com.example.masood.phca.ChildList;

import com.google.firebase.firestore.Exclude;

public class ChildItem {
    @Exclude
    private String childName;
    @Exclude
    private String ChildLastName;
    @Exclude
    private String photoUrl;


    public ChildItem() {
        this.childName = childName;
        this.ChildLastName = ChildLastName;
        this.photoUrl = photoUrl;
    }


    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildLastName() {
        return ChildLastName;
    }

    public void setChildLastName(String childLastName) {
        ChildLastName = childLastName;
    }

    public String getphotoUrl() {
        return photoUrl;
    }

    public void setphotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

