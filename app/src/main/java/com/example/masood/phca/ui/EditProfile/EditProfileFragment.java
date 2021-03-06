package com.example.masood.phca.ui.EditProfile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.masood.phca.Child;
import com.example.masood.phca.DateUtil;
import com.example.masood.phca.MyDrawer;
import com.example.masood.phca.R;
import com.example.masood.phca.Register;
import com.example.masood.phca.ui.profile.ProfileFragment;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.masood.phca.R.id.r_Male_UpdateProfil;

public class EditProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    FirebaseUser user;

    private FirebaseFirestore mDatabase;


    Child child;
    FirebaseFirestore db;

    DatePickerDialog datePicker;
    java.util.Calendar calendar;
    int day;
    int month;
    int year;
    String BDate;

    private EditText txtEditFname,txtEditLname, txtEditchildPhone, txtEditmotherName,
            txtEditAge, txtEditHeight, txtEditWeight;
    private EditText txtEditEmail,txtEditPassword;
    private ImageView imgprofile ;
    RadioGroup RG_Gender;
    RadioButton RG_Male;
    RadioButton RG_Female;
    RadioButton mGenderOptions ;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    CheckBox Special_Needs ;
    Spinner spinner;
    Button btn_update_profile ;
    private FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        child = new Child();
        calendar = java.util.Calendar.getInstance();
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        month = calendar.get(java.util.Calendar.MONTH);
        year = calendar.get(java.util.Calendar.YEAR);
        Activity activity = getActivity();

        db = FirebaseFirestore.getInstance();
        final View v = inflater.inflate(R.layout.fragment_editprofile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        final StorageReference storageReference = firebaseStorage.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseFirestore.getInstance();

        txtEditFname = (EditText) v.findViewById(R.id.editTextFirsttName_UpdateProfil);
        txtEditLname = (EditText) v.findViewById(R.id.editTextLastName_UpdateProfil);
        txtEditEmail = (EditText) v.findViewById(R.id.editTextEmail_UpdateProfil);
        txtEditPassword = (EditText) v.findViewById(R.id.editTextPassword_UpdateProfil);
        RG_Gender = (RadioGroup) v.findViewById(R.id.m_Gender_UpdateProfil);
        RG_Male = (RadioButton) v.findViewById(r_Male_UpdateProfil);
        RG_Female = (RadioButton) v.findViewById(R.id.r_Fmale_UpdateProfil);
        spinner = (Spinner) v.findViewById(R.id.spinnerBloodType_UpdateProfil);
        Special_Needs = (CheckBox) v.findViewById(R.id.checkboxsn_UpdateProfil);
        txtEditchildPhone = (EditText) v.findViewById(R.id.editTextPhoneNum_UpdateProfil);
        txtEditmotherName = (EditText) v.findViewById(R.id.editTextMatherName_UpdateProfil);

        txtEditAge = (EditText) v.findViewById(R.id.editTextBabeBirthday_UpdateProfil);
        txtEditHeight = (EditText) v.findViewById(R.id.editTextHeight_UpdateProfil);
        txtEditWeight = (EditText) v.findViewById(R.id.editTextWeight_UpdateProfil);
        imgprofile = (ImageView) v.findViewById(R.id.user_profile_photo_UpdateProfil);
        btn_update_profile = (Button) v.findViewById(R.id.btn__UpdateProfil);
        user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        storageReference.child("ProfileImage").child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).fit().centerInside().into(imgprofile);
            }
        });

        txtEditAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditAge = (EditText) v;

                datePicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                int et_id = txtEditAge.getId();
                                String date = (dayOfMonth) + "-" + (monthOfYear) + "-" + (year);



                                if(et_id == R.id.editTextBabeBirthday_UpdateProfil) {
                                    BDate = date;
                                }


                                ProfileFragment fragment = new ProfileFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("birthDate", BDate);
                                fragment.setArguments(bundle);

                                txtEditAge.setText(date);
                            }
                        }, year, month, day);

                //limit the birth date picker
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(cal.getTime());
                cal.add(java.util.Calendar.YEAR, -5);
                datePicker.getDatePicker().setMinDate( cal.getTimeInMillis());
                cal.setTime(cal.getTime());
                cal.add(Calendar.YEAR, 5);

                datePicker.getDatePicker().setMaxDate( cal.getTimeInMillis());
                datePicker.show();            }

        });
        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent();
                profileIntent.setType("image/*");
                profileIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(profileIntent, "Select Image."), PICK_IMAGE);
            }
        });

        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            final String id = userID.getUid();

            @Override
            public void onClick(final View v) {

                String ChildFirstName = txtEditFname.getText().toString();
                String ChildLastName = txtEditLname.getText().toString();
                String Childmothername = txtEditmotherName.getText().toString();
                String Childemail = txtEditEmail.getText().toString();
                String phone = txtEditchildPhone.getText().toString();
                String Childpassword = txtEditPassword.getText().toString();
                String typeofbloodtype = spinner.getSelectedItem().toString();
                int Heightnumber = Integer.parseInt(txtEditHeight.getText().toString());
                int Weightnumber = Integer.parseInt(txtEditWeight.getText().toString());
                String strGender= "";
                if (RG_Male.isChecked()) {
                    strGender = getString(R.string.male);
                } else if (RG_Female.isChecked()) {
                    strGender = getString(R.string.female);;
                }
                if (Special_Needs.isChecked()) {
                    Map<String, Object> SpecialNeeds = new HashMap<>();
                    SpecialNeeds.put("status", "Yes");
                    SpecialNeeds.put("type", null);

                    db.collection("child").document(id)
                            .collection("Special Needs").document(id).set(SpecialNeeds);
                } else {
                    db.collection("child").document(id)
                            .collection("Special Needs").document(id).delete();
                }
                updateDocument(ChildFirstName, ChildLastName,Childmothername,Childemail,phone,
                        Childpassword,typeofbloodtype, strGender,Heightnumber,Weightnumber);

                user.updatePassword(Childpassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User password updated.");
                                }
                            }
                        });

                user = FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();

                StorageReference imageReference = storageReference.child("ProfileImage").child(id); //User id/Images/Profile Pic.jpg
                UploadTask uploadTask = imageReference.putFile(imagePath);


