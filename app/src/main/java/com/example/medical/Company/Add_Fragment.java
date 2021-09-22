package com.example.medical.Company;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medical.R;
import com.example.medical.Regist.Company;
import com.example.medical.Splash.SplashActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static java.lang.System.currentTimeMillis;


public class Add_Fragment extends Fragment {

    ImageView imageView1;
    static final int pick_img = 1;
    Uri imageuri1;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    private FirebaseUser user;
    EditText mname,phnum,mmail,cdisc,coname;
    Spinner Specialization_company,city_company;
    Button send;
    private StorageTask storageTask;
    ProgressBar progressBar;
    long maxid=0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_add_, container, false);
        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.white));



        mname=root.findViewById(R.id.mname);
        Specialization_company=root.findViewById(R.id.Specialization_company);
        city_company=root.findViewById(R.id.city_company);
        phnum=root.findViewById(R.id.phnum);
        mmail=root.findViewById(R.id.mmail);
        cdisc=root.findViewById(R.id.cdisc);
        coname=root.findViewById(R.id.coname);
        send=root.findViewById(R.id.send_ads);
        progressBar=root.findViewById(R.id.prograssbarads);


        databaseReference= FirebaseDatabase.getInstance().getReference("ADS");
        storageReference= FirebaseStorage.getInstance().getReference("pic");
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
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mname=mname.getText().toString().trim();
                String specialization_company=Specialization_company.getSelectedItem().toString().trim();
                String City_company=city_company.getSelectedItem().toString().trim();
                String Phnum=phnum.getText().toString().trim();
                String Mmail=mmail.getText().toString().trim();
                String Cdisc=cdisc.getText().toString().trim();
                String Coname=coname.getText().toString().trim();

                if (Mname.isEmpty()){
                    mname.setError("Input Medican Name");
                } else if (Mmail.isEmpty()){
                    mmail.setError("Input Company Mail");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(Mmail).matches()) {
                    mmail.setError("Provide valid email");
                    mmail.requestFocus();
                }else if (Phnum.isEmpty()){
                    phnum.setError("Input Company Number");
                }else if (Phnum.length()<11){
                    phnum.setError("U Number Uncorrected");
                }else if (Cdisc.isEmpty()){
                    cdisc.setError("Input Description");
                }else if (Coname.isEmpty()){
                    coname.setError("Input Company Name");
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    ADS_class ads_class=new ADS_class();
                    ads_class.setMname(Mname);
                    ads_class.setSpecialization_company(specialization_company);
                    ads_class.setCity_company(City_company);
                    ads_class.setPhnum(Phnum);
                    ads_class.setMmail(Mmail);
                    ads_class.setCdisc(Cdisc);
                    ads_class.setConame(Coname);
                    ads_class.setMname(Mname);
                    ads_class.setImageurl("default");

                    uploadimage();

                    databaseReference.child(String.valueOf(maxid+1)).setValue(ads_class).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful()){


                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Send Success", Toast.LENGTH_SHORT).show();

                                 mname.getText().clear();
                                phnum.getText().clear();
                                mmail.getText().clear();
                                cdisc.getText().clear();
                                coname.getText().clear();
                                imageView1.setImageBitmap(null);


                            }else {

                                Toast.makeText(getActivity(), "Failed To Send", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }
                        }
                    });




                }
                }
        });


        String [] values =
                {"Heart",
                        "Teeth", "Dermatology", "Eyes","Obstetrics and Gynecology",};
        Spinner spinner = (Spinner) root.findViewById(R.id.Specialization_company);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        city(root);


        imageView1=root.findViewById(R.id.image1_company);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(gallery , pick_img);


            }
        });



        return root;
    }

   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pick_img && resultCode == RESULT_OK && data != null) {
            imageuri1 = data.getData();

        }
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageuri1);
           imageView1.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadimage() {

        if (imageuri1!=null){

            Bitmap bitmap=null;
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageuri1);
            }catch (IOException e){
                e.printStackTrace();
            }
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            assert bitmap!=null;
            bitmap.compress(Bitmap.CompressFormat.JPEG,25,byteArrayOutputStream);

            byte[] imagfiletobyte=byteArrayOutputStream.toByteArray();
            final StorageReference imagerefrence=storageReference.child(currentTimeMillis()+".jpg");
            storageTask=imagerefrence.putBytes(imagfiletobyte);
            storageTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task ->{
                if (!task.isSuccessful()){
                    throw task.getException();

                }
                return imagerefrence.getDownloadUrl();
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Uri> task) {


                    if (task.isSuccessful()){

                        Uri downloaduri=task.getResult();
                        String sDownloaduri=downloaduri.toString();
                        ADS_class company=new ADS_class();
                        company.setImageurl(sDownloaduri);
                        databaseReference.child(String.valueOf(maxid)).child("imageurl").setValue(company.getImageurl());
                        final DatabaseReference profileimagerefrence=FirebaseDatabase.getInstance().getReference("pic");
                        profileimagerefrence.push().setValue(company).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if (task.isSuccessful()){

                                }else{
                                }
                            }
                        });

                    }else {

                        Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {

                }
            });
        }


    }


    public void city(View root){

        String [] values1 =
                {"Cairo",
                        "Alexandria", "Ismailia", "Aswan", "Assiut", "Luxor",
                        "Red Sea", "Al-Buhaira", "Beni Suef", "Port Said",
                        "South Sinai", "Giza", "Dakahlia", "Damietta",
                        "Sohag", "Suez", "Sharqia", "Gharbia", "Faiyum",
                        "Qalyubia", "Qena", "Kafr El Sheikh", "North Sinai",
                        "Matrouh", "Al-Manzafiya", "Minya", "the new Valley",};
        Spinner spinner1 = (Spinner) root.findViewById(R.id.city_company);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values1);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);





    }


}