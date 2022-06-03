package com.midterm.foodrecipesandconnection.Models;

import java.util.ArrayList;

public class ExtendedIngredient {
    private long id;
    private String aisle;
    private String image;
    private String consistency;
    private String name;
    private String nameClean;
    private String original;
    private String originalName;
    private double amount;
    private String unit;
    private ArrayList<String> meta;
    private Measures measures;

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String value) {
        this.aisle = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String value) {
        this.consistency = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getNameClean() {
        return nameClean;
    }

    public void setNameClean(String value) {
        this.nameClean = value;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String value) {
        this.original = value;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String value) {
        this.originalName = value;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String value) {
        this.unit = value;
    }

    public ArrayList<String> getMeta() {
        return meta;
    }

    public void setMeta(ArrayList<String> value) {
        this.meta = value;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures value) {
        this.measures = value;
    }
}
