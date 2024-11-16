package com.example.chopping_block;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    private DatabaseReference databaseReference;

    public RecipeManager() {
        //initialize Firebase reference to the recipes node
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
    }

    //add a recipe to Firebase
    public void addData(recipes recipe) {
        //push generates a unique key for each recipe
        databaseReference.push().setValue(recipe).addOnSuccessListener(aVoid -> {
            //data successfully written to Firebase
            System.out.println("Recipe added successfully");
        }).addOnFailureListener(e -> {
            //handle write error
            System.err.println("Failed to add recipe: " + e.getMessage());
        });
    }

    //read recipes from Firebase
    public void readData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<recipes> recipes = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recipes recipe = snapshot.getValue(recipes.class);
                    if (recipe != null) {
                        recipes.add(recipe);
                    }
                }
                //print recipes format of [recipe image from firebase] - Title from Firebase \n - description from firebase
                System.out.println("Recipes retrieved: " + recipes.size());
                for (recipes r : recipes) {
                    System.out.println("Title: " + r.getTitle() + ", Description: " + r.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //handle error
                System.err.println("Error reading data: " + databaseError.getMessage());
            }
        });
    }
}