package com.example.masood.phca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.masood.phca.Adapter.VaccinationAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class VaccinationActivity extends AppCompatActivity {

//    private VaccinationAdapter adapter;
//    private FirebaseDatabase database;
//    private RecyclerView recyclerView ;
//    private VaccinationItemFirebase groceryItemsFirebase;





    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    ArrayList<QueryDocumentSnapshot> itineraries;
    FirebaseDatabase database;
    VaccinationAdapter adapter;
    Object context;

    //DatabaseReference vaccinationItem;
    //AppCompatActivity callingActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        getSupportActionBar().setTitle("Vaccinations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

//        recyclerView=(RecyclerView)findViewById(R.id.rv_vacc_items);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        adapter=new VaccinationAdapter(getApplicationContext());
//        recyclerView.setAdapter(adapter);
//        database = FirebaseDatabase.getInstance();
//        groceryItemsFirebase = new VaccinationItemFirebase(adapter, database, this);

            context = this;

            db = FirebaseFirestore.getInstance();

            itineraries = new ArrayList<>();

//        //final String TAG = VaccinationActivity.class.getSimpleName();
//            this.database = database;
//            this.adapter = adapter;
//            this.callingActivity = callingActivity;
//            vaccinationItem = database.getReference("vaccination");

        recyclerView = findViewById(R.id.rv_vacc_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //filter to display only users items

            db.collection("child")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {


                            if (task.isSuccessful()) {

                                if (task.getResult().isEmpty()) {
                                    Toast.makeText(VaccinationActivity.this, "No Itineraries Found", Toast.LENGTH_LONG).show();
                                } else {


                                    // Collect data from firestore to array list
                                    itineraries = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        itineraries.add(document);
                                        //                                    VaccinationItem groceryItem = child.getValue(VaccinationItem.class);
                                        //                                    groceryItem.setId(child.getKey());
                                        //                                    groceryItems.add(groceryItem);
                                    }
                                    Collections.reverse(itineraries);

                                    // adapter.setGroceryItems(itineraries);
                                    // Initialize Recycler View with Adapter
                                    adapter = new VaccinationAdapter(VaccinationActivity.this, itineraries);
                                    recyclerView.setAdapter(adapter);

                                }
                            }else {
                                Log.d("ItinerariesSearch", "Error getting documents: ", task.getException());
                            }
                        }

                    });

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

