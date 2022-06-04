package com.midterm.foodrecipesandconnection.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.midterm.foodrecipesandconnection.Listeners.RecipeClickListener;
import com.midterm.foodrecipesandconnection.Models.Recipe;
import com.midterm.foodrecipesandconnection.Models.Recipes;
import com.midterm.foodrecipesandconnection.R;
import com.midterm.foodrecipesandconnection.View.RandomRecipeAdapter;
import com.midterm.foodrecipesandconnection.ViewModels.RequestManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomRecipesActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager APIService;
    RandomRecipeAdapter randomRecipeAdapter;
    Spinner spinner;
    List<String> tags = new ArrayList<String>();
    SearchView searchView;
    RecyclerView recyclerView;
    List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_recipes);
        dialog = new ProgressDialog(RandomRecipesActivity.this);
        dialog.setTitle("Loading Data ...");

        setTags();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_random);
        recyclerView.setLayoutManager(new LinearLayoutManager(RandomRecipesActivity.this));

        recipes = new ArrayList<>();
        randomRecipeAdapter = new RandomRecipeAdapter(this, recipeClickListener);
        randomRecipeAdapter.loadData((ArrayList<Recipe>) recipes);

        getData(tags);

        spinner = findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.tags, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);
        searchView = findViewById(R.id.searchView_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                tags.clear();
                tags.add(s);
                getData(tags);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


    }

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @SuppressLint("LongLogTag")
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            getData(tags);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private void getData(List<String> tags) {
        dialog.show();
        APIService = new RequestManager();
        APIService.getRandomRecipes(tags)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Recipes>() {
                    @Override
                    public void onSuccess(@NonNull Recipes r) {
                        recipes = new ArrayList<>();
                        for (int i = 0; i < r.getRecipes().size(); i++) {
                            recipes.add(r.getRecipes().get(i));
                        }
                        randomRecipeAdapter.loadData((ArrayList<Recipe>) recipes);
                        recyclerView.setAdapter(randomRecipeAdapter);
                        randomRecipeAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Some Information", e.getMessage());
                        Toast.makeText(RandomRecipesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
//            Toast.makeText(RandomRecipesActivity.this,"Recipe Clicked",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RandomRecipesActivity.this, RecipeDetailsActivity.class));
        }
    };

    private void setTags() {
        for (int i = 0; i < getResources().getStringArray(R.array.tags).length; i++) {
            tags.add(getResources().getStringArray(R.array.tags)[i]);
        }
    }
}