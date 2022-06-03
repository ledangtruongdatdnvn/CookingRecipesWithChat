package com.midterm.foodrecipesandconnection.Models;

import java.util.ArrayList;

public class Recipe {
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private boolean sustainable;
    private long weightWatcherSmartPoints;
    private String gaps;
    private boolean lowFodmap;
    private long aggregateLikes;
    private double spoonacularScore;
    private double healthScore;
    private String creditsText;
    private String license;
    private String sourceName;
    private double pricePerServing;
    private ExtendedIngredient[] extendedIngredients;
    private long id;
    private String title;
    private long readyInMinutes;
    private long servings;
    private String sourceURL;
    private String image;
    private String imageType;
    private String summary;
    private ArrayList<Object> cuisines;
    private ArrayList<String> dishTypes;
    private ArrayList<String> diets;
    private ArrayList<Object> occasions;
    private String instructions;
    private ArrayList<AnalyzedInstruction> analyzedInstructions;
    private Object originalID;
    private String spoonacularSourceURL;

    public boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean value) {
        this.vegetarian = value;
    }

    public boolean getVegan() {
        return vegan;
    }

    public void setVegan(boolean value) {
        this.vegan = value;
    }

    public boolean getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean value) {
        this.glutenFree = value;
    }

    public boolean getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean value) {
        this.dairyFree = value;
    }

    public boolean getVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(boolean value) {
        this.veryHealthy = value;
    }

    public boolean getCheap() {
        return cheap;
    }

    public void setCheap(boolean value) {
        this.cheap = value;
    }

    public boolean getVeryPopular() {
        return veryPopular;
    }

    public void setVeryPopular(boolean value) {
        this.veryPopular = value;
    }

    public boolean getSustainable() {
        return sustainable;
    }

    public void setSustainable(boolean value) {
        this.sustainable = value;
    }

    public long getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public void setWeightWatcherSmartPoints(long value) {
        this.weightWatcherSmartPoints = value;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String value) {
        this.gaps = value;
    }

    public boolean getLowFodmap() {
        return lowFodmap;
    }

    public void setLowFodmap(boolean value) {
        this.lowFodmap = value;
    }

    public long getAggregateLikes() {
        return aggregateLikes;
    }

    public void setAggregateLikes(long value) {
        this.aggregateLikes = value;
    }

    public double getSpoonacularScore() {
        return spoonacularScore;
    }

    public void setSpoonacularScore(double value) {
        this.spoonacularScore = value;
    }

    public double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(double value) {
        this.healthScore = value;
    }

    public String getCreditsText() {
        return creditsText;
    }

    public void setCreditsText(String value) {
        this.creditsText = value;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String value) {
        this.license = value;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String value) {
        this.sourceName = value;
    }

    public double getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(double value) {
        this.pricePerServing = value;
    }

    public ExtendedIngredient[] getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(ExtendedIngredient[] value) {
        this.extendedIngredients = value;
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public long getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(long value) {
        this.readyInMinutes = value;
    }

    public long getServings() {
        return servings;
    }

    public void setServings(long value) {
        this.servings = value;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String value) {
        this.sourceURL = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String value) {
        this.imageType = value;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String value) {
        this.summary = value;
    }

    public ArrayList<Object> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<Object> value) {
        this.cuisines = value;
    }

    public ArrayList<String> getDishTypes() {
        return dishTypes;
    }

    public void setDishTypes(ArrayList<String> value) {
        this.dishTypes = value;
    }

    public ArrayList<String> getDiets() {
        return diets;
    }

    public void setDiets(ArrayList<String> value) {
        this.diets = value;
    }

    public ArrayList<Object> getOccasions() {
        return occasions;
    }

    public void setOccasions(ArrayList<Object> value) {
        this.occasions = value;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String value) {
        this.instructions = value;
    }

    public ArrayList<AnalyzedInstruction> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(ArrayList<AnalyzedInstruction> value) {
        this.analyzedInstructions = value;
    }

    public Object getOriginalID() {
        return originalID;
    }

    public void setOriginalID(Object value) {
        this.originalID = value;
    }

    public String getSpoonacularSourceURL() {
        return spoonacularSourceURL;
    }

    public void setSpoonacularSourceURL(String value) {
        this.spoonacularSourceURL = value;
    }
}
