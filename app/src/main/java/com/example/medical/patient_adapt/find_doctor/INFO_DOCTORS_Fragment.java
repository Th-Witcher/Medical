package com.example.medical.patient_adapt.find_doctor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.medical.R;


public class INFO_DOCTORS_Fragment extends Fragment {

    String  address,  phone,  mail,  name,  gender;



    public  INFO_DOCTORS_Fragment(){}

    public INFO_DOCTORS_Fragment(String address, String phone, String mail, String name, String gender) {
        // Required empty public constructor

        this.address=address;
        this.phone=phone;
        this.mail=mail;
        this.name=name;
        this.gender=gender;
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_i_n_f_o__d_o_c_t_o_r_s_, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.info_doc));



        TextView mail0=root.findViewById(R.id.d_mail);
        TextView address0=root.findViewById(R.id.d_address);
        TextView gender0=root.findViewById(R.id.d_gender);
        TextView phone0=root.findViewById(R.id.d_num);
        TextView name0=root.findViewById(R.id.d_name);


        mail0.setText(mail);
        address0.setText(address);
        phone0.setText(phone);
        gender0.setText(gender);
        name0.setText(name);



        return root;
    }
}