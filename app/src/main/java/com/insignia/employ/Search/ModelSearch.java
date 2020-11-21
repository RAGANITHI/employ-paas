package com.insignia.employ.Search;

public class ModelSearch {

    String imagelink,name;

    public ModelSearch() {
    }

    public ModelSearch(String imagelink, String name) {
        this.imagelink = imagelink;
        this.name = name;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
