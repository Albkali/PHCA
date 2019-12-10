package com.example.masood.phca;

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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


public class Article_activity extends AppCompatActivity {

    Context ctx;
    private RecyclerView recyclerView;
    private DatabaseReference DBReference;
    //    private final Firestore db;
    FirebaseFirestore db;
    FirebaseFirestore querySearch;

    itemArticle art = new itemArticle();
    private FirestoreRecyclerAdapter<itemArticle, NewsViewHolder> mPeopleRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_activity);
        DBReference = FirebaseDatabase.getInstance().getReference().child("News");
        DBReference.keepSynced(true);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycleView);
        db = FirebaseFirestore.getInstance();

        CollectionReference cities = db.collection("article");
        Query chainedQuery2 = cities.orderBy("desc");

//        Query chainedQuery2 = cities.whereEqualTo("state", "CA").whereLessThan("population", 1000000L);

        FirestoreRecyclerOptions<itemArticle> options = new FirestoreRecyclerOptions.Builder<itemArticle>()
                .setQuery(chainedQuery2, itemArticle.class)
                .build();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mPeopleRVAdapter = new FirestoreRecyclerAdapter<itemArticle, NewsViewHolder>(options) {

            @Override
            protected void onBindViewHolder(Article_activity.NewsViewHolder holder, final int position, final itemArticle model) {
                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                holder.setImage(getBaseContext(), model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(getApplicationContext(), Article_activity.class);
                        intent.putExtra("id", url);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.article_raw, parent, false);

                return new NewsViewHolder(view);
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
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);

            Picasso.get().load(image).into(post_image);


        }
    }
}
