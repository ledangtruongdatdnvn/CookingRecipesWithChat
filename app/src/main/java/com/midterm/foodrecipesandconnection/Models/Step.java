package com.midterm.foodrecipesandconnection.Models;

import java.util.ArrayList;

public class Step {
    private int number;
    private String step;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Equipment> equipment;
    private Length length;

    public int getNumber() {
        return number;
    }

    public void setNumber(int value) {
        this.number = value;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String value) {
        this.step = value;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> value) {
        this.ingredients = value;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> value) {
        this.equipment = value;
    }

    public Length getLength() {
        return length;
    }

    public void setLength(Length value) {
        this.length = value;
    }
}
