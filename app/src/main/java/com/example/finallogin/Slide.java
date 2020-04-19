package com.example.finallogin;

import android.net.Uri;

public class Slide {

    private int Image;
    private String Title;
    private Uri Youtube_Uri;
    //more fields can be added

    public Slide(int image, String title, Uri youtube_Uri) {
        Image = image;
        Title = title;
        Youtube_Uri = youtube_Uri;

    }

    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public Uri getYoutube_Uri() {
        return Youtube_Uri;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setYoutube_Uri(Uri youtube_Uri) {
        Youtube_Uri = youtube_Uri;
    }
}
