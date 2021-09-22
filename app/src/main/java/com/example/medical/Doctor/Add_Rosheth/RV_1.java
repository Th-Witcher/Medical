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

import java.util.ArrayList;


public class RV_1 extends RecyclerView.Adapter<RV_1.vholder>{

    ArrayList<Class_Rosheth> roshths;
    private RV_1.ItemClickListener ClickListener;

    public RV_1(ArrayList<Class_Rosheth> roshths, RV_1.ItemClickListener ClickListener) {
        this.roshths = roshths;
        this.ClickListener=ClickListener;
    }


    @NonNull
    @Override
    public RV_1.vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_adapter,null,false);
        RV_1.vholder viewholder=new RV_1.vholder(v);
        return viewholder;
    }



    @Override
    public void onBindViewHolder(@NonNull RV_1.vholder holder, int position) {

        Class_Rosheth D=roshths.get(position);
        holder.tv_specialty.setText(D.getDisease_name());
        holder.tv_date.setText(D.getConslution());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.f1,new Fragment_5(D.getChronicdisease1(),D.getChronicdisease2(),
                        D.getConslution(),D.getMedican1(),
                        D.getDisease_name(),D.getMedican2(),D.getMedican3(),D.getRequired_analysis(),D.getRequired_rayes(),D.getState_discription(),D.getUsage1(),
                        D.getUsage2(),D.getUsage3())).addToBackStack(null).commit();


            }
        });


    }

    @Override
    public int getItemCount() {
        return roshths.size();
    }

    class vholder extends RecyclerView.ViewHolder{
        TextView tv_date,tv_specialty;

        public vholder(@NonNull View itemView) {
            super(itemView);
            tv_date=itemView.findViewById(R.id.Date);
            tv_specialty=itemView.findViewById(R.id.Specialty);
        }
    }

    public interface ItemClickListener{

        public void onItemClick(int position);

    }
}
