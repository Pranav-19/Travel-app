package com.example.finallogin;

public class City {


    private String Title;
    private String Description;
    private String Category;
    private int Thumbnail;

    public City() {

    }

    public City(String title, String description, String category, int thumbnail) {
        Title = title;
        Description = description;
        Category = category;
        Thumbnail = thumbnail;

    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategory() {
        return Category;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
