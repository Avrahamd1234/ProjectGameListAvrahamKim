package com.example.myapplicationrv.classes;

import java.util.ArrayList;

public class User {
    private String email;
    private ArrayList<Integer> favorites; //= new ArrayList<Integer>(); // Create an ArrayList object

    public User(String email, ArrayList<Integer> favorites) {
        this.email = email;
        this.favorites = favorites;
    }
    public User(){ //empty constructor
        this.email = "";
        this.favorites = new ArrayList<Integer>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Integer> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Integer> favorites) {
        this.favorites = favorites;
    }
}
