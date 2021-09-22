package com.example.medical.patient_adapt.find_doctor;

public class FDoctors {


    private String name,address,gender,mail,phone,specialize,dcity;
    FDoctors(){

    }

    public FDoctors (String name,String address,String gender,String mail,String phone,String specialize,String city){

        this.name=name;
        this.address=address;
        this.mail=mail;
        this.gender=gender;
        this.phone=phone;
        this.dcity=city;
        this.specialize=specialize;



    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecialize() {
        return specialize;
    }

    public String getDcity() {
        return dcity;
    }
}

