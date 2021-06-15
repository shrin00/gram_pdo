package com.example.gram_pdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Publishnews extends AppCompatActivity {

    private TextInputLayout tHeading, tAbout, tDetails;
    private Button tPublish;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference newsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishnews);

        firebaseDatabase = FirebaseDatabase.getInstance();
        newsReference = firebaseDatabase.getReference().child("news");

        tHeading = (TextInputLayout) findViewById(R.id.id_newsHeadding);
        tAbout = (TextInputLayout) findViewById(R.id.id_newsAbout);
        tDetails = (TextInputLayout) findViewById(R.id.id_newsDetails);
        tPublish = (Button) findViewById(R.id.id_ButtonPublish);

        tPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishNews();
                Toast.makeText(Publishnews.this, "News Published....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Publishnews.this, MainActivity.class));
                finish();
            }
        });


    }

    private void publishNews(){
        String heading, about, details;
        heading = tHeading.getEditText().getText().toString();
        about = tAbout.getEditText().getText().toString();
        details = tDetails.getEditText().getText().toString();

        Newsmodel news = new Newsmodel(heading, about, details);
        newsReference.push().setValue(news);
    }

}