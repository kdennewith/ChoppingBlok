package com.example.recipebookgui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NewRecipePage extends Fragment {

    private IngredientsAdapter ingredientsAdapter;
    private ArrayList<Ingredient> ingredientList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newrecipe_page, container, false);

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

        return view;
    }
}





