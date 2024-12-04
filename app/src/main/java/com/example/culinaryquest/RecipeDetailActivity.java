package com.example.culinaryquest;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView recipeTitleTextView;
    private TextView recipeIngredientsTextView;
    private RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeTitleTextView = findViewById(R.id.recipeTitleTextView);
        recipeIngredientsTextView = findViewById(R.id.recipeIngredientsTextView);

        recipeRepository = new RecipeRepository(this);
        recipeRepository.open();

        int recipeId = getIntent().getIntExtra("recipe_id", -1);
        if (recipeId != -1) {
            Recipe recipe = recipeRepository.getRecipeById(recipeId);
            if (recipe != null) {
                recipeTitleTextView.setText(recipe.getTitle());
                recipeIngredientsTextView.setText(recipe.getIngredients());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipeRepository.close();
    }
}
