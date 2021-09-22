package com.example.medical.patient_adapt.find_doctor;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medical.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DoctorFragment extends Fragment implements Doctor_Adapter.OnItemClickLitsener {

    RecyclerView recyclerViewd;

    Doctor_Adapter adapterd;
    DatabaseReference databaseReference;

    FirebaseDatabase database ;
    ArrayList<FDoctors> doctors ;


    TextView ontext1,ontext2,ontext3,ontext4,ontext5,ontext6,ontext7,ontext8,ontext9,ontext10,ontext11,ontext12,
            ontext13,ontext14,ontext15,ontext16,ontext17,ontext18,ontext19,ontext20,ontext21,
            ontext22,ontext23,ontext24,ontext25,ontext26,ontext27;

    CardView card1,card2,card3,card4,card5;

    String paper2="Heart";
    String paper1="Cairo";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_doctor, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));

        recyclerViewd=root.findViewById(R.id.recyclefind);
        database = FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Cairo").child("Heart");
        recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
        doctors=new ArrayList<>();
        adapterd=new Doctor_Adapter(getActivity(),doctors,this);

        recyclerViewd.setAdapter(adapterd);
         paper2="Heart";
         paper1="Cairo";
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                    doctors.add(fDoctors);
                }
                adapterd.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        onclick_textview(root);



        EditText editText=root.findViewById(R.id.textsearch);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }

            private void filter(String text) {

                ArrayList<FDoctors> filterlist=new ArrayList<>();

                for (FDoctors item1:doctors){

                    if(item1.getName().toLowerCase().contains(text.toLowerCase()) ){

                        filterlist.add(item1);
                    }
                }

                adapterd.Filterlist(filterlist);
            }
        });




        return root;
    }





    public void onclick_textview(View root){

        ontext1=root.findViewById(R.id.Ontext1);
        ontext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Cairo", Toast.LENGTH_SHORT)
                        .show();
                paper1="Cairo";
                paper2="Heart";

                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Cairo").child("Heart");

                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }

        });

        ontext2=root.findViewById(R.id.Ontext2);
        ontext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Giza", Toast.LENGTH_SHORT)
                        .show();
                paper1="Giza";
                paper2="Heart";
                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Giza").child("Heart");
                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });

        ontext3=root.findViewById(R.id.Ontext3);
        ontext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Ismailia", Toast.LENGTH_SHORT)
                        .show();
                paper1="Ismailia";
                paper2="Heart";

                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Ismailia").child("Heart");
                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }
        });

        ontext4=root.findViewById(R.id.Ontext4);
        ontext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Aswan", Toast.LENGTH_SHORT)
                        .show();
                paper1="Aswan";
                paper2="Heart";

                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Aswan").child("Heart");
                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });

        ontext5=root.findViewById(R.id.Ontext5);
        ontext5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Assiut", Toast.LENGTH_SHORT)
                        .show();
                paper1="Assiut";
                paper2="Heart";

                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Assiut").child("Heart");
                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }
        });

        ontext6=root.findViewById(R.id.Ontext6);
        ontext6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Luxor", Toast.LENGTH_SHORT)
                        .show();
                paper1="Luxor";
                paper2="Heart";

                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Luxor").child("Heart");
                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });

        ontext7=root.findViewById(R.id.Ontext7);
        ontext7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Red Sea", Toast.LENGTH_SHORT)
                        .show();
                paper1="Red Sea";
                paper2="Heart";

                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Red Sea").child("Heart");
                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }
        });

        ontext8=root.findViewById(R.id.Ontext8);
        ontext8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Al-Buhaira", Toast.LENGTH_SHORT)
                        .show();
                paper1="Al-Buhaira";
                paper2="Heart";

                databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Al-Buhaira").child("Heart");
                recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                doctors=new ArrayList<>();
                adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                recyclerViewd.setAdapter(adapterd);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                            doctors.add(fDoctors);
                        }
                        adapterd.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }
        });

        ontext9=root.findViewById(R.id.Ontext9);
        ontext9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Beni Suef", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext10=root.findViewById(R.id.Ontext10);
        ontext10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Port Said", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext11=root.findViewById(R.id.Ontext11);
        ontext11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "South Sinai", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext12=root.findViewById(R.id.Ontext12);
        ontext12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Alexandria", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext13=root.findViewById(R.id.Ontext13);
        ontext13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Dakahlia", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext14=root.findViewById(R.id.Ontext14);
        ontext14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Damietta", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext15=root.findViewById(R.id.Ontext15);
        ontext15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Sohag", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext16=root.findViewById(R.id.Ontext16);
        ontext16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Suez", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext17=root.findViewById(R.id.Ontext18);
        ontext17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Sharqia", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext18=root.findViewById(R.id.Ontext19);
        ontext18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Gharbia", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext19=root.findViewById(R.id.Ontext20);
        ontext19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Faiyum", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext20=root.findViewById(R.id.Ontext21);
        ontext20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Qalyubia", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        ontext21=root.findViewById(R.id.Ontext22);
        ontext21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Qena", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext22=root.findViewById(R.id.Ontext23);
        ontext22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Kafr El Sheikh", Toast.LENGTH_SHORT)
                        .show();


            }
        });

        ontext23=root.findViewById(R.id.Ontext24);
        ontext23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "North Sinai", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext24=root.findViewById(R.id.Ontext25);
        ontext24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Matrouh", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext25=root.findViewById(R.id.Ontext26);
        ontext25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Al-Manzafiya", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext26=root.findViewById(R.id.Ontext27);
        ontext26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText
                        (getActivity(), "Minya", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        ontext27=root.findViewById(R.id.Ontext28);
        ontext27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "the new Valley", Toast.LENGTH_SHORT)
                        .show();

            }
        });


        card1=root.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Heart", Toast.LENGTH_SHORT)
                        .show();

                paper2="Heart";

                if (paper1.equals("Cairo")){

                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Cairo").child("Heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }
                else if (paper1.equals("Giza")){

                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Giza").child("Heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }

                else if (paper1.equals("Ismailia"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Ismailia").child("heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }else if (paper1.equals("Aswan"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Aswan").child("Heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }else if (paper1.equals("Assiut"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Assiut").child("Heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Luxor"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Luxor").child("Heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Red Sea"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Red Sea").child("Heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Al-Buhaira"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Al-Buhaira").child("Heart");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }

            }
        });


        card2=root.findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Teeth", Toast.LENGTH_SHORT)
                        .show();
                paper2="Teeth";

                if (paper1.equals("Cairo"))
                {

                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Cairo").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                } else if (paper1.equals("Giza"))

                {

                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Giza").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                } else if (paper1.equals("Ismailia"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Ismailia").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }else if (paper1.equals("Aswan"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Aswan").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }else if (paper1.equals("Assiut"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Assiut").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Luxor"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Luxor").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Red Sea"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Red Sea").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Al-Buhaira"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Al-Buhaira").child("Teeth");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        card3=root.findViewById(R.id.card3);
                card3.setOnClickListener(new View.OnClickListener() {
                    @Override
                   public void onClick(View v) {
                        Toast.makeText
                                (getActivity(), "Dermatology", Toast.LENGTH_SHORT)
                                .show();
                        paper2="Dermatology";

                        if (paper1.equals("Cairo"))
                        {

                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Cairo").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });


                        } else if (paper1.equals("Giza"))

                        {

                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Giza").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });


                        } else if (paper1.equals("Ismailia"))

                        {
                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Ismailia").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });


                        }else if (paper1.equals("Aswan"))

                        {
                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Aswan").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });


                        }else if (paper1.equals("Assiut"))

                        {
                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Assiut").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                        }else if (paper1.equals("Luxor"))

                        {
                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Luxor").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                        }else if (paper1.equals("Red Sea"))

                        {
                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Red Sea").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                        }else if (paper1.equals("Al-Buhaira"))

                        {
                            databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Al-Buhaira").child("Dermatology");
                            recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                            doctors=new ArrayList<>();
                            adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                            recyclerViewd.setAdapter(adapterd);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                        FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                        doctors.add(fDoctors);
                                    }
                                    adapterd.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });
                        }


                   }
                });



        card4=root.findViewById(R.id.card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Eyes", Toast.LENGTH_SHORT)
                        .show();
                paper2="Eyes";

                if (paper1.equals("Cairo"))
                {

                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Cairo").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                } else if (paper1.equals("Giza"))

                {

                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Giza").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                } else if (paper1.equals("Ismailia"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Ismailia").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }else if (paper1.equals("Aswan"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Aswan").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }else if (paper1.equals("Assiut"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Assiut").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Luxor"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Luxor").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Red Sea"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Red Sea").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }else if (paper1.equals("Al-Buhaira"))

                {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Find").child("Al-Buhaira").child("Eyes");
                    recyclerViewd.setLayoutManager(new LinearLayoutManager(getActivity()));
                    doctors=new ArrayList<>();
                    adapterd=new Doctor_Adapter(getActivity(),doctors,DoctorFragment.this::onItemClick);
                    recyclerViewd.setAdapter(adapterd);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                FDoctors fDoctors=dataSnapshot.getValue(FDoctors.class);
                                doctors.add(fDoctors);
                            }
                            adapterd.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        card5=root.findViewById(R.id.card5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText
                        (getActivity(), "Obstetrics and Gynecology Specialist", Toast.LENGTH_SHORT)
                        .show();

            }
        });


    }



    @Override
    public void onItemClick(int position) {

    }
}