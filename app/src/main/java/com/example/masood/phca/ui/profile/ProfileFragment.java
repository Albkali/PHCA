package com.example.masood.phca.ui.profile;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.masood.phca.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    EditText  weight ,height;
    TextView resulttext;
    String calculation, BMIresult;
    Button calc;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);


        weight = (EditText) v.findViewById(R.id.weight);
        height = (EditText) v.findViewById(R.id.height);
        calc = (Button) v.findViewById(R.id.calculate_button);
        resulttext = (TextView) v.findViewById(R.id.result);

        calc.setOnClickListener( this);

        return v;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calculate_button:

                calculateBMI2();

                break;
        }
    }
    public void calculateBMI2(){
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
