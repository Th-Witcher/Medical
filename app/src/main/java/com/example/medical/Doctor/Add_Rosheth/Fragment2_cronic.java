package com.example.medical.Doctor.Add_Rosheth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medical.R;
import com.example.medical.patient_adapt.chronic.chronic_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Fragment2_cronic extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Class_Rosheth> roshths ;
    RV_2 rv_adapter;
    DatabaseReference databaseReference;

    String id;


    public Fragment2_cronic(String id) {
        this.id=id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_fragment2_cronic, container, false);

        recyclerView =root.findViewById(R.id.recycleview_patient2);
        databaseReference= FirebaseDatabase.getInstance().getReference("Patient").child(id).child("Roshth");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        roshths=new ArrayList<>();
        rv_adapter=new RV_2(roshths);

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