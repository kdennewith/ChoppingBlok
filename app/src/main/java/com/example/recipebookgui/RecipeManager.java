package com.example.recipebookgui;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeManager {

    private DatabaseReference databaseReference;

    public RecipeManager() {
        // Initialize Firebase reference to the recipes node
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
    }

    // Add a recipe to Firebase
    public void addData(Recipe recipe) {
        // Check if recipe is valid
        if (recipe == null) {
            System.err.println("Error: Recipe is null!");
            return;
        }

        // Push generates a unique key for each recipe
        String recipeId = databaseReference.push().getKey();

        if (recipeId != null) {
            recipe.setKey(recipeId); // Set the unique Firebase key for the recipe
            // Log the recipe data to ensure it's correct
            System.out.println("Saving recipe with ID: " + recipeId);
            System.out.println("Recipe Data: " + recipe.toString());

            // Save the recipe object to the database
            databaseReference.child(recipeId).setValue(recipe)
                    .addOnSuccessListener(aVoid -> {
                        // Data successfully written to Firebase
                        System.out.println("Recipe added successfully");
                    })
                    .addOnFailureListener(e -> {
                        // Handle write error
                        System.err.println("Failed to add recipe: " + e.getMessage());
                    });
        }
    }
}
