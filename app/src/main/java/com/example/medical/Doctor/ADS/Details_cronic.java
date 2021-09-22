package com.example.medical.Doctor.ADS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medical.Company.ADS_class;
import com.example.medical.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Details_cronic extends AppCompatActivity {

    TextView mname,phnum,mmail,cdisc,coname,Specialization_company,city_company;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_cronic);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.detail_color));

        String disc=getIntent().getStringExtra("Cdisc");
        String city=getIntent().getStringExtra("City_company");
        String cname=getIntent().getStringExtra("Coname");
        String mail=getIntent().getStringExtra("Mmail");
        String name=getIntent().getStringExtra("Mname");
        String num=getIntent().getStringExtra("Phnum");
        String special=getIntent().getStringExtra("pecialization_company");
        String image=getIntent().getStringExtra("Imageurl");

        mname=findViewById(R.id.iname0);
        Specialization_company=findViewById(R.id.ispecial0);
        city_company=findViewById(R.id.icity0);
        phnum=findViewById(R.id.iphnum0);
        mmail=findViewById(R.id.imail0);
        cdisc=findViewById(R.id.idisc0);
        coname=findViewById(R.id.iconame0);
        imageView=findViewById(R.id.iimage1_company0);


        mname.setText(name);
        Specialization_company.setText(special);
        city_company.setText(city);
        phnum.setText(num);
        mmail.setText(mail);
        cdisc.setText(disc);
        coname.setText(cname);
        Glide.with(this).load(image).into(imageView);


    }






}