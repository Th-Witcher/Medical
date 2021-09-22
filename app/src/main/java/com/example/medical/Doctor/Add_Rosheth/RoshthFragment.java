package com.example.medical.Doctor.Add_Rosheth;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical.Doctor.doctor_profile;
import com.example.medical.R;
import com.example.medical.Splash.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;


public class RoshthFragment extends Fragment {

    private TextView conslution;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "RoshthFragment";

    EditText disease_name, state_discription, required_analysis, required_rayes, medican1, usage1, medican2, usage2, medican3, usage3, chronicdisease1, chronicdisease2, patientid;
    Button send;

    private DatabaseReference databaseReference;
    ProgressBar progressBar;
    long maxid=0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_roshth, container, false);

        disease_name=(EditText) root.findViewById(R.id.Disease_Name);
        state_discription=(EditText)root.findViewById(R.id.state_discription);
        required_analysis=(EditText)root.findViewById(R.id.required_analysis);
        required_rayes=(EditText)root.findViewById(R.id.required_rayes);
        medican1=(EditText)root.findViewById(R.id.medican1);
        medican2=(EditText)root.findViewById(R.id.medican2);
        usage2=(EditText)root.findViewById(R.id.usage2);
        usage1=(EditText)root.findViewById(R.id.usage1);
        medican3=(EditText)root.findViewById(R.id.medican3);
        usage3=(EditText)root.findViewById(R.id.usage3);
        conslution=(TextView) root.findViewById(R.id.conslution);
        chronicdisease1=(EditText)root.findViewById(R.id.chronicdisease1);
        chronicdisease2=(EditText)root.findViewById(R.id.chronicdisease2);
        patientid=(EditText)root.findViewById(R.id.patiendid);
        send=(Button) root.findViewById(R.id.sendrosheth);



        conslution.setOnClickListener(new View.OnClickListener() {
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
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                conslution.setText(date);
            }
        };



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String disease_name0=disease_name.getText().toString().trim();
                String state_discription0=state_discription.getText().toString().trim();
                String required_analysis0=required_analysis.getText().toString().trim();
                String required_rayes0=required_rayes.getText().toString().trim();
                String medican10=medican1.getText().toString().trim();
                String medican20=medican2.getText().toString().trim();
                String usage20=usage2.getText().toString().trim();
                String usage10=usage1.getText().toString().trim();
                String medican30=medican3.getText().toString().trim();
                String usage30=usage3.getText().toString().trim();
                String conslution0=conslution.getText().toString().trim();
                String chronicdisease10=chronicdisease1.getText().toString().trim();
                String chronicdisease20=chronicdisease2.getText().toString().trim();
                String patientid0=patientid.getText().toString().trim();
                progressBar=root.findViewById(R.id.prograssbar_rosheth);

                if (patientid0.isEmpty())
                {
                    patientid.setError("Input Patient ID");
                    return;
                }else {
                    progressBar.setVisibility(View.VISIBLE);databaseReference= (DatabaseReference) FirebaseDatabase.getInstance().getReference("Patient");


                    databaseReference.orderByChild("number").equalTo(patientid0).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String uid = ds.getKey();

                                databaseReference= FirebaseDatabase.getInstance().getReference("Patient").child(uid).child("Roshth");
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                                        if (snapshot.exists())
                                            maxid=(snapshot.getChildrenCount());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                                Class_Rosheth class_rosheth=new Class_Rosheth();
                                class_rosheth.setDisease_name(disease_name0);
                                class_rosheth.setState_discription(state_discription0);
                                class_rosheth.setRequired_analysis(required_analysis0);
                                class_rosheth.setRequired_rayes(required_rayes0);
                                class_rosheth.setMedican1(medican10);
                                class_rosheth.setMedican2(medican20);
                                class_rosheth.setUsage2(usage20);
                                class_rosheth.setUsage1(usage10);
                                class_rosheth.setMedican3(medican30);
                                class_rosheth.setUsage3(usage30);
                                class_rosheth.setConslution(conslution0);
                                class_rosheth.setChronicdisease1(chronicdisease10);
                                class_rosheth.setChronicdisease2(chronicdisease20);
                                databaseReference.child(String.valueOf(maxid+1)).setValue(class_rosheth).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            Toast.makeText(getActivity(), "Send Success", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            patientid.getText().clear();
                                        }

                                    }
                                });


                            }



                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }


            }
        });

        return root;
    }

}
