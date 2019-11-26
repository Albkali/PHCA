package com.example.masood.phca;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
=======
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
>>>>>>> af91993625362e162d809184145891fe96a7e2ed

public class CSHCN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cshcn);

        getSupportActionBar().setTitle("CSHCN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
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
