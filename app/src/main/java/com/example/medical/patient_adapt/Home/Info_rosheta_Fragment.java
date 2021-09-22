package com.example.medical.patient_adapt.Home;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medical.R;

import java.util.ArrayList;


public class Info_rosheta_Fragment extends Fragment {

    String chronicdisease1,  chronicdisease2,  conslution,  medican1,  disease_name,  medican2,
     medican3,  required_analysis,  required_rayes,
     state_discription,  usage1,  usage2,  usage3;

    public  Info_rosheta_Fragment(){}
    public Info_rosheta_Fragment(String chronicdisease1, String chronicdisease2, String conslution, String medican1, String disease_name, String medican2,
                                 String medican3, String required_analysis, String required_rayes,
                                 String state_discription, String usage1, String usage2, String usage3) {
        this.chronicdisease1=chronicdisease1;
        this.chronicdisease2=chronicdisease2;
        this.conslution=conslution;
        this.medican1=medican1;
        this.disease_name=disease_name;
        this.medican2=medican2;
        this.medican3=medican3;
        this.required_analysis=required_analysis;
        this.required_rayes=required_rayes;
        this.state_discription=state_discription;
        this.usage1=usage1;
        this.usage2=usage2;
        this.usage3=usage3;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_info_rosheta_, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));


        TextView disease_name1=root.findViewById(R.id.d1);
        TextView state_discription1=root.findViewById(R.id.d2);
        TextView medican11=root.findViewById(R.id.d3);
        TextView usage11=root.findViewById(R.id.d4);
        TextView medican21=root.findViewById(R.id.d5);
        TextView usage21=root.findViewById(R.id.d6);
        TextView medican31=root.findViewById(R.id.d7);
        TextView usage31=root.findViewById(R.id.d8);
        TextView required_analysis1=root.findViewById(R.id.d9);
        TextView required_rayes1=root.findViewById(R.id.d10);

         disease_name1.setText(disease_name);
         state_discription1.setText(state_discription);
         medican11.setText(medican1);
         usage11.setText(usage1);
         medican21.setText(medican2);
         usage21.setText(usage2);
         medican31.setText(medican3);
         usage31.setText(usage3);
         required_analysis1.setText(required_analysis);
         required_rayes1.setText(required_rayes);


        return root;
    }
}