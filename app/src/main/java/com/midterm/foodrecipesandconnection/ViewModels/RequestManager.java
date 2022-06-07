package com.midterm.foodrecipesandconnection.ViewModels;

import com.midterm.foodrecipesandconnection.Models.Instructions;
import com.midterm.foodrecipesandconnection.Models.RecipeDetails;
import com.midterm.foodrecipesandconnection.Models.Recipes;
import com.midterm.foodrecipesandconnection.Models.SimilarRecipe;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private static final String BASE_URL = "https://api.spoonacular.com/";
    private static final String API_KEY = "f3a9208c655e4b13aafe69cd1d51758d";
    private RandomRecipesAPI randomRecipesAPI;
    private RecipeDetailsAPI recipeDetailsAPI;
    private SimilarRecipesAPI similarRecipeAPI;
    private InstructionOfRecipeAPI instructionOfRecipeAPI;

    public RequestManager() {
        randomRecipesAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RandomRecipesAPI.class);
        recipeDetailsAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RecipeDetailsAPI.class);
        similarRecipeAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(SimilarRecipesAPI.class);
        instructionOfRecipeAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(InstructionOfRecipeAPI.class);
    }

    public Single<Recipes> getRandomRecipes(List<String> tags) {
        return randomRecipesAPI.getRandomRecipes(API_KEY,
                "20", tags);
    }

    public Single<RecipeDetails> getRecipeDetails(int id) {
        return recipeDetailsAPI.getRecipeDetails(id, API_KEY);
    }

    public Single<List<SimilarRecipe>> getSimilarRecipe(int id) {
        return similarRecipeAPI.getSimilarRecipes(id, "20", API_KEY);
    }

    public Single<List<Instructions>> getInstructions(int id) {
        return instructionOfRecipeAPI.getInstructions(id, API_KEY);
    }
}
