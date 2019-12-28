package com.example.masood.phca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Pediatrician extends AppCompatActivity {

    private  String PedName , PedEmail;
    Button btnsetped;

    Pediatrician pediatrician;

    public Pediatrician(String pedName, String pedEmail) {
        PedName = pedName;
        PedEmail = pedEmail;
    }

    public void setPedName(String pedName) {
        PedName = pedName;
    }

    public String getPedName() {
        return PedName;
    }

    public String getPedEmail() {
        return PedEmail;
    }

    public void setPedEmail(String pedEmail) {
        PedEmail = pedEmail;
    }

    public Pediatrician() {

    }
    private static final String TAG = "MainActivity";

    private TextView PedName1 , PedEmail1 ,PedPhone1;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef = db.document("Pediatrician/100");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pediatrician);

        getSupportActionBar().setTitle(getString(R.string.Pediatrician));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        pediatrician = new Pediatrician();

        btnsetped= (Button)findViewById(R.id.sbtnsetped);

        PedName1 =findViewById(R.id.txtViewPedName);
        PedEmail1 =findViewById(R.id.txtViewPedEmail);
        PedPhone1 =findViewById(R.id.txtViewPedPhone);


    }


    @Override
    protected void onResume() {
        super.onResume();
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String PedName = documentSnapshot.getString("FirstName");
                            String PedEmail = documentSnapshot.getString("PedEmail");
                            String PedLastName = documentSnapshot.getString("LastName");
                            String PedPhone = documentSnapshot.getString("PedPhone");



                            // Map<String, Object> note = documentSnapshot.getData();

                            PedName1.setText( PedName + " " + PedLastName);
                            PedEmail1.setText(PedEmail);
                            PedPhone1.setText(PedPhone);


                        } else {
                            Toast.makeText(Pediatrician.this, R.string.information_not_exist, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Pediatrician.this, R.string.error, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }


    public void loadNote(View view) {

        Intent intent = new Intent(Pediatrician.this, Chatting_Activity.class);
        startActivity(intent);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_logout){
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            Intent intent = new Intent ( this, Login_form.class);
            startActivity(intent);

        }
        else {

            if(item.getItemId() == R.id.action_settings) {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            }
            int id = item.getItemId();

            if (id == android.R.id.home) {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_drawer, menu);
        return true;
    }

}
