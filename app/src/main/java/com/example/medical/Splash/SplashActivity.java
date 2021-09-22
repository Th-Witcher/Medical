package com.example.medical.Splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.view.Window;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.medical.Company.CompanyActivity;
import com.example.medical.Doctor.doctor_profile;
import com.example.medical.Pharmacy.PharmacyActivity;
import com.example.medical.R;
import com.example.medical.Regist.Clinic_Regist_ِActivity;
import com.example.medical.patient_adapt.PatientActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import io.paperdb.Paper;

public class SplashActivity extends AppCompatActivity {

    TextView user, pas, forget;
    private FirebaseAuth mAuth;
    DatabaseReference ref4, ref1, ref2, ref3;
    ProgressBar progressBar;
    CheckBox checkBox;
    String email ,password;

    public static final String Email="Email";
    public static final String pass="Password";

    String paper1;
    String paper2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.splash_color));

        mAuth = FirebaseAuth.getInstance();

        user = (TextView) findViewById(R.id.usermail);
        pas = (TextView) findViewById(R.id.userpass);
        progressBar=findViewById(R.id.prograssbar);
        forget=findViewById(R.id.forgetpassword);
        checkBox=findViewById(R.id.chekboxlogin);
        Paper.init(this);

         paper1=Paper.book().read(Email,email);
         paper2=Paper.book().read(pass,password);
        if (paper1==null && paper2==null){
            Toast.makeText(SplashActivity.this, "Welcome ", Toast.LENGTH_SHORT).show();


        }else if (paper1!="" && paper2!=""){
            mAuth.signInWithEmailAndPassword(paper1, paper2)
                    .addOnCompleteListener(SplashActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                onAuthSuccess(task.getResult().getUser());
                            } else {
                                Toast.makeText(SplashActivity.this, "Could not login, password or email wrong ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

        }



        final Button button = findViewById(R.id.btn_signup);




        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, Clinic_Regist_ِActivity.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, ForgetpasswordActivity.class));
            }
        });

        final Button button1 = findViewById(R.id.btn_signin);


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                email = user.getText().toString().trim();
                password = pas.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SplashActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SplashActivity.this, "Please Enter Your PASSWORDS", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SplashActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    onAuthSuccess(task.getResult().getUser());
                                } else {
                                    Toast.makeText(SplashActivity.this, "Could not login, password or email wrong ", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });


    }




    private void onAuthSuccess(FirebaseUser user) {


        if (user != null) {
            ref1 = FirebaseDatabase.getInstance().getReference().child("Doctors").child(user.getUid()).child("type");
            ref2 = FirebaseDatabase.getInstance().getReference().child("Pharmacy").child(user.getUid()).child("type");
            ref3 = FirebaseDatabase.getInstance().getReference().child("Patient").child(user.getUid()).child("type");
            ref4 = FirebaseDatabase.getInstance().getReference().child("Company").child(user.getUid()).child("type");

            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);

                    if ("doctor".equals(value)) {
                        savestate();

                        startActivity(new Intent(SplashActivity.this, doctor_profile.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);

                    if ("pharmacy".equals(value)) {
                        savestate();

                        startActivity(new Intent(SplashActivity.this, PharmacyActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);

                    if ("patient".equals(value)) {
                        savestate();

                        startActivity(new Intent(SplashActivity.this, PatientActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            ref4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);

                    if ("company".equals(value)) {
                        savestate();

                        startActivity(new Intent(SplashActivity.this, CompanyActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }

    public void savestate(){

        if (checkBox.isChecked()){
            Paper.book().write(Email,email);
            Paper.book().write(pass,password);
        }



    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
