package com.example.gram_pdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Addservice extends AppCompatActivity {
    private TextInputLayout serviceName, fees;
    private Button submitService;
    private ListView fieldsofservice;
    private ArrayAdapter<String> adapter;
    private DatabaseReference serviceRef;
    private ArrayList<String> fields = new ArrayList<>(List.of("Child birth date", "Child sex", "Child name", "Father Name",
            "Mother Name", "Birth Place", "State", "District", "Cast", "Father's Education", "Mother's Education", "Father's work",
            "Mother's work", "Mother's age at the time marriage", "Mother's age at the time pregrency",
            "Total Number of child belongs to mother including this"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addservice);


        serviceName = (TextInputLayout) findViewById(R.id.id_servicename);
        fees = (TextInputLayout) findViewById(R.id.id_servicefees);
        submitService = (Button) findViewById(R.id.id_submitservice);
        fieldsofservice = (ListView) findViewById(R.id.id_addservice);

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice, fields);
        fieldsofservice.setAdapter(adapter);

        serviceRef = FirebaseDatabase.getInstance().getReference().child("Services");
        submitService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String userId = user.getUid();
                String randomkey = userId+""+new Random().nextInt(1000);

                String servicename = serviceName.getEditText().getText().toString();
                String Fees = fees.getEditText().getText().toString();

                HashMap ser = new HashMap();
                ser.put("servicename", servicename);
                ser.put("fees", Fees);

                serviceRef.child(randomkey).setValue(ser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            HashMap items = new HashMap();
                            for (int i=0;i<fieldsofservice.getCount();i++){
                                if (fieldsofservice.isItemChecked(i)){
                                    String it = fieldsofservice.getItemAtPosition(i).toString();
                                    items.put(it, it);
                                }
                            }

                            serviceRef.child(randomkey).child("required").setValue(items);
                            Toast.makeText(getApplicationContext(), "service added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "service failed to add", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}