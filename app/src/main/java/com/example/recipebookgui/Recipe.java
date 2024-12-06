package com.example.recipebookgui;

import java.util.List;

public class Recipe {

    private String recipesName;
    private String imageSource; // This will store image file name or path (currently not used)
    private List<Ingredient> ingredientList; // List of ingredient objects
    private String description; // Recipe steps or description
    private boolean favorite; // Holds the Favorite status of the Recipe
    private String key; // Firebase generated key for each recipe

    // Default constructor
    public Recipe() {}

    // Full constructor
    public Recipe(String recipesName, String description, boolean favorite, String imageSource, List<Ingredient> ingredientList) {
        this.recipesName = recipesName;
        this.description = description;
        this.favorite = favorite;
        this.imageSource = imageSource;
        this.ingredientList = ingredientList;
    }

    // Getter and Setter methods
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