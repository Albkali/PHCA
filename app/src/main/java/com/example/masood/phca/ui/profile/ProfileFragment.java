package com.example.masood.phca.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.masood.phca.Child;
import com.example.masood.phca.Pediatrician;
import com.example.masood.phca.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.support.constraint.Constraints.TAG;

public class ProfileFragment extends Fragment {

    EditText weight, height;
    TextView resulttext;
    String calculation, BMIresult;
    Button calc;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    Button CheckDataUser;
    TextView txtViewname , txtviewchildPhone , txtviewmotherName ,txtviewbloodtype ,
            txtviewchildGender ,txtviewAge, txtviewHeight ,txtviewWeight ;
    TextView txtViewEmail;

    private FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();


        txtViewname = (TextView) v.findViewById(R.id.txtViewFname);

        txtviewchildPhone = (TextView) v.findViewById(R.id.txtViewchildPhone);
        txtviewmotherName = (TextView) v.findViewById(R.id.txtViewmahterName);
        txtviewbloodtype = (TextView) v.findViewById(R.id.txtViewbloodtype);
        txtviewchildGender = (TextView) v.findViewById(R.id.txtViewchildGender);
        txtviewAge = (TextView) v.findViewById(R.id.txtViewAge);
        txtviewHeight = (TextView) v.findViewById(R.id.txtViewHeight);
        txtviewWeight = (TextView) v.findViewById(R.id.txtViewWeight);


        // txtViewEmail = (TextView) v.findViewById(R.id.txtViewEmail);





        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        final String id = userID.getUid();
        Log.i("my id", id);
        if (userID != null) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            DocumentReference noteRef =
                    db.collection("child")
                            .document(id);
//                            .collection("IBM")
//                            .document(id);

            noteRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                               // String h = documentSnapshot.getLong("weight") + "" ;
                                String name = documentSnapshot.getString("childName" );
                                String lastname = documentSnapshot.getString("childLastName" );

                                String childmothername = documentSnapshot.getString("childMotherName" );
                                String chlidboodtype = documentSnapshot.getString("blood" );
                                String childgender = documentSnapshot.getString("gender" );
                                String childPhone = documentSnapshot.getString("phone" );
                                String childage = documentSnapshot.getLong("birthday") + "" ;




                                txtViewname.setText( name + " " + lastname );

                                txtviewmotherName.setText(childmothername);
                                txtviewbloodtype.setText(chlidboodtype);
                                txtviewchildGender.setText(childgender);
                                txtviewchildPhone.setText(childPhone);
                                txtviewAge.setText(childage);







                                // Map<String, Object> note = documentSnapshot.getData();




                            } else {
//                                Toast.makeText(ProfileFragment.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }


//            String name = userID.getDisplayName();
        String email = userID.getEmail();



        // Check if user's email is verified
        boolean emailVerified = userID.isEmailVerified();

        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getIdToken() instead.
        String uid = userID.getUid();
    }
}


