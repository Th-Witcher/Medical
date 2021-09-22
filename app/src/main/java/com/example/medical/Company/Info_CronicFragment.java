package com.example.medical.Company;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medical.R;
import com.example.medical.patient_adapt.find_doctor.DoctorFragment;

public class Info_CronicFragment extends Fragment {

    String mname,phnum,mmail,cdisc,coname,Specialization_company,city_company;
    String imageView;

    public Info_CronicFragment(){}
    public Info_CronicFragment(String imageView, String Specialization_company, String mname, String coname, String mmail, String cdisc, String city_company, String phnum) {

        this.mname=mname;
        this.imageView=imageView;
        this.Specialization_company=Specialization_company;
        this.coname=coname;
        this.mmail=mmail;
        this.cdisc=cdisc;
        this.city_company=city_company;
        this.phnum=phnum;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_info__cronic, container, false);


        TextView exist=root.findViewById(R.id.existc);
        exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Home_Company_Fragment()).addToBackStack(null).commit();

            }
        });
        TextView mname0=root.findViewById(R.id.iname);
        TextView Specialization_company0=root.findViewById(R.id.ispecial);
        TextView city_company0=root.findViewById(R.id.icity);
        TextView phnum0=root.findViewById(R.id.iphnum);
        TextView mmail0=root.findViewById(R.id.imail);
        TextView cdisc0=root.findViewById(R.id.idisc);
        TextView coname0=root.findViewById(R.id.iconame);
        ImageView imageView0=root.findViewById(R.id.iimage1_company);

        mname0.setText(mname);
        Specialization_company0.setText(Specialization_company);
        city_company0.setText(city_company);
        phnum0.setText(phnum);
        mmail0.setText(mmail);
        cdisc0.setText(cdisc);
        coname0.setText(coname);
        Glide.with(getContext()).load(imageView).into(imageView0);



        return root;
    }

}