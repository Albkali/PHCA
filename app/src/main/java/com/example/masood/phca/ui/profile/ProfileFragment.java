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
    TextView txtViewname;
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
        txtViewEmail = (TextView) v.findViewById(R.id.txtViewEmail);





        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        final String id = userID.getProviderId();
        if (userID != null) {
            // Name, email address, and profile photo Url


            String name = userID.getDisplayName();
            String email = userID.getEmail();


            txtViewname.setText(name);
            txtViewEmail.setText(" Email :  " + email);

            // Check if user's email is verified
            boolean emailVerified = userID.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = userID.getUid();
        }
    }
}









