package com.midterm.foodrecipesandconnection.ViewModels;

import com.midterm.foodrecipesandconnection.Models.Instructions;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InstructionOfRecipeAPI {
    @GET("recipes/{id}/analyzedInstructions")
    public Single<List<Instructions>> getInstructions(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}
