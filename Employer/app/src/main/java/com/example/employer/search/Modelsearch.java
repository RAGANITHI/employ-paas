package com.example.employer.search;

public class Modelsearch {


    String name,image,treename;
    public Modelsearch() {
    }

    public Modelsearch(String name, String image, String treename) {
        this.name = name;
        this.image = image;
        this.treename = treename;
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

    public String getTreename() {
        return treename;
    }

    public void setTreename(String treename) {
        this.treename = treename;
    }
}
