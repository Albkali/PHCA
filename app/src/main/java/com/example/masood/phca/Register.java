package com.example.masood.phca;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText txtEmail,txtPassword,FName,LName,MoName,Phone;
    Button  btn_next;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FName = (EditText)findViewById(R.id.editTextFirsttName);
        LName = (EditText)findViewById(R.id.editTextLastName);
        MoName = (EditText)findViewById(R.id.editTextMatherName);
        Phone = (EditText) findViewById(R.id.editTextPhoneNum);
        btn_next= (Button)findViewById(R.id.nextregister2);

        firebaseAuth = FirebaseAuth.getInstance();



        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                final String FirstName = FName.getText().toString().trim();
                final String LastName = LName.getText().toString().trim();
                final String MotherName = MoName.getText().toString().trim();
                final String PhoneNo = Phone.getText().toString().trim();


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                      .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   Map<String,Object> userData = new HashMap<>();
                                   userData.put("Email", email);
                                   userData.put("FName", FirstName );
                                   userData.put("LName", LastName);
                                   userData.put("MName", MotherName );
                                   userData.put("Phone", PhoneNo );


                                   FirebaseFirestore db = FirebaseFirestore.getInstance();
                                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                   String id = user.getUid();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    Log.i("PROBEM","Failed");
                                }

                                // ...
                            }
                        });
            }
        });
    }


    public void ClickToRegister1(View view)
    {
        Intent intent = new Intent( this, activity_Register1.class);
        startActivity(intent);
    }

    public void ClickBackToLogin(View view)
    {
        Intent intent = new Intent( this, Login_form.class);
        startActivity(intent);
    }



}
