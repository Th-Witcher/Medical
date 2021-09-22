package com.example.medical.patient_adapt.chronic;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.medical.R;
import com.example.medical.patient_adapt.Food.FoodFragment;
import com.example.medical.patient_adapt.chronic.ChronicFragment;
import com.example.medical.patient_adapt.chronic.blocked_Adapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import com.example.medical.patient_adapt.Drugs.DrugsFragment;

public class BlockedFragment extends Fragment {



    TabLayout tabLayout;
    ViewPager viewPager;
    AppBarLayout appBarLayout;
    blocked_Adapter blocked_adapter;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_blocked, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.blocked_color));
        tabLayout=root.findViewById(R.id.tab);
        viewPager=root.findViewById(R.id.viewpager);
        appBarLayout=root.findViewById(R.id.appbar);
        blocked_adapter=new blocked_Adapter(getChildFragmentManager());

        blocked_adapter.addfragment(new ChronicFragment(),"Chronic Diseases");
        blocked_adapter.addfragment(new DrugsFragment(),"Banned Drugs");
        blocked_adapter.addfragment(new FoodFragment(),"Food Banned");


        viewPager.setAdapter(blocked_adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));


        return root;
    }
}