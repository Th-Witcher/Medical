package com.example.medical.Doctor.Add_Rosheth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;

import com.example.medical.R;
import com.google.android.material.tabs.TabLayout;

public class View_patientActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    tabAdd add_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.blocked_color));

        tabLayout=findViewById(R.id.tab2);
        viewPager=findViewById(R.id.viewpager_patient);
        add_adapter=new tabAdd(getSupportFragmentManager());

        String id=getIntent().getStringExtra("user_id");

        add_adapter.addfragment(new Fragment1_roshth(id),"Prescription");
        add_adapter.addfragment(new Fragment2_cronic(id),"Chronic");
        add_adapter.addfragment(new Fragment3_cronic(id),"Banned Drugs");
        add_adapter.addfragment(new Fragment4_cronic(id),"Food Banned");


        viewPager.setAdapter(add_adapter);
        tabLayout.setupWithViewPager(viewPager);





    }
}