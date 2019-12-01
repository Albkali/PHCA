package com.example.masood.phca;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {

    EditText txtEmail1;
    EditText txtPassword1;
    EditText txtLastName;
    EditText txtMotherName;
    EditText txtPhone;
    EditText txtFirstName ;
    Button btn_next;
    Child child;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db ;

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

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                Toast.makeText(Register.this, "data inserted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Register.this, activity_Register1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("ChildFirstName",  txtFirstName.getText().toString());
                intent.putExtra("ChildLastName",  txtLastName.getText().toString());
                intent.putExtra("ChildMotherName",  txtMotherName.getText().toString());
                intent.putExtra("Phone", txtPhone.getText().toString());
                startActivity(intent);
            }
    });
    }

    public void ClickBackToLogin(View view)
    {
        Intent intent = new Intent( this, Login_form.class);
        startActivity(intent);
    }
}
