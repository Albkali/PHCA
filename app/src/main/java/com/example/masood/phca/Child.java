package com.example.masood.phca;

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


    public static FirebaseAuth firebaseAuth = null;
    public static FirebaseUser firebaseUser = null;

    public static String username = "";
    public static String useremail = "";


private  String ChildName , ChildLastName,password,Email;


    public static void firebaseAuthInit() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public Child(){

    }

    public Child(String childName, String childLastName, String password, String email) {
        ChildName = childName;
        ChildLastName = childLastName;
        this.password = password;
        Email = email;
    }

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
