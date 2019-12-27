package com.example.masood.phca.ChildList;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masood.phca.CSHCN;
import com.example.masood.phca.CSHCN_WebView_activity;
import com.example.masood.phca.R;
import com.example.masood.phca.itemCSHCN;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class Childlist extends AppCompatActivity {
    Context ctx;
    private RecyclerView recyclerView;
    private DatabaseReference DBReference;
    //    private final Firestore db;
    FirebaseFirestore db;
    FirebaseFirestore querySearch;

    ChildItem art = new ChildItem();
    private FirestoreRecyclerAdapter<ChildItem, Childlist.NewsViewHolder> mPeopleRVAdapter;

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

        FirestoreRecyclerOptions<ChildItem> options = new FirestoreRecyclerOptions.Builder<ChildItem>()
                .setQuery(chainedQuery2, ChildItem.class)
                .build();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mPeopleRVAdapter = new FirestoreRecyclerAdapter<ChildItem, Childlist.NewsViewHolder>(options) {

            @Override
            protected void onBindViewHolder(Childlist.NewsViewHolder holder, final int position, final ChildItem model) {
                holder.setTitle(model.getChildName());
                holder.setDesc(model.getChildLastName());
                holder.setphotoUrl(getBaseContext(), model.getphotoUrl());


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
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.child_name);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_descCSHCN = (TextView)mView.findViewById(R.id.child_date);
            post_descCSHCN.setText(desc);
        }
        public void setphotoUrl(Context ctx, String photoUrl){
            ImageView post_image = (ImageView) mView.findViewById(R.id.child_photo);

            Picasso.get().load(photoUrl).into(post_image);


        }

    }}
