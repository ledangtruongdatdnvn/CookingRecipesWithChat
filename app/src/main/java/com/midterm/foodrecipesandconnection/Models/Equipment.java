package com.midterm.foodrecipesandconnection.Models;

public class Equipment {
    private int id;
    private String image;
    private String name;
    private Temperature temperature;

    public Equipment(int id, String image, String name, Temperature temperature) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
}
