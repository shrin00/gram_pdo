package com.example.gram_pdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewUserApplication extends AppCompatActivity {

    private String postkey, servicepostkey;
    private DatabaseReference applicationref;
    private LinearLayout viewapplicationlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_application);
        postkey = getIntent().getStringExtra("postkey");
        servicepostkey = getIntent().getStringExtra("servicepostkey");
        viewapplicationlayout = (LinearLayout) findViewById(R.id.id_viewuserapplication);
        applicationref = FirebaseDatabase.getInstance().getReference().child("Services").child(servicepostkey).child("Applications").child(postkey);

        applicationref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()){
                    String datatext = snap.getKey()+" : "+snap.getValue();
                    TextView newtextview = new TextView(getApplicationContext());
                    LinearLayout.LayoutParams params = (new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));

                    params.setMargins(0, 10, 0, 10);
                    newtextview.setLayoutParams(params);
                    newtextview.setText(datatext);
                    newtextview.setElevation(10);
                    newtextview.setTextSize(20);
                    viewapplicationlayout.addView(newtextview);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}