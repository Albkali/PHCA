package com.example.masood.phca;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Register extends AppCompatActivity {


    EditText txtEmail1;
    EditText txtPassword1;
    EditText txtLastName;
    EditText txtMotherName;
    EditText txtPhone;
    EditText txtFirstName ;

    Button btn_next;
    Child child;
    //DatabaseReference reff;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db ;
//    public List<Child> groceryItems;
    Context context;

//    public Register(Context con) {
//        this.context = con;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        btn_next= (Button)findViewById(R.id.nextregister2);
        txtEmail1=(EditText)findViewById(R.id.editTextEmail);
        txtPassword1=(EditText)findViewById(R.id.editTextPassword);
        txtFirstName=(EditText)findViewById(R.id.editTextFirsttName);
        txtLastName=(EditText)findViewById(R.id.editTextLastName);
        txtMotherName=(EditText)findViewById(R.id.editTextMatherName);
        txtPhone=(EditText) findViewById(R.id.editTextPhoneNum);


        child = new Child();
       // reff = FirebaseDatabase.getInstance().getReference().child("Child");

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int phonenumber = Integer.parseInt(txtPhone.getText().toString());
//
//               child.setChildName(txtFirstName.getText().toString());
//               child.setChildLastName(txtLastName.getText().toString());
//                child.setChildMotherName(txtMotherName.getText().toString());
//                child.setEmail(txtEmail1.getText().toString().trim());
//                child.setPassword(txtPassword1.getText().toString().trim());
//                child.setPhone(phonenumber);

                //  Map<String, Object> child = new HashMap<>();
               // child.put("FirstName", txtFirstName.getText().toString());
               // child.put("LastName", txtLastName.getText().toString());
               // child.put("MatherName", txtMotherName.getText().toString());
               // child.put("Email", txtEmail1.getText().toString());
               // child.put("Password", txtPassword1.getText().toString());
               // child.put("NPhone", txtPhone.getText().toString());
                //child.put("Birthday", txtBirthday.getText().toString());
               // child.put("Height", txtHeight.getText().toString());
               // child.put("Weight", txtWeight.getText().toString());


                String email = txtEmail1.getText().toString().trim();
                String password = txtPassword1.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                  .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                     } else {
                         Toast.makeText(Register.this, "Email or password not correct", Toast.LENGTH_LONG).show();

                     }

                 }
                 });
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                 String id = user.getUid();
//                db.collection("child").document(id).set(child);


//                child.setChildName(txtFirstName.getText().toString());
//                child.setChildLastName(txtLastName.getText().toString());
//                child.setChildMotherName(txtMotherName.getText().toString());
//                child.setEmail(txtEmail1.getText().toString().trim());
//                child.setPhone(phonenumber);


                                Toast.makeText(Register.this, "data inserted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Register.this, activity_Register1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("ChildFirstName",  txtFirstName.getText().toString());
                intent.putExtra("ChildLastName",  txtLastName.getText().toString());
                intent.putExtra("ChildMotherName",  txtMotherName.getText().toString());
                intent.putExtra("Phone", txtPhone.getText().toString());

                startActivity(intent);


            }
            //reff.push().setValue(child);
            // Toast.makeText(Register.this,"data inserted",Toast.LENGTH_LONG).show();


            //}
            // });

            //btn_register.setOnClickListener(new View.OnClickListener() {

            // @Override
            // public void onClick(View view) {

            //String email = txtEmail.getText().toString().trim();
            // String password = txtPassword.getText().toString().trim();

            //  firebaseAuth.createUserWithEmailAndPassword(email, password)
            //    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            //  @Override
            //  public void onComplete(@NonNull Task<AuthResult> task) {
            //     if (task.isSuccessful()) {
            //         startActivity(new Intent(getApplicationContext(),MyDrawer.class));
            //      } else {
            //          Log.i("PROBEM","Failed");
            //    }

            // ...
            //  }
            // });
            //  }
            //});

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
