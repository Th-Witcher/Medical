package com.example.medical.Doctor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.medical.R;
import com.example.medical.Regist.Doctors;
import com.example.medical.Regist.Patient;
import com.example.medical.patient_adapt.find_doctor.FDoctors;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

import static android.app.Activity.RESULT_OK;
import static java.lang.System.currentTimeMillis;

public class DashBoardFragment extends Fragment {

    CircleImageView imageView1;
    static final int pick_img = 1;
    Uri imageuri1;
    ImageButton imageButton;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userid;
    TextView name,city,phone,mail,gender,address,specialize,careerinfo;
    private StorageTask storageTask;
    StorageReference storageReference;
    public static final String Email="Email";
    public static final String pass="Password";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.blue));

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Doctors");
        storageReference= FirebaseStorage.getInstance().getReference("pic");
        userid=user.getUid();

        name=root.findViewById(R.id.namedoc);
        city=root.findViewById(R.id.doc_city);
        phone=root.findViewById(R.id.doc_num);
        mail=root.findViewById(R.id.doc_mail);
        gender=root.findViewById(R.id.doc_gender);
        address=root.findViewById(R.id.doc_address);
        careerinfo=root.findViewById(R.id.doc_info);
        specialize=root.findViewById(R.id.doc_specialize);
        imageView1=root.findViewById(R.id.cicleimage_doc);

        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Doctors docprofile=snapshot.getValue(Doctors.class);
                if (docprofile!=null)
                {

                    name.setText(docprofile.getName());
                    city.setText(docprofile.getCity());
                    mail.setText(docprofile.getEmail());
                    phone.setText(docprofile.getNumber());
                    city.setText(docprofile.getCity());
                    gender.setText(docprofile.getGender());
                    address.setText(docprofile.getAddress());
                    specialize.setText(docprofile.getSpecial());
                    careerinfo.setText(docprofile.getInfo());
                    if(docprofile.getImageurl().equals("default")){
                        imageView1.setImageResource(R.drawable.backdoc);

                    }else{


                        Glide.with(getActivity().getApplicationContext()).load(docprofile.getImageurl()).into(imageView1);

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                View mview=LayoutInflater.from(getActivity()).inflate(R.layout.select_image,null);

                Button openimage=mview.findViewById(R.id.btn_opemimages);
                openimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openimage();
                    }


                });

                builder.setView(mview);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });




        return root;
    }

    private void openimage() {



        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(gallery, pick_img);

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData()!=null  && data!=null && resultCode==RESULT_OK && requestCode==pick_img){
            imageuri1=data.getData();

            if (storageTask!=null && storageTask.isInProgress()){

                Toast.makeText(getActivity(),"Uploading",Toast.LENGTH_LONG).show();
            }else {
                uploadimage();

            }
        }
    }

    private void uploadimage() {
        ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading Image");
        progressDialog.show();

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
                        Doctors doctors=new Doctors();
                        doctors.setImageurl(sDownloaduri);
                        reference.child(user.getUid()).child("imageurl").setValue(doctors.getImageurl());
                        Glide.with(getActivity().getApplicationContext()).load(doctors.getImageurl()).into(imageView1);

                        final DatabaseReference profileimagerefrence=FirebaseDatabase.getInstance().getReference("pic").child(user.getUid());
                        profileimagerefrence.push().setValue(doctors).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    progressDialog.dismiss();
                                }else{
                                    progressDialog.dismiss();
                                }
                            }
                        });

                    }else {

                        Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {

                }
            });
        }


    }







}
