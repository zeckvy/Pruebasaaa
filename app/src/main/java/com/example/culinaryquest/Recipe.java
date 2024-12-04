package com.example.culinaryquest;

public class Recipe {
    private int id;
    private String title;
    private String ingredients;

    public Recipe(int id, String title, String ingredients) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }
}
