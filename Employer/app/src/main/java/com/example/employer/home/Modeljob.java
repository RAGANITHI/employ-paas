package com.example.employer.home;

public class Modeljob {

    String companylogo,companyname,fee,kenburnlogo,location,work;


    public Modeljob() {
    }

    public Modeljob(String companylogo, String companyname, String fee, String kenburnlogo, String location, String work) {
        this.companylogo = companylogo;
        this.companyname = companyname;
        this.fee = fee;
        this.kenburnlogo = kenburnlogo;
        this.location = location;
        this.work = work;
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getKenburnlogo() {
        return kenburnlogo;
    }

    public void setKenburnlogo(String kenburnlogo) {
        this.kenburnlogo = kenburnlogo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
