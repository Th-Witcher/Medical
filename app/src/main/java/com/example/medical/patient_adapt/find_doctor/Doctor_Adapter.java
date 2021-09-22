package com.example.medical.patient_adapt.find_doctor;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.medical.R;

import java.util.ArrayList;

public class Doctor_Adapter extends RecyclerView.Adapter<Doctor_Adapter.VHdoctor> {


    ArrayList<FDoctors> doctors;
    Context context;
    OnItemClickLitsener onItemClickLitsener;



    public Doctor_Adapter(Context context,ArrayList<FDoctors> doctors,OnItemClickLitsener onItemClickLitsener) {
        this.doctors=doctors;
        this.context=context;
        this.onItemClickLitsener=onItemClickLitsener;
    }


    @NonNull
    @Override
    public Doctor_Adapter.VHdoctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_search_doctor,null,false);
        return new VHdoctor(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Doctor_Adapter.VHdoctor holder, int position) {
        FDoctors D=doctors.get(position);
        holder.address.setText(D.getAddress());
        holder.named.setText(D.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new INFO_DOCTORS_Fragment(D.getAddress(),D.getPhone(),
                       D.getMail(),D.getName(),
                        D.getGender())).addToBackStack(null).commit();




            }
        });

    }

    public void Filterlist(ArrayList<FDoctors>filterlist){
        doctors=filterlist;
        notifyDataSetChanged();
    }


    public Filter getFilter() {
        return null;
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }



    class VHdoctor extends RecyclerView.ViewHolder{
        TextView named,address;
        public VHdoctor(@NonNull View itemView) {
            super(itemView);
            named=itemView.findViewById(R.id.doctor_name);
            address=itemView.findViewById(R.id.doctor_number);


        }
    }

    public interface OnItemClickLitsener{

         void onItemClick(int position);

    }
}
