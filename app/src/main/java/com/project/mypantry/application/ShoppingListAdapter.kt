package com.project.mypantry.application
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.mypantry.objects.Recipe
import com.project.mypantry.R

import kotlinx.android.synthetic.main.item_recipe.*

class ShoppingListAdapter(initialRecipes: List<Recipe>): RecyclerView.Adapter<ShoppingListAdapter.RecipeViewHolder>()  {
    private var allrecipes: List<Recipe> = initialRecipes.toList()  // This is so we create a duplicate of the list passed in
    var onRecipeClicked: ((recipe: Recipe) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount() = allrecipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val allTheRecipes = allrecipes[position]
        holder.bind(allTheRecipes)
    }

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.textView)
        //private val tvDescip = itemView.findViewById<TextView>(R.id.recipeDesc)
        private val ivCovers = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(recipe: Recipe) {
            tvTitle.text = recipe.name
            //tvDescip.text = recipe.ingredients.toString()
            ivCovers.setImageResource(R.drawable.ic_launcher_background)
//            val myUri = Uri.parse(recipe.smallImageURL)
//            Picasso.get().load(myUri).into(ivCovers);

            itemView.setOnClickListener{
                onRecipeClicked?.invoke(recipe)
            }
        }


    }
}