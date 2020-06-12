package com.project.mypantry.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.project.mypantry.R
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.IngredientInstance
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter

class PantryListAdapter(
    initialPantry: List<IngredientInstance>,
    pantryApp: PantryApp,
    private val context: Context
) : RecyclerView.Adapter<PantryListAdapter.PantryViewHolder>() {
    private var allIng: List<IngredientInstance> =
        initialPantry.toList()  // This is so we create a duplicate of the list passed in
    var onPantryClicked: ((ingredient: IngredientInstance) -> Unit)? = null
    private val app: PantryApp = pantryApp


    fun update(newList: List<IngredientInstance>) {
        allIng = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PantryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pantry_item, parent, false)
        return PantryViewHolder(view)
    }

    override fun getItemCount() = allIng.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PantryViewHolder, position: Int) {
        val ingredient = allIng[position]
        holder.bind(ingredient)
    }

    inner class PantryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val picture = itemView.findViewById<ImageView>(R.id.ingPic)
        private val expDate = itemView.findViewById<TextView>(R.id.amount)
        private val amountAndUnit = itemView.findViewById<TextView>(R.id.amountAndUnit)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(ing: IngredientInstance) {
            var ingType = app.glossaryManager.getIngredientType(ing.ingredientID)
            name.text = ingType?.ingredientName
            amountAndUnit.text = "${ing.amount} ${ing.unit}"

            val dateString = ing.expiration.format(DateTimeFormatter.ofPattern("dd LLLL"))
            expDate.text = "Best Before:\n $dateString"

            var localImgLink = context.resources.getIdentifier(
                ingType?.ingredientImg,
                "drawable",
                context.packageName
            )
            Picasso.get().load(localImgLink)
                .placeholder(R.drawable.ic_launcher_background)
                .into(picture)

            itemView.setOnClickListener {
                onPantryClicked?.invoke(ing)
            }
        }
    }
}