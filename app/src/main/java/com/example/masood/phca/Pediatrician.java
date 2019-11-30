package com.example.masood.phca;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.masood.phca.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
                            Toast.makeText(Pediatrician.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Pediatrician.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    public void loadNote(View view) {

    }

}