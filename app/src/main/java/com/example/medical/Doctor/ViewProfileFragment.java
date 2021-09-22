package com.example.medical.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.medical.Doctor.Add_Rosheth.Class_Rosheth;
import com.example.medical.Doctor.Add_Rosheth.View_patientActivity;
import com.example.medical.Pharmacy.PharmacyActivity;
import com.example.medical.Pharmacy.Show_Roshth_Fragment;
import com.example.medical.R;
import com.example.medical.Splash.SplashActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class ViewProfileFragment extends Fragment {

    Button enter;
    EditText editText;
    String id;
    String uid;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.viewprofile_fragment, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.shfaf2));

        editText=root.findViewById(R.id.inputid_viewprofile);


        enter=root.findViewById(R.id.btnid);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id=editText.getText().toString().trim();

                    Log.e("op", id);

                    if (id.isEmpty())
                    {
                        editText.setError("Input Patient ID");
                        return;
                    }else {
                        reference= FirebaseDatabase.getInstance().getReference("Patient");
                        reference.orderByChild("number").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    uid = ds.getKey();

                                   Intent intent=new Intent(getActivity(),View_patientActivity.class);
                                   intent.putExtra("user_id",uid);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                            }
                        });
                    }
                }





        });


        return root;
    }
}