//                    user.updateEmail(Childemail)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Log.d(TAG, "User Email updated.");
//                                    }
//                                }
//                            });
            }
        });

        return v;



    }
    private void updateDocument(String Fname, String Lname,String Mohtername,String email,String str_phone,
                                String password,String blood,String str_Gender,int int_height, int int_weight){
        final String id = userID.getUid();
        DocumentReference documentReference = mDatabase.collection("child").document(id);
//        DocumentReference documentReference2 = mDatabase.collection("child").document(id).collection("IBM").document(id);
        documentReference.update("childName", Fname);
        documentReference.update("childLastName", Lname);
        documentReference.update("childMotherName", Mohtername);
        documentReference.update("email", email);
        documentReference.update("phone", str_phone);
        documentReference.update("password", password);
        documentReference.update("blood", blood);
        documentReference.update("gender", str_Gender);
        documentReference.update("birthDate", DateUtil.getDateFromString(BDate + " 00:00"))


//        documentReference.update("gender", gender)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), R.string.toast_profileupdated,Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("Androidview", e.getMessage());
                    }
                });


        Map<String, Object> userh = new HashMap<>();
        userh.put("weight", int_weight);
        userh.put("height", int_height);


        db.collection("child").document(id).collection("IBM").document(id).set(userh);
    }
    @Override
    public void onResume() {
        super.onResume();
        final String id = userID.getUid();
        final String Email = userID.getEmail();
        txtEditEmail.setText(Email);
        Log.i("my id", id);
//
//                if (userID != null) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference noteRef =
                db.collection("child")
                        .document(id);
        DocumentReference noteRef2 =
                db.collection("child")
                        .document(id)
                        .collection("IBM")
                        .document(id);
        DocumentReference noteRef3 =
                db.collection("child")
                        .document(id)
                        .collection("Special Needs")
                        .document(id);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // String h = documentSnapshot.getLong("weight") + "" ;
                            String name = documentSnapshot.getString("childName");
                            String lastname = documentSnapshot.getString("childLastName");

                            String childmothername = documentSnapshot.getString("childMotherName");
                            String chlidboodtype = documentSnapshot.getString("blood");
                            String childgender = documentSnapshot.getString("gender");
                            String childPhone = documentSnapshot.getString("phone");
                            String password = documentSnapshot.getString("password");

//                                        String childage = documentSnapshot.getLong("birthday") + "";
                            Date datetest = documentSnapshot.getTimestamp("birthday").toDate();
                            SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
                            String bdate = sfd.format(datetest);

//                                        String tt = datetest.toString();

                            if (childgender.equals(getString(R.string.male))){
                                RG_Male.setChecked(true);
                            } if (childgender.equals(getString(R.string.female))) {
                                RG_Female.setChecked(true);
                            }
                            txtEditFname.setText(name);
                            txtEditLname.setText(lastname);
                            txtEditchildPhone.setText(childPhone);
                            txtEditmotherName.setText(childmothername);
                            txtEditPassword.setText(password);
                            txtEditAge.setText(bdate);
                            spinner.setSelection(getIndex(spinner, chlidboodtype));

                        } else {
//                                Toast.makeText(ProfileFragment.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        noteRef2.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String childWeight = documentSnapshot.getLong("weight") + "";
                            String childHeight = documentSnapshot.getLong("height") + "";
                            txtEditWeight.setText(childWeight);
                            txtEditHeight.setText(childHeight);
                            // Map<String, Object> note = documentSnapshot.getData();

                        } else {
//                                Toast.makeText(ProfileFragment.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//            storageReference.child("ProfileImage").child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//
//                    Picasso.get().load(uri).fit().centerInside().into(imgprofile);
//                }
//            });

//                    db.collection("child")
//                            .document(id)
//                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                        @RequiresApi(api = Build.VERSION_CODES.O)
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                            if(task.isSuccessful()){
//                                DocumentSnapshot document = task.getResult();
//                                Date datetest = document.getTimestamp("birthday").toDate();
//
//                                Calendar calander = Calendar.getInstance();
//                                calander.setTime(datetest);
//                                int userBirthDay = calander.get(Calendar.DAY_OF_MONTH);
//                                int userBirthMonth = calander.get(Calendar.MONTH);
//                                int userBirthYear = calander.get(Calendar.YEAR);
//
//                                LocalDate userBirthDate = LocalDate.of(userBirthYear, userBirthMonth,userBirthDay );
//                                LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
//                                final Period p = Period.between(userBirthDate, currentDate);
//
//                                String y = p.getYears() + "y" + (p.getYears() > 1 ? "s " : " ");
//                                String m = (p.getMonths()-1) + "m" + (p.getMonths() > 1 ? "s" : " and ");
//                                String d = (p.getDays()+1) + "d" + (p.getDays() > 1 ? "s.\n" : ".\n");
//                                txtEditAge.setText(y+m+d);
//                            }
//                        }
//                    });

        noteRef3.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String sepecialNeedstr = documentSnapshot.getString("status");
                            if (sepecialNeedstr.equals("Yes") ){
                                Special_Needs.setChecked(true);
                            }
                            else
                            {
                                Special_Needs.setChecked(false);
//                                            db.collection("child").document(id)
//                                                    .collection("Special Needs").document(id).delete();
                            }

                        } else {
//                                Toast.makeText(ProfileFragment.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
//            String name = userID.getDisplayName();
        String email = userID.getEmail();
        // Check if user's email is verified
        boolean emailVerified = userID.isEmailVerified();
        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getIdToken() instead.
        String uid = userID.getUid();
    }
//        }

    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), imagePath);
                imgprofile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}