package com.example.masood.phca.ChildList;

public class ChildItem {
    private String childName;
    private String ChildLastName;
    private int phone;

    public ChildItem() {
        //empty constructor needed
    }

    public ChildItem(String childName, String childLastName, int phone) {
        this.childName = childName;
        ChildLastName = childLastName;
        this.phone = phone;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
