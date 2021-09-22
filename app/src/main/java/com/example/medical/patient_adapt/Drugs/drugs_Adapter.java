package com.example.medical.patient_adapt.Drugs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Doctor.Add_Rosheth.Class_Rosheth;
import com.example.medical.R;

import java.util.ArrayList;

public class drugs_Adapter extends RecyclerView.Adapter<drugs_Adapter.MYviewholder>{

    ArrayList<Class_Rosheth> drugsitem;

    public drugs_Adapter(ArrayList<Class_Rosheth>drugs_items){

        this.drugsitem=drugs_items;
    }

    @NonNull
    @Override
    public drugs_Adapter.MYviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_drugs,null,false);
        drugs_Adapter.MYviewholder viewholder=new drugs_Adapter.MYviewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull drugs_Adapter.MYviewholder vholder, int position) {
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

