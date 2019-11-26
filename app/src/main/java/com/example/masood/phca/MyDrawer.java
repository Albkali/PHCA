package com.example.masood.phca;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import com.google.firebase.auth.FirebaseAuth;

public class MyDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
//    private FirebaseAuth firebaseAuth;
//    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_drawer);





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_notifications, R.id.nav_profile,
                R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(authStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(authStateListener != null){
//            firebaseAuth.removeAuthStateListener(authStateListener);
//        }
//    }

    public void ClickToVaccination(View view)
    {
        Intent intent = new Intent ( this, VaccinationActivity.class);
        startActivity(intent);
    }

    public void ClickToPediatrician(View view)
    {
        Intent intent = new Intent ( this, Pediatrician.class);
        startActivity(intent);
    }


    public void click_bnt_mbi(View view)
    {
        Intent intent = new Intent( this, BMI_Calculate.class);
        startActivity(intent);
    }


    public void ClickToCSHCN(View view)
    {
        Intent intent = new Intent ( this, CSHCN.class);
        startActivity(intent);
    }

    public void ClickToArticle(View view)
    {
        Intent intent = new Intent ( this, Article.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_logout){
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            Intent intent = new Intent ( this, Login_form.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
