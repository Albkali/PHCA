package com.example.masood.phca;

public class Child {

private  String ChildName , ChildLastName,password,Email ,childMotherName;
    private Number Phone;

    ;


    public Child(String childName, String childLastName, String password, String email, String childMotherName, Number phone) {
        ChildName = childName;
        ChildLastName = childLastName;
        this.password = password;
        Email = email;
        this.childMotherName = childMotherName;
        Phone = phone;
    }

    public Child() {

    }

    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public String getChildLastName() {
        return ChildLastName;
    }

    public void setChildLastName(String childLastName) {
        ChildLastName = childLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getChildMotherName() {
        return childMotherName;
    }

    public void setChildMotherName(String childMotherName) {
        this.childMotherName = childMotherName;
    }

    public Number getPhone() {
        return Phone;
    }

    public void setPhone(Number phone) {
        Phone = phone;
    }
}
