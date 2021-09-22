package com.example.medical.Doctor.Add_Rosheth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medical.R;
import com.example.medical.patient_adapt.Home.RV_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Fragment1_roshth extends Fragment implements RV_1.ItemClickListener{

    String id;
    RecyclerView recyclerView;
    ArrayList<Class_Rosheth> roshths ;
    RV_1 rv_adapter;
    DatabaseReference databaseReference;



    public Fragment1_roshth(String id) {

        this.id=id;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_fragment1_roshth, container, false);

        recyclerView=root.findViewById(R.id.recycleview_patient1);
        databaseReference= FirebaseDatabase.getInstance().getReference("Patient").child(id).child("Roshth");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        roshths=new ArrayList<>();
        rv_adapter=new RV_1(roshths,this);

        recyclerView.setAdapter(rv_adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Class_Rosheth ad = dataSnapshot.getValue(Class_Rosheth.class);
                    roshths.add(ad);

                    Log.e("value", String.valueOf(ad));
                }
                rv_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return root;
    }

    @Override
    public void onItemClick(int position) {

    }
}