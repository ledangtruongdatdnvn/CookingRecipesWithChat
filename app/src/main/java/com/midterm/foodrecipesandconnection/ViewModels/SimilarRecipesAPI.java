package com.midterm.foodrecipesandconnection.ViewModels;

import com.midterm.foodrecipesandconnection.Models.SimilarRecipe;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SimilarRecipesAPI {
    @GET("recipes/{id}/similar")
    public Single<List<SimilarRecipe>> getSimilarRecipes(
            @Path("id") int id,
            @Query("number") String number,
            @Query("apiKey") String apiKey);

}
