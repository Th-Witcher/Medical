package com.example.medical.Pharmacy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.medical.R;
import com.example.medical.Regist.Pharmacy;
import com.example.medical.Splash.SplashActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import static java.lang.System.currentTimeMillis;


public class PharmacyActivity extends AppCompatActivity {

    Dialog dialog;
    FloatingActionButton fab_add,fab_out,fab_search;
    Animation fapopen,fabclose,fabfrom,fabto;
    boolean isopen=false;

    RelativeLayout relativeLayout;
    CircleImageView imageView1;
    static final int pick_img = 1;
    Uri imageuri1;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userid;
    TextView fname,lname,city,phone,mail,pharmname,docpharmname,usermail,pasmail;
    private StorageTask storageTask;
    StorageReference storageReference;
    public static final String useremail="Email";
    public static final String pass="Password";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pharmacy);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.pharm_color));

        Paper.init(this);
        usermail =findViewById(R.id.usermail);
        pasmail =findViewById(R.id.userpass);
        String usermail="";
        String pasmail="";



        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Pharmacy");
        storageReference= FirebaseStorage.getInstance().getReference("pic");
        userid=user.getUid();

        fname=findViewById(R.id.pharmfname);
        lname=findViewById(R.id.pharmlname);
        pharmname=findViewById(R.id.pharmname);
        city=findViewById(R.id.pharmcity);
        phone=findViewById(R.id.pharmnum);
        mail=findViewById(R.id.pharmmail);
        docpharmname=findViewById(R.id.pharmnamedoc);
        imageView1=findViewById(R.id.circleimage_pharm);



        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Pharmacy pharmprofile=snapshot.getValue(Pharmacy.class);
                if (pharmprofile!=null)
                {

                    fname.setText(pharmprofile.getFpharname());
                    lname.setText(pharmprofile.getFpharlname());
                    mail.setText(pharmprofile.getPharmmail());
                    phone.setText(pharmprofile.getPharmnum());
                    pharmname.setText(pharmprofile.getPharmname());
                    city.setText(pharmprofile.getPharmcity());
                    docpharmname.setText(pharmprofile.getDocphamname());
                    if(pharmprofile.getImageurl().equals("default")){
                        imageView1.setImageResource(R.drawable.backdoc);

                    }else{


                        Glide.with(PharmacyActivity.this.getApplicationContext()).load(pharmprofile.getImageurl()).into(imageView1);

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

                AlertDialog.Builder builder=new AlertDialog.Builder(PharmacyActivity.this);
                builder.setCancelable(true);
                View mview= LayoutInflater.from(PharmacyActivity.this).inflate(R.layout.select_image,null);

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

        fab_add=findViewById(R.id.plus_butn);
        fab_out=findViewById(R.id.out_butn);
        fab_search=findViewById(R.id.search_butn);

        fapopen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animate_file_pharmcy_open);
        fabclose= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animate_file_pharmcy_close);
        fabfrom= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animate_file_pharmcy_frombotm);
        fabto= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.to_botm_animate);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isopen)
                {
                    fab_out.startAnimation(fabclose);
                    fab_search.startAnimation(fabclose);
                    fab_add.startAnimation(fabfrom);

                    fab_out.setClickable(false);
                    fab_search.setClickable(false);

                    isopen=false;
                }

                else{

                    fab_out.startAnimation(fapopen);
                    fab_search.startAnimation(fapopen);
                    fab_add.startAnimation(fabto);

                    fab_out.setClickable(true);
                    fab_search.setClickable(true);

                    isopen=true;


                }

            }
        });

        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.relative_pharm, new Dialog_Fragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                fab_out.startAnimation(fabclose);
                fab_search.startAnimation(fabclose);
                fab_add.startAnimation(fabfrom);

                fab_out.setClickable(false);
                fab_search.setClickable(false);

                isopen=false;
            }
        });

        fab_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_out.startAnimation(fabclose);
                fab_search.startAnimation(fabclose);
                fab_add.startAnimation(fabfrom);

                fab_out.setClickable(false);
                fab_search.setClickable(false);

                isopen=false;
                Paper.book().write(useremail,usermail);
                Paper.book().write(pass,pasmail);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(PharmacyActivity.this, SplashActivity.class));

            }
        });

        relativeLayout=findViewById(R.id.relative_pharm);


        dialog = new Dialog(this);

    }




    private void openimage() {



        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(gallery, pick_img);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData()!=null  && data!=null && resultCode==RESULT_OK && requestCode==pick_img){
            imageuri1=data.getData();

            if (storageTask!=null && storageTask.isInProgress()){

                Toast.makeText(PharmacyActivity.this,"Uploading",Toast.LENGTH_LONG).show();
            }else {
                uploadimage();

            }
        }
    }

    private void uploadimage() {
        ProgressDialog progressDialog=new ProgressDialog(PharmacyActivity.this);
        progressDialog.setMessage("Uploading Image");
        progressDialog.show();

        if (imageuri1!=null){

            Bitmap bitmap=null;
            try {
                bitmap=MediaStore.Images.Media.getBitmap(PharmacyActivity.this.getContentResolver(),imageuri1);
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
                        Pharmacy pharmacy=new Pharmacy();
                        pharmacy.setImageurl(sDownloaduri);
                        reference.child(user.getUid()).child("imageurl").setValue(pharmacy.getImageurl());
                        Glide.with(PharmacyActivity.this.getApplicationContext()).load(pharmacy.getImageurl()).into(imageView1);

                        final DatabaseReference profileimagerefrence=FirebaseDatabase.getInstance().getReference("pic").child(user.getUid());
                        profileimagerefrence.push().setValue(pharmacy).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        Toast.makeText(PharmacyActivity.this,"Failed",Toast.LENGTH_LONG).show();
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
