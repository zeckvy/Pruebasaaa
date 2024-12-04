package com.example.culinaryquest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteRecipeActivity extends AppCompatActivity {

    private EditText idEditText;
    private Button deleteButton;
    private RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);

        idEditText = findViewById(R.id.idEditText);
        deleteButton = findViewById(R.id.deleteButton);
        recipeRepository = new RecipeRepository(this);
        recipeRepository.open();

        deleteButton.setOnClickListener(v -> {
            String id = idEditText.getText().toString();
            if (!id.isEmpty()) {
                int recipeId = Integer.parseInt(id);
                recipeRepository.deleteRecipe(recipeId);
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
