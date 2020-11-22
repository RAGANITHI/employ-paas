package com.example.employer.search;

public class Modelsubsearch {
String name,image,wages,work;

    public Modelsubsearch() {
    }

    public Modelsubsearch(String name, String image, String wages, String work) {
        this.name = name;
        this.image = image;
        this.wages = wages;
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWages() {
        return wages;
    }

    public void setWages(String wages) {
        this.wages = wages;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
