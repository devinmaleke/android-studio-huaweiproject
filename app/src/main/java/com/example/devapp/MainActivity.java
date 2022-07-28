package com.example.devapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebase;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.yourEmail);


        mFirebase = FirebaseAuth.getInstance();

        CardView cv = findViewById(R.id.eplbtn);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });

        CardView cv2 = findViewById(R.id.laligabtn);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivity(intent);
            }
        });
        CardView cv3 = findViewById(R.id.ligue1_btn);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity4.class);
                startActivity(intent);
            }
        });

        CardView cv4 = findViewById(R.id.serie_abtn);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity7.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser mFirebaseUser =  mFirebase.getCurrentUser();
        if(mFirebaseUser!=null){
            email.setText("Hi, "+mFirebaseUser.getEmail());
        }else{
            startActivity(new Intent(MainActivity.this,LoginEmail.class));
            finish();
        }
    }

    public void logout(View view) {
        mFirebase.signOut();
        startActivity(new Intent(MainActivity.this,LoginEmail.class));
    }

}