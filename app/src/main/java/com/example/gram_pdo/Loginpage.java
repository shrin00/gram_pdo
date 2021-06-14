package com.example.gram_pdo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Loginpage extends Activity {
    private TextInputLayout tEmail, tPassword;
    private Button tLogin;
    private TextView mCreateBtn;
    private ProgressBar pb;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference userReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        mAuth = FirebaseAuth.getInstance();

        tEmail = (TextInputLayout) findViewById(R.id.id_emailLogin);
        tPassword = (TextInputLayout) findViewById(R.id.id_pwdLogin);
        tLogin = (Button) findViewById(R.id.id_login);
//        mCreateBtn=findViewById(R.id.id_registerHere);
        pb = (ProgressBar) findViewById(R.id.progressBar3);

        tLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tEmail.getEditText().getText().toString().trim();
                String password = tPassword.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                }else if(TextUtils.isEmpty(password)){
                    tPassword.setError("Password is required");
                }else if(password.length()<=6){
                    tEmail.setError("Email is required");
                    tPassword.setError("Password must have more than 6 characters");
                }else {
                    pb.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Loginpage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        pb.setVisibility(View.INVISIBLE);
//                                       checkUserType(mAuth.getCurrentUser().getUid());
                                        startActivity(new Intent(Loginpage.this, MainActivity.class));
                                        finish();
                                    }else{
                                        pb.setVisibility(View.INVISIBLE);
                                        tEmail.getEditText().setText("");
                                        tPassword.getEditText().setText("");
                                        Toast.makeText(Loginpage.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

//        mCreateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),Register.class));
//            }
//        });
    }

}
