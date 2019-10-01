package com.example.masood.phca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText Name,Password;
    private DatabaseReference ref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);

        ref = FirebaseDatabase.getInstance().getReference().child("Login");
    }
    String prName;
    String prPassword;
    Users us = new Users();
    public void bt_button(View view) {

        prName = Name.getText().toString();
         prPassword = Password.getText().toString();

        if(ref.child(prPassword) != null){
        ref.child(prPassword).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                if(prPassword.equals(us.getPassword())){
                    Toast.makeText(MainActivity.this, "Login Succesfull", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    //Intent start = new Intent(MainActivity.this,Start.class);
                    //finish();
                    //startActivity(start);
                }else {
                    Toast.makeText(MainActivity.this, "Enter Valid Name......", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }else {
            Toast.makeText(MainActivity.this, "User dosnt exist.......", Toast.LENGTH_LONG).show();

        }
}}
