package com.example.masood.phca;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class activity_Register1 extends Register {


    EditText txtBirthday,txtHeight  ,txtWeight;


    Button DoneRegister;
    FirebaseFirestore db ;
    Child child;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register1);

        db = FirebaseFirestore.getInstance();

        child = new Child();


        DoneRegister=(Button)findViewById(R.id.btn_register);

        txtBirthday=(EditText)findViewById(R.id.editTextBabeBirthday);
        txtHeight=(EditText)findViewById(R.id.editTextHeight);
        txtWeight=(EditText)findViewById(R.id.editTextWeight);


        DoneRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // child.setChildName(txtFirstName.getText().toString());
                // child.setChildLastName(txtLastName.getText().toString());
                //child.setChildMotherName(txtMotherName.getText().toString());
                //child.setEmail(txtEmail.getText().toString().trim());
                //child.setPassword(txtPassword.getText().toString().trim());
                //Number = (txtFirstName.getText().toString().trim());
                child = new Child();

                //Map<String, Object> childs = new HashMap<>();
                //child.sett("Birthday", txtBirthday.getText().toString());
                //child.put("Height", txtHeight.getText().toString());
                //child.put("Weight", txtWeight.getText().toString());


                int Birthnumber = Integer.parseInt(txtBirthday.getText().toString());
                int Heightnumber = Integer.parseInt(txtHeight.getText().toString());
                int Weightnumber = Integer.parseInt(txtWeight.getText().toString());




                child.setBirthday(Birthnumber);
                child.setHeight(Heightnumber);
                child.setWeight(Weightnumber);




                //child.put("Birthday", txtBirthday.getText().toString());
                // child.put("Height", txtHeight.getText().toString());
                // child.put("Weight", txtWeight.getText().toString());



                db.collection("Child")
                        .add(child)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(activity_Register1.this, "data inserted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(activity_Register1.this, MyDrawer.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity_Register1.this, "data  not inserted", Toast.LENGTH_LONG).show();
                            }
                        });
                }
            });

        }




            public void ClickBackToLogin2(View view) {
                Intent intent = new Intent(activity_Register1.this, Login_form.class);
                startActivity(intent);
            }


        }
