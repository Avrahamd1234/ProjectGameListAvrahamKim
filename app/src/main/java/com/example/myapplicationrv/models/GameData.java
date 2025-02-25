package com.example.myapplicationrv.models;

public class GameData {

    //TextView gameName;

    private String gameName;
    private int rating;
    private int image;
    private int price;
    private String genre;
    private String description;
    private String videoURL;

    public GameData(String gameName, int rating, int image, int price, String genre, String description, String videoURL) {
        this.gameName = gameName;
        this.rating = rating;
        this.image = image;
        this.price = price;
        this.genre = genre;
        this.description = description;
        this.videoURL = videoURL;
    }
    public GameData() {
        this.gameName = "";
        this.rating = 0;
        this.image = 0;
        this.price = 0;
        this.genre = "";
        this.description = "";
        this.videoURL = "";
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

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int id) {
        this.price = id;
    }
}
