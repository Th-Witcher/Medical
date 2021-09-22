package com.example.medical.Company;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.medical.R;

public class CompanyActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        meowBottomNavigation=findViewById(R.id.bottom_navigation);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.home_icon));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_plus));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.profile2));


        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment=null;

                switch (item.getId()){

                    case 1:
                        fragment=new Home_Company_Fragment();
                        break;

                    case 2:
                        fragment=new Add_Fragment();
                        break;
                    case 3:
                        fragment=new Profile_Company_Fragment();
                        break;
                }
                loadfragment(fragment);
            }
        });

        meowBottomNavigation.setCount(1,"10");

        meowBottomNavigation.show(1,true);

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void loadfragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {


    }
}