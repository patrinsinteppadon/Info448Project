package com.project.mypantry.adapters
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

class ShoppingListAdapter(private val shoppingManager: ShoppingListManager): RecyclerView.Adapter<ShoppingListAdapter.RecipeViewHolder>()  {
    var onGroceryClicked: ((ing: IngredientType) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount() = shoppingManager.shoppingList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val ing = shoppingManager.shoppingList[position]
        holder.bind(ing)
    }

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.name)
        private val ivCovers = itemView.findViewById<ImageView>(R.id.ingPic)
        private val cbGrocery = itemView.findViewById<CheckBox>(R.id.cbGrocery)

        fun bind(ing: IngredientType) {
            tvTitle.text = ing.ingredientName
            cbGrocery.isChecked = shoppingManager.isChecked(ing.id)
//            ivCovers.setImageResource(ing.ingredientImg)
            ivCovers.setImageResource(R.drawable.ic_launcher_background)

            itemView.setOnClickListener{
                onGroceryClicked?.invoke(ing)
            }

            cbGrocery.setOnClickListener {
                shoppingManager.check(ing.id)
            }
        }
    }
}