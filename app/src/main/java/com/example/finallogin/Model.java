package com.example.finallogin;

import android.widget.ImageView;

public class Model {
    ImageView imageView;
    private String name;
    private String rating;
    private String lat,lng;

    public Model(ImageView imageView, String name, String rating,String lat, String lng) {
        this.imageView = imageView;
        this.name = name;
        this.rating = rating;
        this.lat=lat;
        this.lng=lng;

    }



    public ImageView getImageView() {
        return imageView;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
