package com.example.medical.Doctor.Add_Rosheth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medical.Company.Home_Company_Fragment;
import com.example.medical.R;


public class Fragment_5 extends Fragment {

    String chronicdisease1,  chronicdisease2,  conslution,  medican1,  disease_name,  medican2,
            medican3,  required_analysis,  required_rayes,
            state_discription,  usage1,  usage2,  usage3;


    public Fragment_5(String chronicdisease1, String chronicdisease2, String conslution, String medican1,
                      String disease_name, String medican2, String medican3, String required_analysis,
                      String required_rayes, String state_discription, String usage1, String usage2, String usage3) {

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
        View root= inflater.inflate(R.layout.fragment_5, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.blocked_color));


        TextView disease_name1=root.findViewById(R.id.d11);
        TextView state_discription1=root.findViewById(R.id.d21);
        TextView medican11=root.findViewById(R.id.d31);
        TextView usage11=root.findViewById(R.id.d41);
        TextView medican21=root.findViewById(R.id.d51);
        TextView usage21=root.findViewById(R.id.d61);
        TextView medican31=root.findViewById(R.id.d71);
        TextView usage31=root.findViewById(R.id.d81);
        TextView required_analysis1=root.findViewById(R.id.d91);
        TextView required_rayes1=root.findViewById(R.id.d101);

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