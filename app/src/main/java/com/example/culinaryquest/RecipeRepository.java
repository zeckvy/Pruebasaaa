package com.example.culinaryquest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public RecipeRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addRecipe(String title, String ingredients) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("ingredients", ingredients);
        db.insert("recipes", null, values);
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        Cursor cursor = db.query("recipes", null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String ingredients = cursor.getString(cursor.getColumnIndexOrThrow("ingredients"));
                recipes.add(new Recipe(id, title, ingredients));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return recipes;
    }

    public List<Recipe> searchRecipesByIngredients(String ingredient) {
        List<Recipe> recipes = new ArrayList<>();
        Cursor cursor = db.query("recipes", null, "ingredients LIKE ?", new String[]{"%" + ingredient + "%"}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String ingredients = cursor.getString(cursor.getColumnIndexOrThrow("ingredients"));
                recipes.add(new Recipe(id, title, ingredients));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return recipes;
    }

    public Recipe getRecipeById(int id) {
        Recipe recipe = null;
        Cursor cursor = db.query("recipes", null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String ingredients = cursor.getString(cursor.getColumnIndexOrThrow("ingredients"));
            recipe = new Recipe(id, title, ingredients);
            cursor.close();
        }
        return recipe;
    }

    public void updateRecipe(int id, String newTitle, String newIngredients) {
        ContentValues values = new ContentValues();
        values.put("title", newTitle);
        values.put("ingredients", newIngredients);
        db.update("recipes", values, "_id=?", new String[]{String.valueOf(id)});
    }

    public void deleteRecipe(int id) {
        db.delete("recipes", "_id=?", new String[]{String.valueOf(id)});
    }
}
