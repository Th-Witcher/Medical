package com.example.medical.patient_adapt.chronic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Doctor.Add_Rosheth.Class_Rosheth;
import com.example.medical.R;

import java.util.ArrayList;

public class chronic_Adapter extends RecyclerView.Adapter<chronic_Adapter.myviewholder> {

    ArrayList<Class_Rosheth>chronic_items;

    public chronic_Adapter(ArrayList<Class_Rosheth>chronicItems){

        this.chronic_items=chronicItems;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.chronic_item,null,false);
        chronic_Adapter.myviewholder viewholder=new chronic_Adapter.myviewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder vholder, int position) {
        Class_Rosheth c=chronic_items.get(position);
        vholder.cronic1.setText(c.getChronicdisease1());
        vholder.cronic2.setText(c.getChronicdisease2());

    }

    @Override
    public int getItemCount() {
        return chronic_items.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView cronic1,cronic2;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            cronic1=itemView.findViewById(R.id.cronic1);
            cronic2=itemView.findViewById(R.id.cronic2);
        }
    }
}
