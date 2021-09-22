package com.example.medical.Doctor.ADS;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medical.Company.ADS_class;
import com.example.medical.Doctor.doctor_profile;
import com.example.medical.R;
import com.example.medical.patient_adapt.Home.Info_rosheta_Fragment;
import com.example.medical.patient_adapt.Map.MapsFragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CompanyadsFragment extends Fragment implements RV_Adapter3.ItemClickListener1 {

    RecyclerView recyclerView2;

    RV_Adapter3 adapter2;

    DatabaseReference databaseReference;

    ArrayList<ADS_class> adss ;
    FirebaseDatabase database ;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_companyads, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));



        recyclerView2=root.findViewById(R.id.recycleview2);
        database = FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("ADS");
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adss=new ArrayList<>();
        adapter2=new RV_Adapter3(adss, this);

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


        EditText editText=root.findViewById(R.id.textsearch1);
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



    @Override
    public void onItemClick(ADS_class ads_class) {

        Intent intent = new Intent(getActivity(), Details_cronic.class);
        intent.putExtra("Cdisc",ads_class.getCdisc());
        intent.putExtra("City_company",ads_class.getCity_company());
        intent.putExtra("Coname",ads_class.getConame());
        intent.putExtra("Imageurl",ads_class.getImageurl());
        intent.putExtra("Mmail",ads_class.getMmail());
        intent.putExtra("Mname",ads_class.getMname());
        intent.putExtra("Phnum",ads_class.getPhnum());
        intent.putExtra("pecialization_company",ads_class.getSpecialization_company());
        startActivity(intent);

    }
}