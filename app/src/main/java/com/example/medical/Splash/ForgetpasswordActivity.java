package com.example.medical.Splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import org.jetbrains.annotations.NotNull;

public class ForgetpasswordActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    private ProgressBar progressBar;
    TextView resetstae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.forget_color));

        Toolbar toolbar=findViewById(R.id.barpass);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ٌReset password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetpasswordActivity.this, SplashActivity.class));
            }
        });

        mauth=FirebaseAuth.getInstance();
        TextInputEditText editText=findViewById(R.id.mailreset);
        Button resetpass=findViewById(R.id.btn_reset);
        resetstae=findViewById(R.id.resetstate);
        progressBar=findViewById(R.id.prograssbarset);

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().isEmpty()) {

                    Toast.makeText(ForgetpasswordActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    mauth.fetchSignInMethodsForEmail(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<SignInMethodQueryResult> task) {

                            if (task.getResult().getSignInMethods().isEmpty()) {

                                progressBar.setVisibility(View.GONE);
                                resetstae.setText("This is not Registered email,you can create new account");

                            } else {

                                mauth.sendPasswordResetEmail(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            resetstae.setText("An email to reset password has been sent to your email address");
                                        } else {
                                            resetstae.setText(task.getException().getMessage());
                                        }

                                    }
                                });
                            }

                        }
                    });

                }
            }
        });
    }



}
