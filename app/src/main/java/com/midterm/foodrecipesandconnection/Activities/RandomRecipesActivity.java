package com.midterm.foodrecipesandconnection.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.midterm.foodrecipesandconnection.Listeners.RecipeClickListener;
import com.midterm.foodrecipesandconnection.Models.Recipe;
import com.midterm.foodrecipesandconnection.Models.Recipes;
import com.midterm.foodrecipesandconnection.R;
import com.midterm.foodrecipesandconnection.Utilities.Constants;
import com.midterm.foodrecipesandconnection.Utilities.PreferenceManager;
import com.midterm.foodrecipesandconnection.View.RandomRecipeAdapter;
import com.midterm.foodrecipesandconnection.ViewModels.RequestManager;
import com.midterm.foodrecipesandconnection.databinding.ActivityRandomRecipesBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomRecipesActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager APIService;
    RandomRecipeAdapter randomRecipeAdapter;
    List<String> tags = new ArrayList<String>();
    List<Recipe> recipes;

    private PreferenceManager preferenceManager;
    private ActivityRandomRecipesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRandomRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(getApplicationContext());
        loadUserDetails();
        setListeners();

        dialog = new ProgressDialog(RandomRecipesActivity.this);
        dialog.setTitle("Loading Data ...");

        setTags();

        binding.recyclerRandom.setLayoutManager(new LinearLayoutManager(RandomRecipesActivity.this));

        recipes = new ArrayList<>();
        randomRecipeAdapter = new RandomRecipeAdapter(this, recipeClickListener);
        randomRecipeAdapter.loadData((ArrayList<Recipe>) recipes);
        getData(tags);


        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.tags, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        binding.spinnerTags.setAdapter(arrayAdapter);
        binding.spinnerTags.setOnItemSelectedListener(spinnerSelectedListener);
        binding.searchViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                tags.clear();
                tags.add(s);
                getData(tags);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        binding.imageBack.setOnClickListener(v -> onBackPressed());
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
                        binding.recyclerRandom.setAdapter(randomRecipeAdapter);
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
            startActivity(new Intent(RandomRecipesActivity.this, RecipeDetailsActivity.class).putExtra("id", id));
        }
    };

    private void setTags() {
        for (int i = 0; i < getResources().getStringArray(R.array.tags).length; i++) {
            tags.add(getResources().getStringArray(R.array.tags)[i]);
        }
    }

    // Sign out
    private void setListeners() {
        binding.imageSignOut.setOnClickListener(v -> signOut());
    }

    private void loadUserDetails() {
        binding.textName.setText(preferenceManager.getString(Constants.KEY_NAME));
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }

    private void signOut() {
        showToast("Sign out...");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clear();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> showToast("Unable to sign out"));
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}