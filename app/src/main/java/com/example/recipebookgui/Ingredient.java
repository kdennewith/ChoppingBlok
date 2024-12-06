package com.example.recipebookgui;

public class Ingredient {
    private String name;
    private int amount;
    private String measureUnit;

    // No-argument constructor required for Firebase deserialization
    public Ingredient() {
        // Empty constructor for Firebase to use
    }

    // Constructor with parameters
    public Ingredient(String name, int amount, String measureUnit) {
        this.name = name;
        this.amount = amount;
        this.measureUnit = measureUnit;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}
