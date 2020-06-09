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
import com.project.mypantry.objects.IngredientInstance

import kotlinx.android.synthetic.main.item_recipe.*

class ShoppingListAdapter(initialGroceries: List<IngredientInstance>): RecyclerView.Adapter<ShoppingListAdapter.RecipeViewHolder>()  {
    private var allGroc: List<IngredientInstance> = initialGroceries.toList()  // This is so we create a duplicate of the list passed in
    var onGroceryClicked: ((ing: IngredientInstance) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount() = allGroc.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val ing = allGroc[position]
        holder.bind(ing)
    }

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.name)
        private val ivCovers = itemView.findViewById<ImageView>(R.id.ingPic)

        fun bind(ing: IngredientInstance) {
            tvTitle.text = "need to change"
            //tvDescip.text = recipe.ingredients.toString()
            ivCovers.setImageResource(R.drawable.ic_launcher_background)
//            val myUri = Uri.parse(recipe.smallImageURL)
//            Picasso.get().load(myUri).into(ivCovers);

            itemView.setOnClickListener{
                onGroceryClicked?.invoke(ing)
            }
        }


    }
}