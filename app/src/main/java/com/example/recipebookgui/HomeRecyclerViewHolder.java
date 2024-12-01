package com.example.recipebookgui;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This is the holder class that binds all the elements of home_recycler_view_holder.xml
 *  to the home_page.xml's recyclerView.
 */
public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView cardImage;
    TextView recipeName;
    ImageButton favoriteHeart;
    Context context;

    public HomeRecyclerViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        cardView = itemView.findViewById(R.id.home_cardView);
        cardImage = itemView.findViewById(R.id.card_image);
        recipeName = itemView.findViewById(R.id.card_recipe_name);
        favoriteHeart = itemView.findViewById(R.id.favorite_heart);
    }

    /**
     * This method binds the objects variables to the ViewHolder for the HomeRecyclerView
     * @param recipe the Recipe object you are binding to this HomeRecyclerViewHolder
     */
    void bindRecipes(final Recipe recipe) {
        int resourceId = context.getResources().getIdentifier("chicken_curry.jpg", "drawable", context.getPackageName());
        cardImage.setImageResource(resourceId);
        recipeName.setText(recipe.getRecipesName());
        if (recipe.isFavorite()) {
            favoriteHeart.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            favoriteHeart.setImageResource(R.drawable.ic_favorite_outline);
        }
    }
}
