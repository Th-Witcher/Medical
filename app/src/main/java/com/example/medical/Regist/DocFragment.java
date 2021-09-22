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

import com.example.medical.R;
import com.example.medical.Splash.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DocFragment extends Fragment {

    EditText name,mail,number,address,about,pass,confirm;
    Spinner gender,city,special;
    private FirebaseAuth mauth;
    Doctors doctors;
    ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_doc, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.splash_color));

        mauth = FirebaseAuth.getInstance();


        spincity(root);
        spingender(root);
        spinspelize(root);
        registdoctor(root);


        return root;
    }
    private void spingender(View root) {

        final Spinner spinner = root.findViewById(R.id.spin1);

        String[] plants = new String[]{
                "Male",
                "Male",
                "Female"


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

    private void spinspelize(View root) {

        final Spinner spinner = root.findViewById(R.id.Specialization);

        String[] plants = new String[]{
                "Heart",
                "Heart",
                "Teeth", "Dermatology", "Eyes","Obstetrics and Gynecology"


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

    private void spincity(View root) {

        final Spinner spinner = root.findViewById(R.id.spin2);

        String[] plants = new String[]{
                "Cairo",
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


    public void registdoctor(View view){

        Button button=view.findViewById(R.id.sign_doc);
        name=view.findViewById(R.id.doc_name);
        city=view.findViewById(R.id.spin2);
        gender=view.findViewById(R.id.spin1);
        special=view.findViewById(R.id.Specialization);
        mail=view.findViewById(R.id.doc_mail);
        number=view.findViewById(R.id.doc_num);
        address=view.findViewById(R.id.address_doc);
        about=view.findViewById(R.id.About);
        pass=view.findViewById(R.id.doc_pass);
        confirm=view.findViewById(R.id.confirmpas_doc);
        progressBar=view.findViewById(R.id.prograssbar2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String docname=name.getText().toString().trim();
                String docmail=mail.getText().toString().trim();
                String docmobile=number.getText().toString().trim();
                String docaddress=address.getText().toString().trim();
                String docabout=about.getText().toString().trim();
                String docpass=pass.getText().toString().trim();
                String docconfirm=confirm.getText().toString().trim();
                String doccity=city.getSelectedItem().toString().trim();
                String docgender=gender.getSelectedItem().toString().trim();
                String docspecial=special.getSelectedItem().toString().trim();


                if (docname.isEmpty()){
                    name.setError("Input U Name");

                } else if (docmail.isEmpty()){
                    mail.setError("Input U Email");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(docmail).matches()) {
                    mail.setError("Provide valid email");
                    mail.requestFocus();
                }else if (docmobile.isEmpty()){
                    number.setError("Input U Number Phone");
                } else if (docmobile.length()<11){
                    number.setError("U Number Uncorrected");
                }
                 else if (docaddress.isEmpty()){
                    address.setError("Input U Address");

                }else if (docabout.isEmpty()){
                    about.setError("Input U Info");
                } else if (docpass.isEmpty()){
                    pass.setError("Input U Password");
                } else if (docpass.length()<6){
                    pass.setError("U Password Week");
                } else if (!docconfirm.equals(docpass)){
                    confirm.setError("Password Not matching");
                }
                 else {
                    progressBar.setVisibility(View.VISIBLE);

                    mauth.createUserWithEmailAndPassword(docmail,docpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                doctors=new Doctors();
                                doctors.setName(docname);
                                doctors.setEmail(docmail);
                                doctors.setNumber(docmobile);
                                doctors.setAddress(docaddress);
                                doctors.setInfo(docabout);
                                doctors.setCity(doccity);
                                doctors.setSpecial(docspecial);
                                doctors.setGender(docgender);
                                doctors.setType("doctor");
                                doctors.setImageurl("default");


                                FirebaseDatabase.getInstance().getReference("Doctors")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(doctors).addOnCompleteListener(new OnCompleteListener<Void>() {
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