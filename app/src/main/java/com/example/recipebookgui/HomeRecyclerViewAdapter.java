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

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder> {
    Context context;
    private List<Recipe> recipeList;

    public HomeRecyclerViewAdapter(List<Recipe> recipeList, Context context){
        this.context = context;
        this.recipeList = recipeList;
    }

    /**
     * This inflates the home_recycler_view_holder to the ViewHolder, basically getting the XML to display/inflate on the parent,
     *  which is the Adapter
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a HomeRecyclerViewHolder
     */
    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recycler_view_holder, parent, false);
        return new HomeRecyclerViewHolder(view, view.getContext());
    }

    /**
     * This binds each view to a holder, for each item there is a position in the HomeReccyclerAdapters recipeList variable
     *  we pass into it which is updated on the homepage.java using allRecipes and filteredRecipes Lists.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bindRecipes(recipeList.get(position));


        /*
            For this favoriteheart listener I put toast messages for error catching and successful changes of the staus
             of favorite variable. This also set's the value of the Recipe's "favorite" variable which is called with
             the databaseReference which is the Recipe node so you can go into it's subtree to find the favorite boolean var.
         */
        holder.favoriteHeart.setOnClickListener(v -> {
            try {
                if (recipe.getKey() != null) { // Ensure the recipe ID is not null
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("recipes").child(recipe.getKey());
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

    /**
     * This method is used in the homePage to update the current list, mainly for the filtering of the search
     * bar if the new recipeList is not equal to the currentRecipeList.
     * @param newRecipes a new list of recipes that is passed through methods like HomePage.filter().
     */
    public void updateRecipes(List<Recipe> newRecipes) {
        if (!this.recipeList.equals(newRecipes)) {
            this.recipeList = new ArrayList(newRecipes);
            notifyDataSetChanged();
        }
    }

}