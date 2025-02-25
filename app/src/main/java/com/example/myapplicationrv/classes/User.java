package com.example.myapplicationrv.classes;

import java.util.ArrayList;

public class User {
    private String email;
    private String phoneNumber;
    private ArrayList<String> favorites = new ArrayList<String>(); // Create an ArrayList object

    public User(String email, String phoneNumber, ArrayList<String> favorites) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.favorites = favorites;
    }
    public User(){ //empty constructor
        this.email = "";
        this.phoneNumber = "";
        this.favorites = new ArrayList<String>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<String> favorites) {
        this.favorites = favorites;
    }
}
