package com.example.chopping_block;

public class Ingredient {
    //ingredients for a recipe
    private String name;
    private String units;

    //constructor
    public Ingredient(String name, String units) {
        this.name = name;
        this.units = units;
    }

    //getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
