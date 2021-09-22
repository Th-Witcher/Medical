package com.example.medical.Company;

import android.content.Context;
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
import com.example.medical.R;

import java.util.ArrayList;


public class RV_Adapter2 extends RecyclerView.Adapter<RV_Adapter2.vholder> implements Filterable {

    ArrayList<ADS_class> adss;
    private ItemClickListener1 ClickListener;


    public RV_Adapter2(ArrayList<ADS_class> adss, ItemClickListener1 ClickListener) {
        this.adss = adss;
        this.ClickListener=ClickListener;
    }


    @NonNull
    @Override
    public vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_adapter_ads,null,false);
        vholder viewholder=new vholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull vholder holder, int position) {

        ADS_class A=adss.get(position);
        holder.tv_specialtyt.setText(A.getSpecialization_company());
        holder.tv_namet.setText(A.getMname());
        Glide.with(holder.imaget.getContext()).load(A.getImageurl()).into(holder.imaget);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Info_CronicFragment(A.getImageurl(),
                        A.getSpecialization_company(),A.getMname(),A.getConame(),A.getMmail(),A.getCdisc(),
                        A.getCity_company(),A.getPhnum())).addToBackStack(null).commit();

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

        public void onItemClick(int position);

    }



}
