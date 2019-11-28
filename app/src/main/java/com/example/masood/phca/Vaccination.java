package com.example.masood.phca;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;



import com.example.masood.phca.Adapter.VaccinationAdapter;
import com.example.masood.phca.Model.VaccinationItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.masood.phca.NotificationChannels.CHANNEL_1_ID;

//import com.google.firebase.Timestamp;
//import com.google.firebase.Timestamp;

public class Vaccination extends AppCompatActivity {

//    private VaccinationAdapter adapter;
//    private FirebaseDatabase database;
//    private RecyclerView recyclerView ;
//    private VaccinationItemFirebase groceryItemsFirebase;





    private static FirebaseFirestore db ;

    RecyclerView recyclerView;
    ArrayList<VaccinationItem> posts;
    VaccinationAdapter adapter;
    private NotificationManagerCompat notificationManager;

    //public static String  name = "";
    public static String status = "";
    public static String name2 = "";
    public  static String status2 = "";


    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        getSupportActionBar().setTitle("Vaccinations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar


        notificationManager = NotificationManagerCompat.from(this);
//        CheckVacciDate ch = new CheckVacciDate();
        check();
        notification();

        recyclerView = findViewById(R.id.rv_vacc_items);

        db = FirebaseFirestore.getInstance();

        db.collection("vaccination")
                    .document("QAv2QZFhih39UGOKTTVM")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            posts = new ArrayList<>();

                            if (task.isSuccessful()) {

                                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");

                                String name = task.getResult().get("1to4_name").toString();
                                String status = task.getResult().get("1to4_status").toString();
                                String date = sfd.format(task.getResult().get("1to4_date"));


                                String name2 = task.getResult().get("cc_name").toString();
                                String status2 = task.getResult().get("cc_status").toString();
                                String date2 = sfd.format(task.getResult().get("cc_date"));


                                posts.add(new VaccinationItem(name , status ,date));
                                posts.add(new VaccinationItem(name2 , status2 ,date2));

                                adapter = new VaccinationAdapter(Vaccination.this,posts);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(Vaccination.this));






                            }else {
                                Log.d("ItinerariesSearch", "Error getting documents: ", task.getException());
                            }
                    }
                    });

    }

    public void notification() {
        Intent activityIntent = new Intent(this, Vaccination.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0 , activityIntent,0);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_hospital)
                .setContentTitle("Vaccination alert")
                .setContentText("your child vaccination date is close!!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setColor(Color.GRAY)
                .build();

        notificationManager.notify(1, notification);

    }
    public void check () {

        db = FirebaseFirestore.getInstance();

        db.collection("vaccination")
                .document("QAv2QZFhih39UGOKTTVM")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Date userDate = document.getDate("cc_date");
                                Date c = Calendar.getInstance().getTime();
                                String status = task.getResult().get("cc_status").toString();

                                if ( userDate.compareTo(c) >= 0 && status.equals("waiting") ){
                                    Toast.makeText(Vaccination.this, "comming", Toast.LENGTH_LONG).show();

                                }else{
                                    Toast.makeText(Vaccination.this, "gone", Toast.LENGTH_LONG).show();

                                }


//                                Log.d("ItinerariesSearch", userDate.compareTo(c), task.getException());


                            }
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

