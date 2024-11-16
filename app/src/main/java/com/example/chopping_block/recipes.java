package com.example.chopping_block;

public class recipes {
    //3 items per database recipe
    private String photoUrl;
    private String description;
    private String title;

    //empty constructor required for Firebase Realtime Database
    public recipes() {}

    public recipes(String photoUrl, String description, String title) {
        this.photoUrl = photoUrl;
        this.description = description;
        this.title = title;
    }

    //getters and setters from original recipes
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
