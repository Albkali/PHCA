package com.example.masood.phca.ChildList;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masood.phca.CSHCN_WebView_activity;
import com.example.masood.phca.Child;
import com.example.masood.phca.DateUtil;
import com.example.masood.phca.MyDrawer;
import com.example.masood.phca.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import static java.time.LocalDate.of;

public class Childlist extends AppCompatActivity {
    Context ctx;
    private RecyclerView recyclerView;
    private DatabaseReference DBReference;
    //    private final Firestore db;
    FirebaseFirestore db;
    FirebaseFirestore querySearch;

    Child art = new Child();
    private FirestoreRecyclerAdapter<Child, Childlist.NewsViewHolder> mPeopleRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childlist);

        setTitle("my childern");


        recyclerView = (RecyclerView) findViewById(R.id.rv_child_items);



        db = FirebaseFirestore.getInstance();

        CollectionReference cities = db.collection("child");
        Query chainedQuery2 = cities.orderBy("childName");

//        Query chainedQuery2 = cities.whereEqualTo("state", "CA").whereLessThan("population", 1000000L);

        FirestoreRecyclerOptions<Child> options = new FirestoreRecyclerOptions.Builder<Child>()
                .setQuery(chainedQuery2, Child.class)
                .build();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mPeopleRVAdapter = new FirestoreRecyclerAdapter<Child, Childlist.NewsViewHolder>(options) {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void onBindViewHolder(Childlist.NewsViewHolder holder, final int position, final Child model) {
                holder.setChildname(model.getChildName(),model.getChildLastName());
                holder.setgender(model.getGender());
                holder.setage(model.getBirthday());

                holder.setphotoUrl(getBaseContext(), model.getPhotoUrl());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String id = model.getUid();
                        Intent intent = new Intent(getApplicationContext(), Child4Ped.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public Childlist.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_childlist, parent, false);

                return new Childlist.NewsViewHolder(view);
            }
        };

        recyclerView.setAdapter(mPeopleRVAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();


    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setChildname(String name , String lastname ){
            TextView childfullname = (TextView)mView.findViewById(R.id.child_name);
            childfullname.setText(name + " "+ lastname);
        }
        public void setgender(String gender){
            TextView childgender = (TextView)mView.findViewById(R.id.child_gender);
            childgender.setText(gender);
        }
        public void setphotoUrl(Context ctx, String photoUrl){
            ImageView child_image = (ImageView) mView.findViewById(R.id.child_photo);

            Picasso.get().load(photoUrl).into(child_image);
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void setage(Date age){
            TextView childage = (TextView)mView.findViewById(R.id.child_age);
            String date = age.toString();
            Date datetest = age ;

            Calendar calander = Calendar.getInstance();
            calander.setTime(datetest);
            int userBirthDay = calander.get(Calendar.DAY_OF_MONTH);
            int userBirthMonth = calander.get(Calendar.MONTH);
            int userBirthYear = calander.get(Calendar.YEAR);

            LocalDate userBirthDate = of(userBirthYear, userBirthMonth,userBirthDay );
            LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
             Period p = Period.between(userBirthDate, currentDate);

            String y = p.getYears() + " year" + (p.getYears() > 1 ? "s " : " ");
            String m = (p.getMonths()-1) + " month" + (p.getMonths() > 1 ? "s" : " and ");
//            String d = (p.getDays()+1) + "day" + (p.getDays() > 1 ? "s.\n" : ".\n");


            childage.setText(y+m);
        }
    }}