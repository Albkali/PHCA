package com.example.masood.phca;
import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import com.example.masood.phca.ui.profile.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText txtEmail1;
    EditText txtPassword1;
    EditText txtLastName;
    EditText txtMotherName;
    EditText txtPhone;
    EditText txtFirstName;
    Child child;
    ImageView ImgUserPhoto;
    Uri pickedImgUri ;
    FirebaseUser currentUser ;

    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
   StorageTask uploadTask ;
   public  Uri imguri ;



    DatePickerDialog datePicker;
    Calendar calendar;
    int day;
    int month;
    int year;
    String BDate;
    EditText txtBirthday,txtHeight  ,txtWeight;
    Button DoneRegister;
    RadioGroup mGender;
    RadioButton mGenderOptions ;
    CheckBox SpecialNeeds ;
    FirebaseUser user;
    int Heightnumber,Weightnumber;

    private static final int PReqCode = 2 ;
    private static final int REQUESCODE = 2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        txtEmail1 = (EditText) findViewById(R.id.editTextEmail);
        txtPassword1 = (EditText) findViewById(R.id.editTextPassword);
        txtFirstName = (EditText) findViewById(R.id.editTextFirsttName);
        txtLastName = (EditText) findViewById(R.id.editTextLastName);
        txtMotherName = (EditText) findViewById(R.id.editTextMatherName);
        txtPhone = (EditText) findViewById(R.id.editTextPhoneNum);
        child = new Child();

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        db = FirebaseFirestore.getInstance();
        txtBirthday = (EditText) findViewById(R.id.editTextBabeBirthday);
        txtHeight = (EditText) findViewById(R.id.editTextHeight);
        txtWeight = (EditText) findViewById(R.id.editTextWeight);
        mGender = (RadioGroup) findViewById(R.id.m_Gender);
        SpecialNeeds = (CheckBox) findViewById(R.id.checkboxsn);

        DoneRegister = (Button) findViewById(R.id.btn_register);


        DoneRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Register.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try {

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

                    user = FirebaseAuth.getInstance().getCurrentUser();
                    String id = user.getUid();

//                    StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
//                    final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
//                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            // image uploaded succesfully
//                            // now we can get our image url
//
//                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//
//                                    // uri contain user image url
//
//
//                                    UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
//                                .setDisplayName(txtFirstName +""+ txtLastName)
//                                            .setPhotoUri(uri)
//                                            .build();
//
//
//                                    currentUser.updateProfile(profleUpdate)
//                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//
//                                                    if (task.isSuccessful()) {
//                                                        // user info updated successfully
////                                            showMessage("Register Complete");
////                                            updateUI();
//                                                    }
//
//                                                }
//                                            });
//
//                                }
//                            });
//
//
//
//
//
//                        }
//                    });

                    child = new Child();
                    Spinner spinner = (Spinner) findViewById(R.id.spinnerBloodType);
                    String typeofblood = spinner.getSelectedItem().toString();

                    Heightnumber = Integer.parseInt(txtHeight.getText().toString());
                    Weightnumber = Integer.parseInt(txtWeight.getText().toString());


                    String ChildFirstName = txtFirstName.getText().toString();
                    child.setChildName(ChildFirstName);


                    String ChildLastName = txtLastName.getText().toString();
                    child.setChildLastName(ChildLastName);


                    String ChildMotherName = txtMotherName.getText().toString();
                    child.setChildMotherName(ChildMotherName);


                    String Phone = txtPhone.getText().toString();
                    child.setPhone(Phone);

                    child.setPassword(password);
                    child.setEmail(email);
                    child.setUid(id);

                    child.setBirthday(DateUtil.getDateFromString(BDate + " 00:00"));


                    child.setBlood(typeofblood);

                    final String strGender =
                            ((RadioButton) findViewById(mGender.getCheckedRadioButtonId()))
                                    .getText().toString();
                    child.setGender(strGender);

                    mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            mGenderOptions = mGender.findViewById(i);

                            switch (i) {
                                case R.id.r_Male:
//                                strGender  = mGenderOptions.getText().toString();
//                                child.setGender(strGender);
                                    break;

                                case R.id.r_Fmale:
//                                strGender  = mGenderOptions.getText().toString();
//                                child.setGender(strGender);

                                    break;

                                default:


                            }
                        }
                    });



                    Map<String, Object> userh = new HashMap<>();
                    userh.put("weight", Weightnumber);
                    userh.put("height", Heightnumber);


                    db.collection("child").document(id).set(child);

                    LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

                    db.collection("child").document(id).collection("IBM").document(id).set(userh);

                    if (SpecialNeeds.isChecked()) {
                        Map<String, Object> SpecialNeeds = new HashMap<>();
                        SpecialNeeds.put("status", "Yes");
                        SpecialNeeds.put("type", null);

                        db.collection("child").document(id)
                                .collection("Special Needs").document(id).set(SpecialNeeds);
                    } else {
//                    db.collection("child").document(id)
//                            .collection("Special Needs").document(id).delete();
                    }

                    Toast.makeText(Register.this, "data inserted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this, MyDrawer.class);
                    startActivity(intent);
                    finish();



                } catch (Exception e) {
                    Toast.makeText(Register.this, "Wrong input, please retry", Toast.LENGTH_SHORT).show();

                }
            }
        });


//        ImgUserPhoto = findViewById(R.id.user_profile_photo) ;
//
//        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (Build.VERSION.SDK_INT >= 22) {
//
//                    checkAndRequestForPermission();
//
//
//                }
//                else
//                {
//                   openGallery();
//                }
//
//
//
//            }
//        });

    }
//    private void updateUserInfo( Uri pickedImgUri, final FirebaseUser currentUser) {
//
//        // first we need to upload user photo to firebase storage and get url
//
//
//
//
//
//    }
//
//    private void openGallery() {
//        //TODO: open gallery intent and wait for user to pick an image !
//
//        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        galleryIntent.setType("image/*");
//        startActivityForResult(galleryIntent,REQUESCODE);
//    }
//
//    private void checkAndRequestForPermission() {
//
//
//        if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//                Toast.makeText(Register.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//
//            }
//
//            else
//            {
//                ActivityCompat.requestPermissions(Register.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        PReqCode);
//            }
//
//        }
//        else
//            openGallery();
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {
//
//            // the user has successfully picked an image
//            // we need to save its reference to a Uri variable
//            pickedImgUri = data.getData();
//            ImgUserPhoto.setImageURI(pickedImgUri);
//
//        }
//        }
        public void setDate(View view) {
            txtBirthday = (EditText) view;

            datePicker = new DatePickerDialog(Register.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            int et_id = txtBirthday.getId();
                            String date = (dayOfMonth) + "-" + (monthOfYear) + "-" + (year);

                            if(et_id == R.id.editTextBabeBirthday) {
                                BDate = date;
                            }


                            ProfileFragment fragment = new ProfileFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("birthDate", BDate);
                            fragment.setArguments(bundle);

                            txtBirthday.setText(date);
                        }
                    }, year, month, day);
            datePicker.show();

        }
        public void ClickBackToLogin2(View view) {
            Intent intent = new Intent(Register.this, Login_form.class);
            startActivity(intent);
        }



    public void ClickBackToLogin(View view)
    {
        Intent intent = new Intent( this, Login_form.class);
        startActivity(intent);
    }
}
