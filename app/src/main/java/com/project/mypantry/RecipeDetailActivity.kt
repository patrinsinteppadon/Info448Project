package com.project.mypantry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.recipe_detail.*

class RecipeDetailActivity: AppCompatActivity() {
    lateinit var currRecipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_detail)

        val recipe: Recipe = intent.getParcelableExtra("SELECTED_RECIPE")

        recipe?.let {
            currRecipe = recipe
            recipeTitle.text = recipe.name
            recipeImg.setImageResource(R.drawable.beforeyougo)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("RECIPE", currRecipe)
        super.onSaveInstanceState(outState)
    }

    private fun onEditClick() {

    }
}