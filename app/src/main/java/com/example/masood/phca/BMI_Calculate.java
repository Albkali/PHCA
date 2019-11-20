package com.example.masood.phca;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMI_Calculate extends AppCompatActivity {

    EditText weight, height;
    TextView resulttext;
    String calculation, BMIresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__calculate);

        getSupportActionBar().setTitle("BMI_Calculate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //  Auto-generated method stub
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }







}
