package com.example.culinaryquest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewRecipesActivity extends AppCompatActivity {

    private RecyclerView recipesRecyclerView;
    private AdminRecipeAdapter recipeAdapter;
    private RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);

        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);

        recipeRepository = new RecipeRepository(this);
        recipeRepository.open();

        List<Recipe> recipes = recipeRepository.getAllRecipes();
        recipeAdapter = new AdminRecipeAdapter(recipes);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesRecyclerView.setAdapter(recipeAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipeRepository.close();
    }
}
