package com.example.medical.Doctor.Add_Rosheth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.medical.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class foodsFragment extends Fragment {

    EditText food1, food2, food3, food4, food5, food6,food_id;
    Button send;
    private DatabaseReference databaseReference;
    ProgressBar progressBar;
    long maxid=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_foods, container, false);


        food1=(EditText) root.findViewById(R.id.food1);
        food2=(EditText)root.findViewById(R.id.food2);
        food3=(EditText)root.findViewById(R.id.food3);
        food4=(EditText)root.findViewById(R.id.food4);
        food_id=(EditText)root.findViewById(R.id.patiendid_food);
        send=(Button) root.findViewById(R.id.send_food);
        progressBar=root.findViewById(R.id.prograssbar_food);




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food11=food1.getText().toString().trim();
                String food22=food2.getText().toString().trim();
                String food33=food3.getText().toString().trim();
                String food44=food4.getText().toString().trim();
                String food_id1=food_id.getText().toString().trim();

                if (food_id1.isEmpty())
                {
                    food_id.setError("Input Patient ID");
                    return;
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    databaseReference= (DatabaseReference) FirebaseDatabase.getInstance().getReference("Patient");


                    databaseReference.orderByChild("number").equalTo(food_id1).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String uid = ds.getKey();

                                databaseReference= FirebaseDatabase.getInstance().getReference("Patient").child(uid).child("Food");
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                                        if (snapshot.exists())
                                            maxid=(snapshot.getChildrenCount());



                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                                Class_Rosheth class_rosheth=new Class_Rosheth();
                                class_rosheth.setFood1(food11);
                                class_rosheth.setFood2(food22);
                                class_rosheth.setFood3(food33);
                                class_rosheth.setFood4(food44);


                                databaseReference.child(String.valueOf(maxid+1)).setValue(class_rosheth).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            Toast.makeText(getActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            food_id.getText().clear();
                                        }

                                    }
                                });


                            }



                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });




                }


            }
        });

        return  root;
    }
}