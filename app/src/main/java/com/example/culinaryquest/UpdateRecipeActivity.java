package com.example.culinaryquest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateRecipeActivity extends AppCompatActivity {

    private EditText idEditText;
    private EditText titleEditText;
    private EditText ingredientsEditText; // Nuevo campo de entrada para ingredientes
    private Button updateButton;
    private RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        idEditText = findViewById(R.id.idEditText);
        titleEditText = findViewById(R.id.titleEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText); // Inicializar el nuevo campo
        updateButton = findViewById(R.id.updateButton);
        recipeRepository = new RecipeRepository(this);
        recipeRepository.open();

        updateButton.setOnClickListener(v -> {
            String id = idEditText.getText().toString();
            String title = titleEditText.getText().toString();
            String ingredients = ingredientsEditText.getText().toString();
            if (!id.isEmpty() && !title.isEmpty() && !ingredients.isEmpty()) {
                int recipeId = Integer.parseInt(id);
                recipeRepository.updateRecipe(recipeId, title, ingredients); // Pasar todos los argumentos
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
