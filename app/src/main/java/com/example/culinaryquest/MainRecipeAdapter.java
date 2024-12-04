package com.example.culinaryquest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainRecipeAdapter extends RecyclerView.Adapter<MainRecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private OnItemClickListener onItemClickListener;

    public MainRecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;

        public RecipeViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(view, position);
                    }
                }
            });
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_main, parent, false);
        return new RecipeViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeTitle.setText(recipe.getTitle());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void updateRecipes(List<Recipe> newRecipes) {
        recipes.clear();
        recipes.addAll(newRecipes);
        notifyDataSetChanged();
    }
}
