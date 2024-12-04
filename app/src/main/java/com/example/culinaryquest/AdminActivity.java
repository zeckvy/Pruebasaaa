package com.example.culinaryquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private Button addButton;
    private Button viewButton;
    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addButton = findViewById(R.id.addButton);
        viewButton = findViewById(R.id.viewButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        addButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, AddRecipeActivity.class)));
        viewButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, ViewRecipesActivity.class)));
        updateButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, UpdateRecipeActivity.class)));
        deleteButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, DeleteRecipeActivity.class)));
    }
}
