package com.project.mypantry.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mypantry.R
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe

//class RecipeIngredientListAdapter(pantryApp: PantryApp, recipe: Recipe): RecyclerView.Adapter<RecipeIngredientListAdapter.RecipeIngredientViewHolder>() {
//    private val recipe = recipe
//    private val app = pantryApp
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeIngredientListAdapter.RecipeIngredientViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item, parent, false)
//        return RecipeIngredientViewHolder(view)
//    }
//
////    override fun getItemCount() = ingList.size
////
////    override fun onBindViewHolder(holder: RecipeIngredientViewHolder, position: Int) {
////        val ingredient = ingList[position]
////        holder.bind(ingredient)
////    }
//
//    inner class RecipeIngredientViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private val name = itemView.findViewById<TextView>(R.id.name)
//        private val ingPic = itemView.findViewById<ImageView>(R.id.ingPic)
//        private val amount = itemView.findViewById<TextView>(R.id.amount)
//
//
//        fun bind(ing: IngredientType) {
//            name.text = ing.ingredientName
//            ingPic.setImageResource(R.drawable.ic_launcher_background)
//        }
//    }
//
//}