package com.example.medical.Pharmacy;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medical.R;


public class Show_Roshth_Fragment extends Fragment {

    ImageButton button;
    TextView disease_name1, state_discription1,medican11,usage11,medican21,usage21,medican31,usage31,required_analysis1,required_rayes1;

    String disease_name,  state_discription,  medican1,  usage1,
     medican2,  usage2,  medican3,  usage3,  required_analysis,  required_rayes;

    public Show_Roshth_Fragment(String disease_name, String state_discription, String medican1, String usage1,
                                String medican2, String usage2, String medican3, String usage3, String required_analysis, String required_rayes) {


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
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_show_rosheth_, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));

        button=root.findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        disease_name1=root.findViewById(R.id.p1);
        state_discription1=root.findViewById(R.id.p2);
        medican11=root.findViewById(R.id.p3);
        usage11=root.findViewById(R.id.p4);
        medican21=root.findViewById(R.id.p5);
        usage21=root.findViewById(R.id.p6);
        medican31=root.findViewById(R.id.p7);
        usage31=root.findViewById(R.id.p8);
        required_analysis1=root.findViewById(R.id.p9);
        required_rayes1=root.findViewById(R.id.p10);

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