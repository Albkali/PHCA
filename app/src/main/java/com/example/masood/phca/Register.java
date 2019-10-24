package com.example.masood.phca;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText txtEmail,txtPassword,txtLastName,txtMotherName,txtPhone,txtFirstName;
    Button btn_next;
    Child child;
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btn_next= (Button)findViewById(R.id.nextregister2);
        txtEmail=(EditText)findViewById(R.id.editTextFirsttName);
        txtPassword=(EditText)findViewById(R.id.editTextPassword);
        txtFirstName=(EditText)findViewById(R.id.editTextFirsttName);
        txtLastName=(EditText)findViewById(R.id.editTextLastName);
        txtMotherName=(EditText)findViewById(R.id.editTextMatherName);
        txtPhone=(EditText)findViewById(R.id.editTextPhoneNum);

        child = new Child();
        reff = FirebaseDatabase.getInstance().getReference().child("child");

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child.setChildName(txtFirstName.getText().toString().trim());
                child.setChildLastName(txtLastName.getText().toString().trim());
                child.setChildMotherName(txtMotherName.getText().toString().trim());
                child.setEmail(txtEmail.getText().toString().trim());
                child.setPassword(txtPassword.getText().toString().trim());
                //Number = (txtFirstName.getText().toString().trim());

                reff.push().setValue(child);
                Toast.makeText(Register.this,"data inserted",Toast.LENGTH_LONG).show();


            }
        });

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
