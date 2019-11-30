package com.example.masood.phca;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.masood.phca.R.*;

public class activity_Register1 extends AppCompatActivity {


    EditText txtBirthday,txtHeight  ,txtWeight;


    Button DoneRegister;
    FirebaseFirestore db ;
    Child child;

    private FirebaseAuth firebaseAuth;
RadioGroup mGender;
RadioButton mGenderOptions ;
//RadioButton rMale ;
//RadioButton rFemale;
String strGender ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity__register1);

        db = FirebaseFirestore.getInstance();

        child = new Child();


        DoneRegister=(Button)findViewById(id.btn_register);

        txtBirthday=(EditText)findViewById(id.editTextBabeBirthday);
        txtHeight=(EditText)findViewById(id.editTextHeight);
        txtWeight=(EditText)findViewById(id.editTextWeight);

//        rMale=(RadioButton) findViewById(id.r_Male);
//        rFemale=(RadioButton) findViewById(id.r_Fmale);
        mGender=(RadioGroup) findViewById(id.m_Gender);




        DoneRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // child.setChildName(txtFirstName.getText().toString());
                // child.setChildLastName(txtLastName.getText().toString());
                //child.setChildMotherName(txtMotherName.getText().toString());
                //child.setEmail(txtEmail.getText().toString().trim());
                //child.setPassword(txtPassword.getText().toString().trim());
                //Number = (txtFirstName.getText().toString().trim());
                child = new Child();

                //Map<String, Object> childs = new HashMap<>();
                //child.sett("Birthday", txtBirthday.getText().toString());
                //child.put("Height", txtHeight.getText().toString());
                //child.put("Weight", txtWeight.getText().toString());

                int Birthnumber = Integer.parseInt(txtBirthday.getText().toString());
                int Heightnumber = Integer.parseInt(txtHeight.getText().toString());
                int Weightnumber = Integer.parseInt(txtWeight.getText().toString());

                int selectedId = mGender.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                mGenderOptions = (RadioButton) findViewById(selectedId);
                strGender = mGenderOptions.getText().toString();

                Spinner spinner = (Spinner)findViewById(id.spinnerBloodType);
                String text = spinner.getSelectedItem().toString();

                getIntent().hasExtra("ChildFirstName");
                String ChildFirstName = getIntent().getStringExtra("ChildFirstName");
                child.setChildName(ChildFirstName);

                getIntent().hasExtra("ChildLastName");
                String ChildLastName = getIntent().getStringExtra("ChildLastName");
                child.setChildLastName(ChildLastName);

                getIntent().hasExtra("ChildMotherName");
                String ChildMotherName = getIntent().getStringExtra("ChildMotherName");
                child.setChildMotherName(ChildMotherName);

                getIntent().hasExtra("Phone");
                String Phone = getIntent().getStringExtra("Phone");
                child.setPhone(Phone);

                child.setBirthday(Birthnumber);
                child.setGender(strGender);
                child.setBlood(text);

//                mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
////                    @Override
////                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
////                        mGenderOptions = mGender.findViewById(i);
////                        switch (i){
////                            case id.r_Male:
////                            strGender  = mGenderOptions.getText().toString();
////                                break;
////                            case id.r_Fmale:
////                             strGender  = mGenderOptions.getText().toString();
////
////                                break;
////                            default:
////
////
////                        }
////                        child.setGender(strGender);
////                    }
////                });
//                child.setHeight(Heightnumber);
//                child.setWeight(Weightnumber);
//
//                if(rMale.isChecked()) {
//                    strGender = rMale.getText().toString();
//                    child.setGender(strGender);
//
//                    //Toast.makeText(activity_Register1.this,Gender, Toast.LENGTH_LONG).show();
//                            }
//                           else
//                            if(rFemale.isChecked()) {
//
//
//                                 Gender = rFemale.getText().toString();
//                                child.setGender(strGender);
//
//                               // Toast.makeText(activity_Register1.this,Gender, Toast.LENGTH_LONG).show();
//
//
//                            }

                //child.put("Birthday", txtBirthday.getText().toString());
                // child.put("Height", txtHeight.getText().toString());
                // child.put("Weight", txtWeight.getText().toString());

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();
Map<String,Object> userh = new HashMap<>();
userh.put("weight",Weightnumber);
userh.put("height",Heightnumber);

                db.collection("child").document(id).set(child);

                db.collection("child").document(id).collection("IBM").document(id).set(userh);



           Toast.makeText(activity_Register1.this, "data inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity_Register1.this, MyDrawer.class);
                startActivity(intent);
            }
                        });


        }



//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }


    public void ClickBackToLogin2(View view) {
                Intent intent = new Intent(activity_Register1.this, Login_form.class);
                startActivity(intent);
            }


        }
