package com.example.medical.patient_adapt.Food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Doctor.Add_Rosheth.Class_Rosheth;
import com.example.medical.R;
import java.util.ArrayList;

public class Food_Adapter extends RecyclerView.Adapter<Food_Adapter.VH_FOOD> {

    ArrayList<Class_Rosheth> food_items;

    public Food_Adapter( ArrayList<Class_Rosheth> food_items){

        this.food_items=food_items;
    }

    @NonNull
    @Override
    public Food_Adapter.VH_FOOD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_drugs,null,false);
        Food_Adapter.VH_FOOD viewholder=new Food_Adapter.VH_FOOD(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Food_Adapter.VH_FOOD vholder, int position) {
        Class_Rosheth F=food_items.get(position);
        vholder.f1.setText(F.getFood1());
        vholder.f2.setText(F.getFood2());
        vholder.f3.setText(F.getFood3());
        vholder.f4.setText(F.getFood4());

        vholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return food_items.size();
    }

    class VH_FOOD extends RecyclerView.ViewHolder{
        TextView f1,f2,f3,f4;

        public VH_FOOD(@NonNull View itemView) {
            super(itemView);
            f1=itemView.findViewById(R.id.drugs1);
            f2=itemView.findViewById(R.id.drugs2);
            f3=itemView.findViewById(R.id.drugs3);
            f4=itemView.findViewById(R.id.drugs4);
        }
    }
}
