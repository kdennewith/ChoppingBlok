package com.example.recipebookgui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsRecyclerViewHolder> {

    private ArrayList<Ingredient> ingredientList;
    private Context context;

    public IngredientsAdapter(ArrayList<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_recycler_view_holder, parent, false);
        return new IngredientsRecyclerViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsRecyclerViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bindIngredient(ingredient);

        // Initialize Spinner and set adapter inside onBindViewHolder
        Spinner spinner = holder.itemView.findViewById(R.id.dropdown_menu);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Tsp");
        arrayList.add("Tbsp");
        arrayList.add("oz");
        arrayList.add("Cup(s)");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Set listener for spinner selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                ingredient.setMeasureUnit(item); // Use setMeasureUnit instead of setMeasurement
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Optionally handle the case when no item is selected
            }
        });


        // Handling changes in ingredient name or amount
        holder.ingredientName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                ingredient.setName(editable.toString());
            }
        });

        holder.ingredientAmount.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    ingredient.setAmount(Integer.parseInt(editable.toString()));
                } catch (NumberFormatException e) {
                    ingredient.setAmount(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void updateIngredients(ArrayList<Ingredient> newIngredients) {
        ingredientList = newIngredients;
        notifyDataSetChanged();
    }

    private class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable editable) {}
    }
}


