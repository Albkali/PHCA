package com.example.masood.phca;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_Register1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register1);
    }



    public void ClickBackToLogin2(View view)
    {
        Intent intent = new Intent( this, Login_form.class);
        startActivity(intent);
    }

}
