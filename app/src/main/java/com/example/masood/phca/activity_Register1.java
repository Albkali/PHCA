package com.example.masood.phca;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.masood.phca.ui.profile.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.example.masood.phca.R.*;
public class activity_Register1 extends AppCompatActivity {

    DatePickerDialog datePicker;
    Calendar calendar;
    int day;
    int month;
    int year;
    String BDate;
    EditText txtBirthday,txtHeight  ,txtWeight;
    Button DoneRegister;
    FirebaseFirestore db ;
    Child child;

    private FirebaseAuth firebaseAuth;
    RadioGroup mGender;
    RadioButton mGenderOptions ;
    CheckBox SpecialNeeds ;

    int Birthnumber,Heightnumber,Weightnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity__register1);

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        db = FirebaseFirestore.getInstance();
        child = new Child();

        DoneRegister = (Button) findViewById(id.btn_register);

        txtBirthday = (EditText) findViewById(id.editTextBabeBirthday);
        txtHeight = (EditText) findViewById(id.editTextHeight);
        txtWeight = (EditText) findViewById(id.editTextWeight);
        mGender = (RadioGroup) findViewById(id.m_Gender);
        SpecialNeeds = (CheckBox) findViewById(id.checkboxsn);
    }
    public void submitData(View view){
        // Hide Keyboard
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Register.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        try {

            child = new Child();
            Spinner spinner = (Spinner)findViewById(id.spinnerBloodType);
            String typeofblood = spinner.getSelectedItem().toString();

//            Birthnumber = Integer.parseInt(txtBirthday.getText().toString());
            Heightnumber = Integer.parseInt(txtHeight.getText().toString());
            Weightnumber = Integer.parseInt(txtWeight.getText().toString());

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

            child.setBirthday(DateUtil.getDateFromString(BDate+" 00:00"));


            child.setBlood(typeofblood);

            final String strGender =
                    ((RadioButton)findViewById(mGender.getCheckedRadioButtonId()))
                            .getText().toString();
            child.setGender(strGender);

            mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    mGenderOptions = mGender.findViewById(i);

                    switch (i){
                        case id.r_Male:
//                                strGender  = mGenderOptions.getText().toString();
//                                child.setGender(strGender);
                            break;

                        case id.r_Fmale:
//                                strGender  = mGenderOptions.getText().toString();
//                                child.setGender(strGender);

                            break;

                        default:


                    }
                }
            });

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String id = user.getUid();

//            Map<String,Object> userh1 = new HashMap<>();
//            userh1.put("Bdate",DateUtil.getDateFromString(BDate+" 00:00"));
//            db.collection("child").document(id).set(userh1);

            Map<String,Object> userh = new HashMap<>();
            userh.put("weight",Weightnumber);
            userh.put("height",Heightnumber);




            db.collection("child").document(id).set(child);


            db.collection("child").document(id).collection("IBM").document(BDate).set(userh);

            if(SpecialNeeds.isChecked()){
                Map<String,Object> SpecialNeeds = new HashMap<>();
                SpecialNeeds.put("status","Yes");
                SpecialNeeds.put("type",null);

                db.collection("child").document(id)
                        .collection("Special Needs").document(id).set(SpecialNeeds);
            }
            else
            {
                db.collection("child").document(id)
                        .collection("Special Needs").document(id).delete();
            }

            Toast.makeText(activity_Register1.this, "data inserted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity_Register1.this, MyDrawer.class);
            startActivity(intent);

        }catch (Exception e){
            Toast.makeText(activity_Register1.this, "Wrong input, please retry", Toast.LENGTH_SHORT).show();

        }
    }
    public void setDate(View view) {
        txtBirthday = (EditText) view;

        datePicker = new DatePickerDialog(activity_Register1.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int et_id = txtBirthday.getId();
                        String date = (dayOfMonth) + "-" + (monthOfYear) + "-" + (year);

                        if(et_id == id.editTextBabeBirthday) {
                            BDate = date;
                        }


//                        ProfileFragment fragment = new ProfileFragment();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("birthDate", date);
//                        fragment.setArguments(bundle);

                        txtBirthday.setText(date);
                    }
                }, year, month, day);
        datePicker.show();




    }
    public void ClickBackToLogin2(View view) {
        Intent intent = new Intent(activity_Register1.this, Login_form.class);
        startActivity(intent);
    }


}