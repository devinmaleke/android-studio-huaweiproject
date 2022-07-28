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
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.banner.BannerView;

public class SignUpEmail extends AppCompatActivity {

    private EditText email, pass, confirmPass;
    private RelativeLayout signIn;
    private TextView signUp;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

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

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.signUpEmail);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirm_Password);
        signUp = findViewById(R.id.signUp_bawah);
       // email = findViewById(R.id.signIn_shape);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_ = email.getText().toString();
                String pass_ = pass.getText().toString();
                String confirmPass_ = confirmPass.getText().toString();

                if(!email_.isEmpty()&&!pass_.isEmpty()&&!confirmPass_.isEmpty()){
                    if(pass_.equals(confirmPass_)){
                        if(pass_.length()>=8){
                            firebaseAuth.createUserWithEmailAndPassword(email_,pass_).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(SignUpEmail.this,LoginEmail.class));
                                        finish();
                                    }else{
                                        Toast.makeText(SignUpEmail.this, "Input Wrong, Please Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpEmail.this, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    Toast.makeText(SignUpEmail.this, "Canceled, Please Try Again!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            ;

                        }else{
                            Toast.makeText(SignUpEmail.this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(SignUpEmail.this, "Password is Wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUpEmail.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signIn = findViewById(R.id.signIn_shape);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpEmail.this,LoginEmail.class));
            }
        });
    }
}