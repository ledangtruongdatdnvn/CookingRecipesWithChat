package com.midterm.foodrecipesandconnection.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.foodrecipesandconnection.Listeners.RecipeClickListener;
import com.midterm.foodrecipesandconnection.Models.SimilarRecipe;
import com.midterm.foodrecipesandconnection.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeAdapter.SimilarRecipeViewHolder> {
    Context context;
    List<SimilarRecipe> list;
    RecipeClickListener recipeClickListener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipe> list, RecipeClickListener recipeClickListener) {
        this.context = context;
        this.list = list;
        this.recipeClickListener = recipeClickListener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.textView_similar_title.setText(list.get(position).getTitle());
        holder.textView_similar_title.setSelected(true);
        holder.textView_similar_serving.setText(list.get(position).getServings() + " Persons");
        Picasso.get().load("https://spoonacular.com/recipeImages/" + list.get(position).getId() + "-556x370." + list.get(position).getImageType()).into(holder.imageView_similar);
        holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeClickListener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SimilarRecipeViewHolder extends RecyclerView.ViewHolder {

        CardView similar_recipe_holder;
        TextView textView_similar_title, textView_similar_serving;
        ImageView imageView_similar;

        public SimilarRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
            textView_similar_serving = itemView.findViewById(R.id.textView_similar_serving);
            textView_similar_title = itemView.findViewById(R.id.textView_similar_title);
            imageView_similar = itemView.findViewById(R.id.imageView_similar);
        }
    }
}
