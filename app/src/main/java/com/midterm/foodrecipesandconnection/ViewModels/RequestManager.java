package com.midterm.foodrecipesandconnection.ViewModels;

import com.midterm.foodrecipesandconnection.Models.Recipes;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private static final String BASE_URL = "https://api.spoonacular.com/";
    private static final String API_KEY = "2920d2dce28b44cbbdb63e6f5f12e932";
    private RandomRecipesAPI randomRecipesAPI;
    public RequestManager() {
        randomRecipesAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RandomRecipesAPI.class);
    }

    public Single<Recipes> getRandomRecipes(List<String> tags) {
        return randomRecipesAPI.getRandomRecipes(API_KEY,
                "20", tags);
    }
}
