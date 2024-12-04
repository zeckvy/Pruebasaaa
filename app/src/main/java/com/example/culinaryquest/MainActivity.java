package com.example.culinaryquest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText ingredientEditText;
    private Button searchButton;
    private Button adminButton;
    private RecyclerView recipesRecyclerView;
    private MainRecipeAdapter recipeAdapter;
    private RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingredientEditText = findViewById(R.id.ingredientEditText);
        searchButton = findViewById(R.id.searchButton);
        adminButton = findViewById(R.id.adminButton);
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);

        recipeRepository = new RecipeRepository(this);
        recipeRepository.open();

        searchButton.setOnClickListener(view -> {
            String ingredient = ingredientEditText.getText().toString();
            if (!TextUtils.isEmpty(ingredient)) {
                searchRecipes(ingredient);
            }
        });

        adminButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
        });
    }

    private void searchRecipes(String ingredient) {
        List<Recipe> recipes = recipeRepository.searchRecipesByIngredients(ingredient);
        recipeAdapter = new MainRecipeAdapter(recipes);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesRecyclerView.setAdapter(recipeAdapter);

        recipeAdapter.setOnItemClickListener((view, position) -> {
            Recipe recipe = recipes.get(position);
            Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipe_id", recipe.getId());
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipeRepository.close();
    }
}
