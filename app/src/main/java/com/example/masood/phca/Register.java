package com.example.masood.phca;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button btn_register;
    Button  btn_next;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btn_register= (Button)findViewById(R.id.nextregister2);
        firebaseAuth = FirebaseAuth.getInstance();



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
