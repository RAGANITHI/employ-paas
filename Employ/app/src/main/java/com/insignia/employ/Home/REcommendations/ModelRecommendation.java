package com.insignia.employ.Home.REcommendations;

public class ModelRecommendation {

    String companylogo, kenburnlogo, work, fee,location,companyname;

    public ModelRecommendation() {
    }

    public ModelRecommendation(String companylogo, String kenburnlogo, String work, String fee, String location, String companyname) {
        this.companylogo = companylogo;
        this.kenburnlogo = kenburnlogo;
        this.work = work;
        this.fee = fee;
        this.location = location;
        this.companyname = companyname;
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
    }

    public String getKenburnlogo() {
        return kenburnlogo;
    }

    public void setKenburnlogo(String kenburnlogo) {
        this.kenburnlogo = kenburnlogo;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
