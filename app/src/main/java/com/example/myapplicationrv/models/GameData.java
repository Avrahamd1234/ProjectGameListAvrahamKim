package com.example.myapplicationrv.models;

import android.net.Uri;

public class GameData {

    //TextView gameName;

    private String gameName;
    private int rating;
    private Uri image;
    private double price;
    private String genre;
    private String description;
    private Uri videoURI;

    public GameData(String gameName, int rating, Uri image, double price, String genre, String description, Uri videoURI) {
        this.gameName = gameName;
        this.rating = rating;
        this.image = image;
        this.price = price;
        this.genre = genre;
        this.description = description;
        this.videoURI = videoURI;
    }
    public GameData() {
        this.gameName = "";
        this.rating = 0;
        this.image = Uri.parse("");
        this.price = 0;
        this.genre = "";
        this.description = "";
        this.videoURI = Uri.parse("");
    }

    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getVideoURL() {
        return videoURI;
    }

    public void setVideoURL(Uri videoURI) {
        this.videoURI = videoURI;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int id) {
        this.price = id;
    }
}
