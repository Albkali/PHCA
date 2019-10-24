package com.example.masood.phca;

public class Child {

private  String ChildName , ChildLastName,password,Email;





    public Child(){



    }
    public Child(String childName, String childLastName, String password, String email) {
        ChildName = childName;
        ChildLastName = childLastName;
        this.password = password;
        Email = email;
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
}
