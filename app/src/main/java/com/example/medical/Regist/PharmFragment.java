package com.example.medical.Regist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical.Pharmacy.PharmacyActivity;
import com.example.medical.R;
import com.example.medical.Splash.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class PharmFragment extends Fragment {

    EditText pharnamef,pharlnamef,pharmail,pharnumber,docpharmname,pharname,pharpass,pharconfirm;
    Spinner city;
    private FirebaseAuth mauth;
    Pharmacy pharmacy;
    ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_pharm, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.splash_color));


        mauth = FirebaseAuth.getInstance();


        pharmacycity(root);
        registpharmacy(root);

        return root;
    }

    private void pharmacycity(View root) {

        final Spinner spinner = root. findViewById(R.id.pharmacycity);

        String[] plants = new String[]{
                "Cairo",
                "Alexandria", "Ismailia", "Aswan", "Assiut", "Luxor",
                "Red Sea", "Al-Buhaira", "Beni Suef", "Port Said",
                "South Sinai", "Giza", "Dakahlia", "Damietta",
                "Sohag", "Suez", "Sharqia", "Gharbia", "Faiyum",
                "Qalyubia", "Qena", "Kafr El Sheikh", "North Sinai",
                "Matrouh", "Al-Manzafiya", "Minya", "the new Valley"


        };

        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_drobdown, plantsList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {

                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_drobdown);
        spinner.setAdapter(spinnerArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void registpharmacy(View root) {



        pharnamef=root.findViewById(R.id.firstnamepharmcy);
        pharlnamef=root.findViewById(R.id.lastnamepharmcy);
        city=root.findViewById(R.id.pharmacycity);
        pharmail=root.findViewById(R.id.emailpharmacy);
        pharnumber=root.findViewById(R.id.pharmacynumber);
        pharname=root.findViewById(R.id.namepharmcy);
        docpharmname=root.findViewById(R.id.doctorname);
        pharpass=root.findViewById(R.id.passpharmacy);
        pharconfirm=root.findViewById(R.id.confirmpharmacy);
        progressBar=root.findViewById(R.id.prograssbar);
        Button button=root.findViewById(R.id.regpharmacy);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fpharname=pharnamef.getText().toString().trim();
                String fpharlname=pharlnamef.getText().toString().trim();
                String pharmmail=pharmail.getText().toString().trim();
                String pharmnum=pharnumber.getText().toString().trim();
                String Docpharmname=docpharmname.getText().toString().trim();
                String pharmname=pharname.getText().toString().trim();
                String pharmcity=city.getSelectedItem().toString().trim();
                String pharmpass=pharpass.getText().toString().trim();
                String pharmconfirm=pharconfirm.getText().toString().trim();

                if (fpharname.isEmpty()){
                    pharnamef.setError("Input First Name");
                }else if (fpharlname.isEmpty()){
                    pharlnamef.setError("Input Last Name");
                }else if (Docpharmname.isEmpty()){
                    docpharmname.setError("Input Doctor Name");
                }
                else if (pharmmail.isEmpty()){
                    pharmail.setError("Input Pharmacy Mail");

                }if (!Patterns.EMAIL_ADDRESS.matcher(pharmmail).matches()) {
                    pharmail.setError("Provide valid email");
                    pharmail.requestFocus();
                }else if (pharmname.isEmpty()){
                    pharname.setError("Input Pharmacy Name");
                } else if (pharmnum.isEmpty()){
                    pharnumber.setError("Input Pharmacy Number");
                }else if (pharmnum.length()<11){
                    pharnumber.setError("U Number Uncorrected");
                }
                else if (pharmpass.isEmpty()){
                    pharpass.setError("Input U Password");
                } else if (pharmpass.length()<6){
                    pharpass.setError("U Password Week");
                }else if (!pharmconfirm.equals(pharmpass)){
                    pharconfirm.setError("Password Not matching");
                }else {

                    progressBar.setVisibility(View.VISIBLE);

                    mauth.createUserWithEmailAndPassword(pharmmail,pharmpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                pharmacy=new Pharmacy();
                                pharmacy.setFpharname(fpharname);
                                pharmacy.setFpharlname(fpharlname);
                                pharmacy.setPharmmail(pharmmail);
                                pharmacy.setPharmnum(pharmnum);
                                pharmacy.setDocphamname(Docpharmname);
                                pharmacy.setPharmname(pharmname);
                                pharmacy.setPharmcity(pharmcity);
                                pharmacy.setType("pharmacy");
                                pharmacy.setImageurl("default");


                                FirebaseDatabase.getInstance().getReference("Pharmacy")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(pharmacy).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {

                                        if (task.isSuccessful()){

                                            Toast.makeText(getActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getActivity(), SplashActivity.class));

                                        }else {

                                            Toast.makeText(getActivity(), "Failed To Regist", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    }
                                });
                            }else {


                                Toast.makeText(getActivity(), "Failed To Register", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }
                        }
                    });



                }


            }
        });




    }
}