package com.example.masood.phca;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageButton calbtn2 ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        calbtn2= (ImageButton)findViewById(R.id.cal_btn);
        calbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calclick();
            }
            });
        }
        public void calclick(){
            Intent intent = new Intent(this, cal.class);
            startActivity(intent);
        }




    }