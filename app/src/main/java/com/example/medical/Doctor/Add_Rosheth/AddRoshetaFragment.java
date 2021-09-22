package com.example.medical.Doctor.Add_Rosheth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.medical.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class AddRoshetaFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
    tabAdd add_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.addrosheta_fragment, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));


        tabLayout=root.findViewById(R.id.tab1);
        viewPager=root.findViewById(R.id.viewpager1);
        add_adapter=new tabAdd(getChildFragmentManager());

        add_adapter.addfragment(new RoshthFragment(),"Add Prescription");
        add_adapter.addfragment(new DrugFragment(),"Banned Drugs");
        add_adapter.addfragment(new foodsFragment(),"Food Banned");


        viewPager.setAdapter(add_adapter);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }
}
