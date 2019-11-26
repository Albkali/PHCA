package com.example.masood.phca;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Child {

    private static FirebaseFirestore db;
=======
import android.text.format.DateFormat;

public class Child {

private  String ChildName , ChildLastName,password,Email ,childMotherName;
    private Integer Phone , Height ,Weight,Birthday ;
>>>>>>> af91993625362e162d809184145891fe96a7e2ed

    public Integer getBirthday() {
        return Birthday;
    }

<<<<<<< HEAD
    public static FirebaseAuth firebaseAuth = null;
    public static FirebaseUser firebaseUser = null;

    public static String username = "";
    public static String useremail = "";
=======
    public void setBirthday(Integer birthday) {
        Birthday = birthday;
    }

    public Integer getPhone() {
        return Phone;
    }
>>>>>>> af91993625362e162d809184145891fe96a7e2ed

    public void setPhone(Integer phone) {
        Phone = phone;
    }

<<<<<<< HEAD
private  String ChildName , ChildLastName,password,Email;
=======
    public Integer getHeight() {
        return Height;
    }
>>>>>>> af91993625362e162d809184145891fe96a7e2ed

    public void setHeight(Integer height) {
        Height = height;
    }

<<<<<<< HEAD
    public static void firebaseAuthInit() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public Child(){
=======
    public Integer getWeight() {
        return Weight;
    }
>>>>>>> af91993625362e162d809184145891fe96a7e2ed

    public void setWeight(Integer weight) {
        Weight = weight;
    }

<<<<<<< HEAD
    public Child(String childName, String childLastName, String password, String email) {
=======




    public Child(String childName, String childLastName, String password, String email, String childMotherName,
                 Integer phone,Integer height, Integer weight, Integer birthday) {
>>>>>>> af91993625362e162d809184145891fe96a7e2ed
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

<<<<<<< HEAD
    public static void getUserData(final Runnable then) {
        db = FirebaseFirestore.getInstance();

        // Get user name and set greetings
        db.collection("child")
                .document(Child.firebaseUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            String name = task.getResult().get("Fname").toString();
                            Child.username = name;
                            // After Got data
                            then.run();
                        } else {
                            Log.w(this.toString(), "Error getting documents", task.getException());
                        }
                    }
                });
=======
    public Child() {

    }
>>>>>>> af91993625362e162d809184145891fe96a7e2ed

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




}
