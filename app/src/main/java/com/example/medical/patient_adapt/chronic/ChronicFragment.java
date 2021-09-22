package com.example.medical.patient_adapt.chronic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medical.Doctor.Add_Rosheth.Class_Rosheth;
import com.example.medical.R;
import com.example.medical.patient_adapt.Food.Food_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ChronicFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Class_Rosheth> roshths ;
    chronic_Adapter rv_adapter;
    DatabaseReference databaseReference;
    FirebaseUser user;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_drugs, container, false);
        recyclerView =root.findViewById(R.id.recycledrugs);
        user= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Patient").child(user.getUid()).child("Roshth");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        roshths=new ArrayList<>();
        rv_adapter=new chronic_Adapter(roshths);

        recyclerView.setAdapter(rv_adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Class_Rosheth ad = dataSnapshot.getValue(Class_Rosheth.class);
                    roshths.add(ad);

                }
                rv_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return root;
    }
}