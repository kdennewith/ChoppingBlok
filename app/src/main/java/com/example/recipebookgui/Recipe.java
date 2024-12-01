package com.example.recipebookgui;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String recipesName;
    private String imageSource; // Was gonna just use a .jpg name as String but gonna try Bitmap
    private List<Ingredient> ingredientList; //List of ingredient objects
    private String description; // Can explain the recipes steps in here
    private boolean favorite; // Holds the Favorite status of the Recipe
    private String key; // What the node's key is I used the default key Strings made by Firebase.

    public Recipe(){}

    public Recipe(String key, String description, boolean favorite, String imageSource, List<Ingredient> ingredientList, String recipesName) {
        this.key = key;
        this.recipesName = recipesName;
        this.imageSource = imageSource;
        this.ingredientList = ingredientList;
        this.description = description;
        this.favorite = favorite;
    }



    public String getRecipesName() {
        return recipesName;
    }

    public void setRecipesName(String recipesName) {
        this.recipesName = recipesName;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}