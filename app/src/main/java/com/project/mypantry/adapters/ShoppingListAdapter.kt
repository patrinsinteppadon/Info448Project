package com.project.mypantry.adapters

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mypantry.R
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import kotlinx.android.synthetic.main.item_recipe.*

class ShoppingListAdapter(initialGroceries: List<IngredientType>): RecyclerView.Adapter<ShoppingListAdapter.RecipeViewHolder>()  {
    private var allGroc: List<IngredientType> = initialGroceries.toList()  // This is so we create a duplicate of the list passed in
    var onGroceryClicked: ((ing: IngredientType) -> Unit)? = null
    var checkMap: MutableMap<Int, Boolean>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount() = allGroc.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val ing = allGroc[position]
        holder.bind(ing)
    }

    // runs through and updates every checkbox in the list
    fun updateChecks(newMap: MutableMap<Int, Boolean>) {
        checkMap = newMap

        checkMap?.let {
            // do shit
        }
    }

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.name)
        private val ivCovers = itemView.findViewById<ImageView>(R.id.ingPic)

        fun bind(ing: IngredientType) {
            tvTitle.text = "need to change"
            //tvDescip.text = recipe.ingredients.toString()
            ivCovers.setImageResource(R.drawable.ic_launcher_background)

            itemView.setOnClickListener{
                onGroceryClicked?.invoke(ing)
            }

            // TODO: connect checkMap to this
//            cbGrocery.setOnClickListener {
//                Log.i("patrin", cbGrocery.isChecked.toString())
//            }
        }
    }
}