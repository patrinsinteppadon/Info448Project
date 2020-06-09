package com.project.mypantry.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mypantry.R
import com.project.mypantry.adapters.RecipeIngredientListAdapter
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.Recipe
import com.squareup.picasso.Picasso
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
//            val image: ImageView = findViewById(R.id.recipeImg)
//            Picasso.get().load("https://picsum.photos/seed/Can%27tTouchThis/50").into(image)
            ingredList.adapter = RecipeIngredientListAdapter((application as PantryApp), recipe)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("RECIPE", currRecipe)
        super.onSaveInstanceState(outState)
    }

    private fun onEditClick() {

    }
}