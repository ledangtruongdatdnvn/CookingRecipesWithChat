package com.midterm.foodrecipesandconnection.ViewModels;

import com.midterm.foodrecipesandconnection.Models.RecipeDetails;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeDetailsAPI {
    @GET("recipes/{id}/information")
    public Single<RecipeDetails> getRecipeDetails(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}
