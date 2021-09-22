package com.example.medical.Regist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical.Company.CompanyActivity;
import com.example.medical.R;
import com.example.medical.Splash.SplashActivity;
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

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;


public class CompFragment extends Fragment  {

    private TextView mDisplayDate1;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    EditText comname,commail,comnumber,comaddress,compdescription,compass,comconfirm;
    TextView comdate;
    private FirebaseAuth mAuth;
    Company company;
    ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_comp, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.splash_color));
        mAuth = FirebaseAuth.getInstance();

        Button button=root.findViewById(R.id.regcompany);
        comname=root.findViewById(R.id.namecompany);
        commail=root.findViewById(R.id.emailcompany);
        comnumber=root.findViewById(R.id.companynumber);
        comdate=root.findViewById(R.id.datecompany);
        compdescription=root.findViewById(R.id.Descriptio);
        comaddress=root.findViewById(R.id.comAddress);
        compass=root.findViewById(R.id.pass_comp);
        comconfirm=root.findViewById(R.id.confirmpas_comp);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Companyname=comname.getText().toString().trim();
                String comemail=commail.getText().toString().trim();
                String Number=comnumber.getText().toString().trim();
                String Description=compdescription.getText().toString().trim();
                String Address=comaddress.getText().toString().trim();
                String Establishdate=comdate.getText().toString().trim();
                String compassword=compass.getText().toString().trim();
                String cconfirm=comconfirm.getText().toString().trim();
                progressBar=root.findViewById(R.id.prograssbar1);


                if (Companyname.isEmpty()){
                    comname.setError("Input Company Name");
                } else if (comemail.isEmpty()){
                    commail.setError("Input Company Mail");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(comemail).matches()) {
                    commail.setError("Provide valid email");
                    commail.requestFocus();
                }else if (Number.isEmpty()){
                    comnumber.setError("Input Company Number");
                }else if (Number.length()<11){
                    comnumber.setError("U Number Uncorrected");
                }else if (Address.isEmpty()){
                    comaddress.setError("Input Address");
                }
                else if (Description.isEmpty()){
                    compdescription.setError("Input Address");
                }else if (compassword.isEmpty()){
                    compass.setError("Input U Password");
                } else if (compassword.length()<6){
                    compass.setError("U Password Week");
                }else if (!cconfirm.equals(compassword)){
                    comconfirm.setError("Password Not matching");
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(comemail,compassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                company=new Company();
                                company.setCompanyName(Companyname);
                                company.setComemail(comemail);
                                company.setNumber(Number);
                                company.setDescription(Description);
                                company.setComaddress(Address);
                                company.setEstablishdate(Establishdate);
                                company.setType("company");
                                company.setImageurl("default");


                                FirebaseDatabase.getInstance().getReference("Company")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(company).addOnCompleteListener(new OnCompleteListener<Void>() {
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


        datecomp(root);

        return root;
    }

    private void datecomp(View root) {

        mDisplayDate1 = root.findViewById(R.id.datecompany);

        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(getActivity()), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date1 = month + "/" + day + "/" + year;
                mDisplayDate1.setText(date1);
            }
        };
    }

}