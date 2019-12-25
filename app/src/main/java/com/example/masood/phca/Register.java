package com.example.masood.phca;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText txtEmail1,txtPassword1,txtLastName,txtMotherName,txtPhone,txtFirstName,txtBirthday,txtHeight  ,txtWeight;
    Child child;
    FirebaseUser currentUser ,user;
    FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    DatePickerDialog datePicker;
    Calendar calendar;
    int day,month,year,Heightnumber,Weightnumber;
    String BDate;
    Button DoneRegister;
    RadioGroup mGender;
    RadioButton mGenderOptions ;
    CheckBox SpecialNeeds ;



    private ImageView profileImageView;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        firebaseStorage = FirebaseStorage.getInstance();

        txtEmail1 = (EditText) findViewById(R.id.editTextEmail);
        txtPassword1 = (EditText) findViewById(R.id.editTextPassword);
        txtFirstName = (EditText) findViewById(R.id.editTextFirsttName);
        txtLastName = (EditText) findViewById(R.id.editTextLastName);
        txtMotherName = (EditText) findViewById(R.id.editTextMatherName);
        txtPhone = (EditText) findViewById(R.id.editTextPhoneNum);
        txtBirthday = (EditText) findViewById(R.id.editTextBabeBirthday);
        txtHeight = (EditText) findViewById(R.id.editTextHeight);
        txtWeight = (EditText) findViewById(R.id.editTextWeight);
        mGender = (RadioGroup) findViewById(R.id.m_Gender);
        SpecialNeeds = (CheckBox) findViewById(R.id.checkboxsn);
        DoneRegister = (Button) findViewById(R.id.btn_register);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = firebaseStorage.getReference();

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        profileImageView = findViewById(R.id.user_profile_photo);


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
                                        Toast.makeText(Register.this, R.string.email_password_not_correct, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                    user = FirebaseAuth.getInstance().getCurrentUser();
                    String id = user.getUid();

                    StorageReference imageReference = storageReference.child("ProfileImage").child(id); //User id/Images/Profile Pic.jpg
                    UploadTask uploadTask = imageReference.putFile(imagePath);

                    Spinner spinner = (Spinner) findViewById(R.id.spinnerBloodType);
                    String typeofblood = spinner.getSelectedItem().toString();
                    Heightnumber = Integer.parseInt(txtHeight.getText().toString());
                    Weightnumber = Integer.parseInt(txtWeight.getText().toString());
                    String ChildFirstName = txtFirstName.getText().toString();
                    String ChildLastName = txtLastName.getText().toString();
                    String ChildMotherName = txtMotherName.getText().toString();
                    String Phone = txtPhone.getText().toString();
                    final String strGender =
                            ((RadioButton) findViewById(mGender.getCheckedRadioButtonId()))
                                    .getText().toString();

                    child = new Child();
                    child.setChildMotherName(ChildMotherName);
                    child.setChildLastName(ChildLastName);
                    child.setChildName(ChildFirstName);
                    child.setPhone(Phone);
                    child.setPassword(password);
                    child.setEmail(email);
                    child.setUid(id);
                    child.setBirthday(DateUtil.getDateFromString(BDate + " 00:00"));
                    child.setBlood(typeofblood);
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

                    LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());

                    db.collection("child").document(id).set(child);
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

                    Toast.makeText(Register.this, R.string.registered_successfully, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this, MyDrawer.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(Register.this, R.string.worn_input_retry, Toast.LENGTH_SHORT).show();

                }
                       }


        });



        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent();
                profileIntent.setType("image/*");
                profileIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(profileIntent, "Select Image."), PICK_IMAGE);
            }
        });
    }


        public void setDate(View view) {
            txtBirthday = (EditText) view;

            datePicker = new DatePickerDialog(Register.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
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

            //limit the birthdate
            Calendar cal = Calendar.getInstance();
            datePicker.getDatePicker().setMinDate( cal.getTimeInMillis());
            cal.setTime(cal.getTime());
            cal.add(Calendar.YEAR, 5);
            long newDate = cal.getTimeInMillis();
            datePicker.getDatePicker().setMaxDate( newDate);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
