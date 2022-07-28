package com.example.devapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.banner.BannerView;

public class  LoginEmail extends AppCompatActivity {

    private RelativeLayout signUp;
    private EditText email, password;
    private TextView login;
    private FirebaseAuth firebaseAuth;

    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huaweiproject-efd1a-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        HwAds.init(this);

        BannerView bottomBannerView = findViewById(R.id.hw_banner_view);
        AdParam adParam = new AdParam.Builder().build();
        bottomBannerView.loadAd(adParam);

        BannerView bannerView = new BannerView(this);
        bannerView.setAdId("testw6vs28auh3");
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        bannerView.loadAd(adParam);
        RelativeLayout rootView = findViewById(R.id.root_view);
        rootView.addView(bannerView);

        email = findViewById(R.id.signInEmail);
        password = findViewById(R.id.passwordSignIn);
        login = findViewById(R.id.login_click);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = email.getText().toString();
                String _pass = password.getText().toString();

                if(!_email.isEmpty()&&!_pass.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(_email,_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginEmail.this,MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(LoginEmail.this, "Input is Wrong, Try Again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                           .addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull  Exception e) {
                                   Toast.makeText(LoginEmail.this, "Error: "+ e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                               }
                           })
                            .addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    Toast.makeText(LoginEmail.this, "Canceled!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(LoginEmail.this, "Input Cannot be Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //setContentView(R.layout.activity_login_email);
        signUp = findViewById(R.id.signUp_shape);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginEmail.this,SignUpEmail.class));
            }
        });
    }
}