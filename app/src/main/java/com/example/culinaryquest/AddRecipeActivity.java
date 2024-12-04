package com.example.culinaryquest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText ingredientsEditText; // Nuevo campo de entrada
    private Button saveButton;
    private RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        titleEditText = findViewById(R.id.titleEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText); // Inicializar el nuevo campo
        saveButton = findViewById(R.id.saveButton);
        recipeRepository = new RecipeRepository(this);
        recipeRepository.open();

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String ingredients = ingredientsEditText.getText().toString();
            if (!title.isEmpty() && !ingredients.isEmpty()) {
                recipeRepository.addRecipe(title, ingredients); // Pasar ambos argumentos
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipeRepository.close();
    }
}
