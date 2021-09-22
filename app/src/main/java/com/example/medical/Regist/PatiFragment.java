package com.example.medical.Regist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical.R;
import com.example.medical.Splash.SplashActivity;
import com.example.medical.patient_adapt.PatientActivity;
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
import java.util.Calendar;
import java.util.List;


public class PatiFragment extends Fragment {

    private TextView  mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText fname,lname,patmail,patnum,patjop,patpass,patconfirm;
    TextView datepat;
    DatabaseReference ref;
    Patient patient;
    Spinner gender,state,city;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_pati, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.splash_color));

        mAuth = FirebaseAuth.getInstance();

        spingenderpatient(root);
        spinstatepatient(root);
        patientdate(root);
        spincitypatient(root);
        registpatient(root);


        return root;
    }
    private void spinstatepatient(View root) {

        final Spinner spinner =root.findViewById(R.id.socialstate);

        String[] plants = new String[]{
                "Married",
                "Married",
                "Single"


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

    private void spingenderpatient(View root) {

        final Spinner spinner =root.findViewById(R.id.patientgender);

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

    private void patientdate(View root) {

        mDisplayDate = root.findViewById(R.id.birthdatepatient);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(getActivity()), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    private void spincitypatient(View root) {

        final Spinner spinner =root.findViewById(R.id.patientcity);

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

    public void registpatient(View root) {

        fname=root.findViewById(R.id.patientfirstname);
        lname=root.findViewById(R.id.patientlastname);
        gender=root.findViewById(R.id.patientgender);
        city=root.findViewById(R.id.patientcity);
        state=root.findViewById(R.id.socialstate);
        patmail=root.findViewById(R.id.patientemail);
        datepat=root.findViewById(R.id.birthdatepatient);
        patnum=root.findViewById(R.id.pat_num);
        patjop=root.findViewById(R.id.jobtitle);
        patpass=root.findViewById(R.id.passpatient);
        patconfirm=root.findViewById(R.id.confirmpatient);
        progressBar=root.findViewById(R.id.prograssbar3);




        Button button=root.findViewById(R.id.loginpatient);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String patfname=fname.getText().toString().trim();
                String patlname=lname.getText().toString().trim();
                String patientmail=patmail.getText().toString().trim();
                String patmobile=patnum.getText().toString().trim();
                String patdate=datepat.getText().toString().trim();
                String patientjop=patjop.getText().toString().trim();
                String patientpass=patpass.getText().toString().trim();
                String patientconfirm=patconfirm.getText().toString().trim();

                if (patfname.isEmpty()){
                    fname.setError("Input First Name");
                }else if (patlname.isEmpty()){
                    lname.setError("Input Last Name");
                } else if (patientmail.isEmpty()){
                    patmail.setError("Input U Email");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(patientmail).matches()) {
                    patmail.setError("Provide valid email");
                    patmail.requestFocus();
                }else if (patmobile.isEmpty()){
                    patnum.setError("Input U Number Phone");
                }else if (patmobile.length()<11){
                    patnum.setError("U Number Uncorrected");
                }
                else if (patientjop.isEmpty()){
                    patjop.setError("Input U Jop");
                }else if (patientpass.isEmpty()){
                    patpass.setError("Input U Password");
                } else if (patientpass.length()<6){
                    patpass.setError("U Password Week");
                }else if (!patientconfirm.equals(patientpass)){
                    patconfirm.setError("Password Not matching");

                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(patientmail,patientpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                patient=new Patient();
                    patient.setfName(patfname);
                    patient.setlName(patlname);
                    patient.setEmail(patientmail);
                    patient.setNumber(patmobile);
                    patient.setBirthdate(patdate);
                    patient.setJoptitle(patientjop);
                    patient.setGender(gender.getSelectedItem().toString().trim());
                    patient.setState(state.getSelectedItem().toString().trim());
                    patient.setCity(city.getSelectedItem().toString().trim());
                    patient.setType("patient");
                    patient.setImageurl("default");


                                FirebaseDatabase.getInstance().getReference("Patient")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
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