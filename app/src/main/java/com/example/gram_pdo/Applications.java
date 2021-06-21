package com.example.gram_pdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Service;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Applications extends AppCompatActivity {

    private RecyclerView applicationsRecview, row_recview;
    private ApplicationAdapter madapter;
    private DatabaseReference mserviceref;
    private UserApplicationAdapter museradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);
        mserviceref = FirebaseDatabase.getInstance().getReference().child("Services");

        applicationsRecview = (RecyclerView) findViewById(R.id.id_recycleviewApplications);
        applicationsRecview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerOptions<ServiceviewModel> options =
                new FirebaseRecyclerOptions.Builder<ServiceviewModel>()
                        .setQuery(mserviceref, ServiceviewModel.class)
                        .build();


        madapter = new ApplicationAdapter(options, getApplicationContext());
        applicationsRecview.setAdapter(madapter);

    }


    public void onStart() {
        super.onStart();
        madapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        madapter.stopListening();
    }
}