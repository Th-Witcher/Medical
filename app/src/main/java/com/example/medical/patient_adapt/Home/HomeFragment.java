package com.example.medical.patient_adapt.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Company.ADS_class;
import com.example.medical.Company.RV_Adapter2;
import com.example.medical.Doctor.Add_Rosheth.Class_Rosheth;
import com.example.medical.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RV_Adapter.ItemClickListener {


    RecyclerView recyclerView;
    ArrayList<Class_Rosheth> roshths ;
    RV_Adapter rv_adapter;
    DatabaseReference databaseReference;
    FirebaseUser user;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));


        recyclerView=root.findViewById(R.id.recycleview_patient);
        user=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Patient").child(user.getUid()).child("Roshth");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        roshths=new ArrayList<>();
        rv_adapter=new RV_Adapter(roshths,this);

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