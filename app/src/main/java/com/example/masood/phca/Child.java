package com.example.masood.phca;

import android.text.format.DateFormat;

public class Child {

private  String ChildName , ChildLastName,password,Email ,childMotherName , Phone ;
    private Integer  Height ,Weight,Birthday ;

    public Integer getBirthday() {
        return Birthday;
    }

    public void setBirthday(Integer birthday) {
        Birthday = birthday;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone( String phone) {
        Phone = phone;
    }

//    public Integer getHeight() {
//        return Height;
//    }

//    public void setHeight(Integer height) {
//        Height = height;
//    }
//
//    public Integer getWeight() {
//        return Weight;
//    }
//
//    public void setWeight(Integer weight) {
//        Weight = weight;
//    }





    public Child(String childName, String childLastName, String password, String email, String childMotherName,
                 String phone,Integer height, Integer weight, Integer birthday) {
        ChildName = childName;
        ChildLastName = childLastName;
        this.password = password;
        Email = email;
        this.childMotherName = childMotherName;
        Phone = phone;
        Height = height;
        Weight = weight;
        Birthday = birthday;
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

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }

    public String getChildMotherName() {
        return childMotherName;
    }

    public void setChildMotherName(String childMotherName) {
        this.childMotherName = childMotherName;
    }




}
