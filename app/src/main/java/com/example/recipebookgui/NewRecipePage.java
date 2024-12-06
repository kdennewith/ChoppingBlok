package com.example.recipebookgui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;

public class NewRecipePage extends Fragment {

    private IngredientsAdapter ingredientsAdapter;
    private ArrayList<Ingredient> ingredientList;
    private EditText recipeNameEditText, descriptionEditText;
    private RecipeManager recipeManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newrecipe_page, container, false);

        // Initialize UI components
        recipeNameEditText = view.findViewById(R.id.recipeName);
        descriptionEditText = view.findViewById(R.id.description);

        // Initialize the ingredient list and RecyclerView
        ingredientList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.ingredientRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ingredientsAdapter = new IngredientsAdapter(ingredientList, getContext());
        recyclerView.setAdapter(ingredientsAdapter);

        // Add a new ingredient when the add button is clicked
        view.findViewById(R.id.addIngredientButton).setOnClickListener(v -> {
            ingredientList.add(new Ingredient("", 0, "")); // Add an empty ingredient with amount as 0
            ingredientsAdapter.updateIngredients(ingredientList);
        });

        // Initialize the RecipeManager (Firebase handler)
        recipeManager = new RecipeManager();

        // Set up the "Create Recipe" button
        MaterialButton createRecipeButton = view.findViewById(R.id.create_recipe);
        createRecipeButton.setOnClickListener(v -> createRecipe());

        return view;
    }

    private void createRecipe() {
        // Get the recipe name and description
        String recipeName = recipeNameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        // Validate input
        if (recipeName.isEmpty() || description.isEmpty()) {
            Toast.makeText(getContext(), "Please enter both a name and a description", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate ingredients
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getName().isEmpty() || ingredient.getAmount() <= 0 || ingredient.getMeasureUnit().isEmpty()) {
                Toast.makeText(getContext(), "Please complete all ingredient fields", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Create a new Recipe object
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipesName(recipeName);
        newRecipe.setDescription(description);
        newRecipe.setFavorite(false); // Default favorite is false
        newRecipe.setImageSource(""); // No image initially, but can be added later

        // Add ingredients to the recipe
        ArrayList<Ingredient> ingredients = new ArrayList<>(ingredientList);
        newRecipe.setIngredientList(ingredients);

        // Log the recipe data to see what is being passed to Firebase
        System.out.println("Recipe Name: " + newRecipe.getRecipesName());
        System.out.println("Recipe Description: " + newRecipe.getDescription());
        for (Ingredient ingredient : ingredients) {
            System.out.println("Ingredient: " + ingredient.getName() + ", Amount: " + ingredient.getAmount() + ", Unit: " + ingredient.getMeasureUnit());
        }

        // Add recipe to Firebase
        recipeManager.addData(newRecipe);

        // Provide feedback to the user
        Toast.makeText(getContext(), "Recipe created successfully!", Toast.LENGTH_SHORT).show();

        // Optionally, clear fields after creation
        recipeNameEditText.setText("");
        descriptionEditText.setText("");
        ingredientList.clear();
        ingredientsAdapter.updateIngredients(ingredientList);
    }
}
