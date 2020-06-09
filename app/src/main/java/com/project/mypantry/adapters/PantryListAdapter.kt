package com.project.mypantry.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mypantry.R
import com.project.mypantry.objects.IngredientInstance

class PantryListAdapter(initialPantry: List<IngredientInstance>): RecyclerView.Adapter<PantryListAdapter.PantryViewHolder>() {
    private var allIng: List<IngredientInstance> = initialPantry.toList()  // This is so we create a duplicate of the list passed in
    var onPantryClicked: ((ingredient: IngredientInstance) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PantryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pantry_item, parent, false)
        return PantryViewHolder(view)
    }

    override fun getItemCount() = allIng.size

    override fun onBindViewHolder(holder: PantryViewHolder, position: Int) {
        val ingredient = allIng[position]
        holder.bind(ingredient)
    }

    inner class PantryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val ivCovers = itemView.findViewById<ImageView>(R.id.ingPic)
        private val expDate = itemView.findViewById<TextView>(R.id.expDate)


        fun bind(ing: IngredientInstance) {
            name.text = ing.name
            ivCovers.setImageResource(R.drawable.ic_launcher_background)
            expDate.text = "Expiration Date:\n" + ing.expiration.toString()

            itemView.setOnClickListener{
                onPantryClicked?.invoke(ing)
            }
        }


    }
}