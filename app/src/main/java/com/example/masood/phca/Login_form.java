package com.example.masood.phca;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;

public class Login_form extends AppCompatActivity {

   private EditText txtEmail,txtPassword;
    private Button btn_login,btn_register;

    FirebaseUser user;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            String id = user.getEmail();
            if(id.equals("moh@gmail.com")||id.equals("yasser2@gmail.com")){
                Intent groceryItemsIntent = new Intent(Login_form.this,
                        MainActivity.class);
                startActivity(groceryItemsIntent);
                finish();            } else {
                Intent groceryItemsIntent = new Intent(Login_form.this,
                        MyDrawer.class);
                startActivity(groceryItemsIntent);
                finish();            }

        }
        txtEmail = (EditText)findViewById(R.id.etName);
        txtPassword = (EditText)findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.login_button);
        btn_register = (Button)findViewById(R.id.toRegister_button);

//        if (firebaseAuth.getCurrentUser() != null) {
//            Intent Intent = new Intent(Login_form.this,
//                    MyDrawer.class);
//            startActivity(Intent);
//            finish();
//        }
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                /*firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                } else {
                                    Log.i("PROBEM","Failed");
                                }

                                // ...
                            }
                        });*/


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = FirebaseAuth.getInstance().getCurrentUser();
                                    String id = user.getEmail();
                                    if(id.equals("moh@gmail.com")||id.equals("yasser2@gmail.com")){
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                                        to show more optione to do condtions of type users , check this link
//     https://stackoverflow.com/questions/50534695/how-to-log-in-two-different-types-of-users-to-different-activities-automatically
                                    } else {
                                        startActivity(new Intent(getApplicationContext(),MyDrawer.class));
                                    }

                                } else {
                                    //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    Toast.makeText(Login_form.this,"Login Failed ", Toast.LENGTH_SHORT);
                                }

                                // ...
                            }
                        });

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));

            }
        });
    }


}
