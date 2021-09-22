package com.example.medical.Doctor.Add_Rosheth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.R;
import com.example.medical.patient_adapt.Drugs.drugs_Adapter;

import java.util.ArrayList;

public class RV_3 extends RecyclerView.Adapter<RV_3.MYviewholder>{

    ArrayList<Class_Rosheth> drugsitem;

    public RV_3(ArrayList<Class_Rosheth>drugs_items){

        this.drugsitem=drugs_items;
    }

    @NonNull
    @Override
    public RV_3.MYviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_drugs,null,false);
        RV_3.MYviewholder viewholder=new RV_3.MYviewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RV_3.MYviewholder vholder, int position) {
        Class_Rosheth c=drugsitem.get(position);
        vholder.drugs1.setText(c.getDrug1());
        vholder.drugs2.setText(c.getDrug2());
        vholder.drugs3.setText(c.getDrug3());
        vholder.drugs4.setText(c.getDrug4());


    }

    @Override
    public int getItemCount() {
        return drugsitem.size();
    }

    class MYviewholder extends RecyclerView.ViewHolder{
        TextView drugs1,drugs2,drugs3,drugs4;

        public MYviewholder(@NonNull View itemView) {
            super(itemView);
            drugs1=itemView.findViewById(R.id.drugs1);
            drugs2=itemView.findViewById(R.id.drugs2);
            drugs3=itemView.findViewById(R.id.drugs3);
            drugs4=itemView.findViewById(R.id.drugs4);

        }
    }
}
