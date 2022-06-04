package com.midterm.foodrecipesandconnection.ViewModels;


import com.midterm.foodrecipesandconnection.Models.Recipes;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomRecipesAPI {
    @GET("recipes/random")
    public Single<Recipes> getRandomRecipes(
            @Query("apiKey") String apiKey,
            @Query("number") String number,
            @Query("tags") List<String> tags);
}
