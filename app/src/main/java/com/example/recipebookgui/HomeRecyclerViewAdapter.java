package com.example.recipebookgui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {
    Context context;
    private List<Recipe> recipeList;

    RecipeListener recipeListener;

    public HomeRecyclerViewAdapter(List<Recipe> recipeList, Context context){
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recycler_view_holder, parent, false);
        return new HomeRecyclerViewHolder(view, view.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bindRecipes(recipeList.get(position));

        holder.favoriteHeart.setOnClickListener(v -> {
            try {
                if (recipe.getKey() != null) { // Ensure the recipe ID is not null
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("recipe_table").child(recipe.getKey());
                    if (!recipe.isFavorite()) {
                        holder.favoriteHeart.setImageResource(R.drawable.ic_favorite_filled);
                        recipe.setFavorite(true);
                        databaseReference.child("favorite").setValue(true)
                                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Recipe added to favorites!", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> {
                                    Toast.makeText(context, "Error adding to favorites.", Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        holder.favoriteHeart.setImageResource(R.drawable.ic_favorite_outline);
                        recipe.setFavorite(false);
                        databaseReference.child("favorite").setValue(false)
                                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Recipe removed from favorites!", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> {
                                    Toast.makeText(context, "Error removing from favorites.", Toast.LENGTH_SHORT).show();
                                });
                    }
                    recipeList.set(position, recipe);
                    notifyItemChanged(position);
                } else {
                    Toast.makeText(context, "No such recipe was found.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(context, "Failed to update favorite status... oops.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void updateRecipes(List<Recipe> newRecipes) {
        this.recipeList.clear();
        this.recipeList.addAll(newRecipes);
        notifyDataSetChanged();
    }

}