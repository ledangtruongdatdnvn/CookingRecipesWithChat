package com.midterm.foodrecipesandconnection.Models;

import java.util.ArrayList;

public class AnalyzedInstruction {
    private String name;
    private ArrayList<Step> steps;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> value) {
        this.steps = value;
    }
}
