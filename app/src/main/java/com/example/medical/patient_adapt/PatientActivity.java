package com.example.medical.patient_adapt;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.medical.R;
import com.example.medical.patient_adapt.Home.HomeFragment;
import com.example.medical.patient_adapt.Map.MapsFragment;
import com.example.medical.patient_adapt.chronic.BlockedFragment;
import com.example.medical.patient_adapt.find_doctor.DoctorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PatientActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        loadFragment(new HomeFragment());




    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_blocked:
                    fragment = new BlockedFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_map:
                    fragment = new MapsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_doctors:
                    fragment = new DoctorFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fram_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}