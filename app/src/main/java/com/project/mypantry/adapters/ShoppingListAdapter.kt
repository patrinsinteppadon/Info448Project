package com.project.mypantry.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mypantry.R
import com.project.mypantry.managers.ShoppingListManager
import com.project.mypantry.objects.IngredientType
import com.squareup.picasso.Picasso

class ShoppingListAdapter(
    private val shoppingManager: ShoppingListManager,
    private val context: Context
) :
    RecyclerView.Adapter<ShoppingListAdapter.GroceryViewHolder>() {
    var onGroceryClicked: ((ing: IngredientType) -> Unit)? = null
    private var shoppingList: List<IngredientType> = shoppingManager.shoppingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount() = shoppingList.size

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val ing = shoppingList[position]
        holder.bind(ing)
    }

    fun update(newList: List<IngredientType>) {
        shoppingList = newList
        notifyDataSetChanged()
    }

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val picture = itemView.findViewById<ImageView>(R.id.ingPic)
        private val checkbox = itemView.findViewById<CheckBox>(R.id.cbGrocery)

        fun bind(ing: IngredientType) {
            name.text = ing.ingredientName
            checkbox.isChecked = shoppingManager.isChecked(ing.id)

            var localImgLink =
                context.resources.getIdentifier(ing.ingredientImg, "drawable", context.packageName)
            Picasso.get().load(localImgLink)
                .placeholder(R.drawable.ic_launcher_background)
                .into(picture);

            itemView.setOnClickListener {
                onGroceryClicked?.invoke(ing)
            }

            checkbox.setOnClickListener {
                shoppingManager.check(ing.id)
            }
        }
    }
}