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


import com.midterm.foodrecipesandconnection.Activities.RandomRecipesActivity;
import com.midterm.foodrecipesandconnection.Listeners.RecipeClickListener;
import com.midterm.foodrecipesandconnection.Models.Recipe;
import com.midterm.foodrecipesandconnection.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeAdapter.RandomRecipeViewHolder> {
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(RandomRecipesActivity randomRecipesActivity, RecipeClickListener listener) {
        this.context = randomRecipesActivity;
        this.listener = listener;
    }

    public void loadData(ArrayList<Recipe> recipes) {
        this.list = recipes;
    }


    @NonNull
    @Override
    public RandomRecipeAdapter.RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_random_recipe, parent, false);
        return new RandomRecipeAdapter.RandomRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);
        holder.textView_likes.setText(list.get(position).getAggregateLikes() + " Likes");
        holder.textView_servings.setText((list.get(position).getServings() + " Servings"));
        holder.textView_time.setText(list.get(position).getReadyInMinutes() + " Minutes");
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView_food);
        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).getID()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
        CardView random_list_container;
        TextView textView_title, textView_servings, textView_likes, textView_time;
        ImageView imageView_food;


        public RandomRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            random_list_container = itemView.findViewById(R.id.random_list_container);
            textView_title = itemView.findViewById((R.id.textView_title));
            textView_servings = itemView.findViewById(R.id.textView_servings);
            textView_likes = itemView.findViewById(R.id.textView_likes);
            textView_time = itemView.findViewById(R.id.textView_time);
            imageView_food = itemView.findViewById(R.id.imageView_food);
        }
    }
}
