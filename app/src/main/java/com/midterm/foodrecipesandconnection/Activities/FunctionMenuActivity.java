package com.midterm.foodrecipesandconnection.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.midterm.foodrecipesandconnection.R;


public class FunctionMenuActivity extends AppCompatActivity {
    CardView cv_recipes, cv_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_menu);
        cv_recipes = findViewById(R.id.cv_recipes);
        cv_connection = findViewById(R.id.cv_connection);
        cv_recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FunctionMenuActivity.this, "Recipes Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FunctionMenuActivity.this, RandomRecipesActivity.class));
            }
        });
        cv_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FunctionMenuActivity.this, "Connection Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}