package com.midterm.foodrecipesandconnection.Models;

import java.util.ArrayList;

public class Instructions {
    private String name;
    private ArrayList<Step> steps;

    public Instructions(String name, ArrayList<Step> steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
