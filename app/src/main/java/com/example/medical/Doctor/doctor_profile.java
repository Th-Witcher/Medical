package com.example.medical.Doctor;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Company.Home_Company_Fragment;
import com.example.medical.Doctor.ADS.CompanyadsFragment;
import com.example.medical.Doctor.Add_Rosheth.AddRoshetaFragment;
import com.example.medical.R;
import com.example.medical.Splash.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import io.paperdb.Paper;


public class doctor_profile extends AppCompatActivity implements doctor_adapter.OnItemSelectedListener {


    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_COMPANY_ADS = 2;
    private static final int POS_VIEW_PROFILE = 3;
    private static final int POS_ADD_ROSHETA = 4;
    private static final int POS_LOGOUT = 6;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    Toolbar toolbar;
    TextView usermail,pasmail;
    public static final String useremail="Email";
    public static final String pass="Password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);


        Paper.init(this);
        usermail = findViewById(R.id.usermail);
        pasmail =findViewById(R.id.userpass);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_layout)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        doctor_adapter adapter = new doctor_adapter(Arrays.asList(

                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_COMPANY_ADS),
                createItemFor(POS_VIEW_PROFILE),
                createItemFor(POS_ADD_ROSHETA),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)

        ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.deawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_DASHBOARD);


    }

    private Drawer_item createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.blue))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.blue))
                .withSelectedTextTint(color(R.color.blue));
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    private String[] loadScreenTitles() {

        return getResources().getStringArray(R.array.Id_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {

        TypedArray ta = getResources().obtainTypedArray(R.array.Id_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();


        if (position==POS_DASHBOARD){

            DashBoardFragment dashBoardFragment=new DashBoardFragment();
            transaction.replace(R.id.activity_doctorr, dashBoardFragment);

            toolbar = findViewById(R.id.toolbar);
            toolbar.setBackgroundColor(getResources().getColor(R.color.blue));



        }
        else if (position==POS_COMPANY_ADS){

            CompanyadsFragment companyadsFragment =new CompanyadsFragment();
            transaction.replace(R.id.activity_doctorr, companyadsFragment);

            toolbar = findViewById(R.id.toolbar);
            toolbar.setBackgroundColor(getResources().getColor(R.color.white));
            toolbar.setTitle("Medicines Ads");
            toolbar.setTitleTextColor(R.color.black);

        }
        else if (position==POS_VIEW_PROFILE){

            ViewProfileFragment viewProfileFragment =new ViewProfileFragment();
            transaction.replace(R.id.activity_doctorr, viewProfileFragment);
            toolbar = findViewById(R.id.toolbar);
            toolbar.setBackgroundColor(getResources().getColor(R.color.shfaf2));
            toolbar.setTitle("");
        }
        else if (position==POS_ADD_ROSHETA){

            AddRoshetaFragment addRoshetaFragment =new AddRoshetaFragment();
            transaction.replace(R.id.activity_doctorr, addRoshetaFragment);

            toolbar = findViewById(R.id.toolbar);
            toolbar.setBackgroundColor(getResources().getColor(R.color.white));
            toolbar.setTitle("");
        }
        else if (position==POS_CLOSE){

            closeOptionsMenu();
        }else if (position==POS_LOGOUT){
            String usermail="";
            String pasmail="";

            Paper.book().write(useremail,usermail);
            Paper.book().write(pass,pasmail);
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(doctor_profile.this, SplashActivity.class));

        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }




}