package com.example.medical.Pharmacy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medical.Doctor.Add_Rosheth.Class_Rosheth;
import com.example.medical.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Dialog_Fragment extends Fragment {

    String uid,id_Pat;
    Button button;
    EditText id;
    DatabaseReference reference;
    Query query;
    String disease_name,  state_discription,  medican1,  usage1,
            medican2,  usage2,  medican3,  usage3,  required_analysis,  required_rayes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View root= inflater.inflate(R.layout.dialog_id, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));

        id=root.findViewById(R.id.xyz);

        button =root. findViewById(R.id.btn_id);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                id_Pat=id.getText().toString().trim();
                Log.e("op", id_Pat);

                if (id_Pat.isEmpty())
                {
                    id.setError("Input Patient ID");
                    return;
                }else {
                    reference= FirebaseDatabase.getInstance().getReference("Patient");
                    reference.orderByChild("number").equalTo(id_Pat).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            for (DataSnapshot ds : snapshot.getChildren()) {
                                uid = ds.getKey();
                               reference=FirebaseDatabase.getInstance().getReference("Patient").child(uid);
                               query=reference.child("Roshth").orderByKey().limitToLast(1);
                               query.addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                       Class_Rosheth class_rosheth = new Class_Rosheth();

                                       for (DataSnapshot ds : snapshot.getChildren()) {
                                           usage1 = ds.child("usage1").getValue().toString();
                                           disease_name = ds.child("disease_name").getValue().toString();
                                           state_discription = ds.child("state_discription").getValue().toString();
                                           medican1 = ds.child("medican1").getValue().toString();
                                           medican2 = ds.child("medican2").getValue().toString();
                                           usage2 = ds.child("usage2").getValue().toString();
                                           medican3 = ds.child("medican3").getValue().toString();
                                           usage3 = ds.child("usage3").getValue().toString();
                                           required_analysis = ds.child("required_analysis").getValue().toString();
                                           required_rayes = ds.child("required_analysis").getValue().toString();

                                           FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                           fragmentTransaction.replace(R.id.relative_pharm, new Show_Roshth_Fragment(disease_name,
                                                   state_discription,
                                                   medican1, usage1, medican2,
                                                   usage2, medican3, usage3,
                                                   required_analysis, required_rayes));
                                           fragmentTransaction.addToBackStack(null);
                                           fragmentTransaction.commit();
                                       }
                                   }
                                   @Override
                                   public void onCancelled(@NonNull @NotNull DatabaseError error) {
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

