package com.example.medical.Company;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.example.medical.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Home_Company_Fragment extends Fragment implements RV_Adapter2.ItemClickListener1 {

    RecyclerView recyclerView2;

    RV_Adapter2 adapter2;

    DatabaseReference databaseReference;

    ArrayList<ADS_class> adss ;
    FirebaseDatabase database ;
    ADS_class ad;








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home__company_, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));



        recyclerView2=root.findViewById(R.id.recycleview_company);
        database = FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("ADS");
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adss=new ArrayList<>();
        adapter2=new RV_Adapter2(adss,this);

        recyclerView2.setAdapter(adapter2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    ADS_class ad=dataSnapshot.getValue(ADS_class.class);
                    adss.add(ad);
                }
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        EditText editText=root.findViewById(R.id.textsearch_company);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }

            private void filter(String text) {

                ArrayList<ADS_class>filterlist=new ArrayList<>();

                for (ADS_class item:adss){

                    if(item.getSpecialization_company().toLowerCase().contains(text.toLowerCase())){

                        filterlist.add(item);
                    }
                }

                adapter2.Filterlist(filterlist);
            }
        });
        return root;
    }






    public void onItemClick(int position) {




    }
}