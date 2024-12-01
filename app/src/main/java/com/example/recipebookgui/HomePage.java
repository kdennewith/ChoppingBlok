package com.example.recipebookgui;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends Fragment {
    private Toolbar topBar;
    private TextView title;
    private SearchView searchbar;
    private RecyclerView recipeHolder;
    private static DatabaseReference database;
    private List<Recipe> filteredRecipes = new ArrayList<>();
    private List<Recipe> allRecipes = new ArrayList<>();
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflating the xml home_page to this fragment.
        return inflater.inflate(R.layout.home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance().getReference("recipe_table");
        topBar = view.findViewById(R.id.title_bar_home);
        title = view.findViewById(R.id.toolbar_title);
        searchbar = view.findViewById(R.id.homepage_searchbar);
        recipeHolder = view.findViewById(R.id.homepage_recyclerview);
        recipeHolder.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(allRecipes, getActivity());
        recipeHolder.setAdapter(homeRecyclerViewAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                allRecipes.clear(); // Clear existing data to prevent duplicates
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    Recipe recipe = recipeSnapshot.getValue(Recipe.class);
                    if (recipe != null) { // Check for a null to just add all the recipes
                        recipe.setKey(recipeSnapshot.getKey());
                        allRecipes.add(recipe);
                        Log.d("HomePage", "Recipe Name: " + recipe.getRecipesName());
                    }
                }
                homeRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("FirebaseDBError", "Failed to read data: " + error.getMessage());
                Toast.makeText(getActivity(), "Failed to load recipes. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return true;
            }

            /**
             * This method takes in the textfield from the search query and filters the results,
             *  if the search query is empty then all recipes will be added.
             * @param newText the new content of the query text field.
             *
             * @return a boolean value if the text was changed or not.
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText == null){
                    homeRecyclerViewAdapter.updateRecipes(allRecipes);
                }else{
                    filter(newText);
                }
                return true;
            }
        });
    }

    //Using to push some recipes to make a Database available.
    private void addRecipe(Recipe recipe) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("recipe_table");

        // A recipeID
        String recipeId = databaseReference.push().getKey();

        if (recipeId != null) {

            databaseReference.child(recipeId).setValue(recipe)
                    .addOnSuccessListener(aVoid -> Log.d("FirebaseDB", "Recipe added successfully."))
                    .addOnFailureListener(e -> Log.e("FirebaseDBError", "Failed to add recipe: ", e));
        }
    }

    /**
     * This method adds to a filteredRecipeList everytime a new char is entered into the searchbar
     * @param currentText
     */
    private void filter(String currentText) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (recipe.getRecipesName().toLowerCase().contains(currentText.toLowerCase())) {
                filteredRecipes.add(recipe);
            }
        }
        homeRecyclerViewAdapter.updateRecipes(filteredRecipes);
    }

}
