package com.example.medical.Doctor.ADS;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical.Company.ADS_class;
import com.example.medical.Company.Home_Company_Fragment;
import com.example.medical.Company.Info_CronicFragment;
import com.example.medical.R;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class RV_Adapter3 extends RecyclerView.Adapter<RV_Adapter3.vholder> implements Filterable {

    ArrayList<ADS_class> adss;
    private RV_Adapter3.ItemClickListener1 ClickListener;


    public RV_Adapter3(ArrayList<ADS_class> adss, RV_Adapter3.ItemClickListener1 ClickListener) {
        this.adss = adss;
        this.ClickListener=ClickListener;
    }


    @NonNull
    @Override
    public RV_Adapter3.vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_adapter_ads,null,false);
        RV_Adapter3.vholder viewholder=new RV_Adapter3.vholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Adapter3.vholder holder, int position) {

        ADS_class A=adss.get(position);
        holder.tv_specialtyt.setText(A.getSpecialization_company());
        holder.tv_namet.setText(A.getMname());
        Glide.with(holder.imaget.getContext()).load(A.getImageurl()).into(holder.imaget);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            ClickListener.onItemClick(adss.get(position));
            }
        });



    }

    @Override
    public int getItemCount() {
        return adss.size();
    }

    public void Filterlist(ArrayList<ADS_class>filterlist){
        adss=filterlist;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    class vholder extends RecyclerView.ViewHolder{
        TextView tv_namet,tv_specialtyt;
        ImageView imaget;

        public vholder(@NonNull View itemView) {
            super(itemView);
            tv_namet=itemView.findViewById(R.id.namemedicine);
            tv_specialtyt=itemView.findViewById(R.id.treatment_Specialty);
            imaget=itemView.findViewById(R.id.image_rosheth);

        }
    }
    public interface ItemClickListener1{

        void onItemClick(ADS_class ads_class);
    }
}
