package com.example.medical.Doctor.Add_Rosheth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.R;
import com.example.medical.patient_adapt.Home.Info_rosheta_Fragment;
import com.example.medical.patient_adapt.chronic.chronic_Adapter;

import java.util.ArrayList;

public class RV_2 extends RecyclerView.Adapter<RV_2.myviewholder>{

    ArrayList<Class_Rosheth>chronic_items;

    public RV_2(ArrayList<Class_Rosheth>chronicItems){

        this.chronic_items=chronicItems;
    }

    @NonNull
    @Override
    public RV_2.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.chronic_item,null,false);
        RV_2.myviewholder viewholder=new RV_2.myviewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RV_2.myviewholder vholder, int position) {
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
