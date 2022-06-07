package com.midterm.foodrecipesandconnection.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.foodrecipesandconnection.Listeners.RecipeClickListener;
import com.midterm.foodrecipesandconnection.Models.Instructions;
import com.midterm.foodrecipesandconnection.Models.RecipeDetails;
import com.midterm.foodrecipesandconnection.Models.SimilarRecipe;
import com.midterm.foodrecipesandconnection.View.IngredientsAdapter;
import com.midterm.foodrecipesandconnection.View.InstructionsAdapter;
import com.midterm.foodrecipesandconnection.View.SimilarRecipeAdapter;
import com.midterm.foodrecipesandconnection.ViewModels.RequestManager;
import com.midterm.foodrecipesandconnection.databinding.ActivityRecipeDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    RequestManager APIService;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionsAdapter instructionsAdapter;

    private ActivityRecipeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        id = Integer.parseInt(getIntent().getStringExtra("id"));

        getData(id);
        getSimilarRecipe(id);
        getInstructions(id);

        binding.imageBack.setOnClickListener(v -> onBackPressed());

    }

    private void getInstructions(int id) {
        dialog.show();
        APIService = new RequestManager();
        APIService.getInstructions(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Instructions>>() {
                    @Override
                    public void onSuccess(@NonNull List<Instructions> instructions) {
                        dialog.dismiss();
                        binding.recyclerMealInstructions.setHasFixedSize(true);
                        binding.recyclerMealInstructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
                        instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this, instructions);
                        binding.recyclerMealInstructions.setAdapter(instructionsAdapter);
                        instructionsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(RecipeDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void getSimilarRecipe(int id) {
        dialog.show();
        APIService = new RequestManager();
        APIService.getSimilarRecipe(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<SimilarRecipe>>() {
                    @Override
                    public void onSuccess(@NonNull List<SimilarRecipe> similarRecipes) {
                        dialog.dismiss();
                        binding.recyclerMealSimilar.setHasFixedSize(true);
                        binding.recyclerMealSimilar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this, similarRecipes, recipeClickListener);
                        binding.recyclerMealSimilar.setAdapter(similarRecipeAdapter);
                        similarRecipeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(RecipeDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class).putExtra("id", id));
        }
    };

    private void getData(int id) {
        dialog.show();
        APIService = new RequestManager();
        APIService.getRecipeDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<RecipeDetails>() {
                    @Override
                    public void onSuccess(@NonNull RecipeDetails recipeDetails) {
                        dialog.dismiss();
                        binding.textViewMealName.setText(recipeDetails.getTitle());
                        binding.textViewMealSource.setText(recipeDetails.getSourceName());
                        binding.textViewMealSummary.setText(Html.fromHtml(recipeDetails.getSummary()));
                        Picasso.get().load(recipeDetails.getImage()).into(binding.imageViewMealImage);
                        binding.recyclerMealIngredients.setHasFixedSize(true);
                        binding.recyclerMealIngredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this, recipeDetails.getExtendedIngredients());
                        binding.recyclerMealIngredients.setAdapter(ingredientsAdapter);
                        ingredientsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(RecipeDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}