package com.midterm.foodrecipesandconnection.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.foodrecipesandconnection.Listeners.RecipeClickListener;

import com.midterm.foodrecipesandconnection.Models.SimilarRecipe;
import com.midterm.foodrecipesandconnection.R;
import com.midterm.foodrecipesandconnection.View.SimilarRecipeAdapter;
import com.midterm.foodrecipesandconnection.ViewModels.RequestManager;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    TextView textView_meal_name, textView_meal_source, textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_similar;
    RequestManager APIService;
    ProgressDialog dialog;
    SimilarRecipeAdapter similarRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        findViews();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        getSimilarRecipe(id);

    }

    private void findViews() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        textView_meal_summary = findViewById(R.id.textView_meal_summary);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_similar = findViewById(R.id.recycler_meal_similar);
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
                        recycler_meal_similar.setHasFixedSize(true);
                        recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this, similarRecipes, recipeClickListener);
                        recycler_meal_similar.setAdapter(similarRecipeAdapter);
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
//            Toast.makeText(RecipeDetailsActivity.this,id,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class).putExtra("id", id));
        }
    };
}