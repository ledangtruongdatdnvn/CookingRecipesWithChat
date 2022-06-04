package com.midterm.foodrecipesandconnection.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.midterm.foodrecipesandconnection.R;
import com.midterm.foodrecipesandconnection.Utilities.Constants;
import com.midterm.foodrecipesandconnection.Utilities.PreferenceManager;
import com.midterm.foodrecipesandconnection.databinding.ActivityFunctionMenuBinding;
import com.midterm.foodrecipesandconnection.databinding.ActivityMainChatAppBinding;

import java.util.HashMap;


public class FunctionMenuActivity extends AppCompatActivity {
    CardView cv_recipes, cv_connection;
    private PreferenceManager preferenceManager;
    private ActivityFunctionMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_function_menu);
        binding = ActivityFunctionMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        binding.cvConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FunctionMenuActivity.this, MainChatAppActivity.class));
            }
        });
        binding.cvRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FunctionMenuActivity.this, RandomRecipesActivity.class));
            }
        });
//        cv_recipes = findViewById(R.id.cv_recipes);
//        cv_connection = findViewById(R.id.cv_connection);
//        cv_recipes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(FunctionMenuActivity.this, "Recipes Clicked", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(FunctionMenuActivity.this, RandomRecipesActivity.class));
//            }
//        });
//        cv_connection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(FunctionMenuActivity.this, "Connection Clicked", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(FunctionMenuActivity.this, MainChatAppActivity.class));
//            }
//        });
        loadUserDetails();
        setListeners();
    }
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
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
    }
}