package com.example.medical.Doctor.Add_Rosheth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.medical.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class DrugFragment extends Fragment {


    EditText drug1, drug2, drug3, drug4, drug5, drug6,drugs_id;
    Button send;
    private DatabaseReference databaseReference;
    ProgressBar progressBar;
    long maxid=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_drug, container, false);

        drug1=(EditText) root.findViewById(R.id.drug1);
        drug2=(EditText)root.findViewById(R.id.drug2);
        drug3=(EditText)root.findViewById(R.id.drug3);
        drug4=(EditText)root.findViewById(R.id.drug4);
        drugs_id=(EditText)root.findViewById(R.id.patiendid_drugs);
        send=(Button) root.findViewById(R.id.send_drugs);
        progressBar=root.findViewById(R.id.prograssbar_drugs);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String drug11=drug1.getText().toString().trim();
                String drug22=drug2.getText().toString().trim();
                String drug33=drug3.getText().toString().trim();
                String drug44=drug4.getText().toString().trim();
                String drugs_id1=drugs_id.getText().toString().trim();

                if (drugs_id1.isEmpty())
                {
                    drugs_id.setError("Input Patient ID");
                    return;
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    databaseReference= (DatabaseReference) FirebaseDatabase.getInstance().getReference("Patient");


                    databaseReference.orderByChild("number").equalTo(drugs_id1).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String uid = ds.getKey();


                                databaseReference= FirebaseDatabase.getInstance().getReference("Patient").child(uid).child("Drugs");
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
                                class_rosheth.setDrug1(drug11);
                                class_rosheth.setDrug2(drug22);
                                class_rosheth.setDrug3(drug33);
                                class_rosheth.setDrug4(drug44);



                                databaseReference.child(String.valueOf(maxid+1)).setValue(class_rosheth).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            Toast.makeText(getActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            drugs_id.getText().clear();
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