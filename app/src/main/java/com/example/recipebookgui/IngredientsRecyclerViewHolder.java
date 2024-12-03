package com.example.recipebookgui;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientsRecyclerViewHolder extends RecyclerView.ViewHolder {

    EditText ingredientName, ingredientAmount;
    Spinner measureUnit;
    Context context;

    public IngredientsRecyclerViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        ingredientName = itemView.findViewById(R.id.IngredientName);
        ingredientAmount = itemView.findViewById(R.id.ingredientAmount);
        measureUnit = itemView.findViewById(R.id.dropdown_menu);
    }

    void bindIngredient(Ingredient ingredient) {
        ingredientName.setText(ingredient.getName());
        ingredientAmount.setText(String.valueOf(ingredient.getAmount()));

    }
}

