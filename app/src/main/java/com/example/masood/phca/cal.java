package com.example.masood.phca;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



public class cal extends AppCompatActivity {

    EditText weight, height;
    TextView resulttext;
    String calculation, BMIresult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttext = findViewById(R.id.result);

    }

    public void calculateBMI(View view){
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();


        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) / 100;


        float bmi = weightValue / (heightValue * heightValue);


        if (bmi < 16) {
            BMIresult = "Severely Under Weight";
        } else if (bmi < 18.5) {
            BMIresult = "Under Weight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal Weight";
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = "Overweight";
        } else {
            BMIresult = "Obese";
        }


        calculation = "BMI is : " + bmi + "\n" + "The Status is " + BMIresult;


        resulttext.setText(calculation);
    }


}



