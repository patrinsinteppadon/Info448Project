package com.project.mypantry.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mypantry.objects.Recipe
import com.project.mypantry.R
import com.squareup.picasso.Picasso

class RecipeListAdapter(initialRecipes: List<Recipe>, context: Context): RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>()  {
    private val context = context
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
        private val tvTitle = itemView.findViewById<TextView>(R.id.name)
        //private val tvDescip = itemView.findViewById<TextView>(R.id.recipeDesc)
        private val recipePic = itemView.findViewById<ImageView>(R.id.ingPic)

        fun bind(recipe: Recipe) {
            var localImgLink = context.resources.getIdentifier(recipe.img, "drawable", context.packageName)
            tvTitle.text = recipe.name
            //tvDescip.text = recipe.ingredients.toString()
            Picasso.get().load(localImgLink)
                .placeholder(R.drawable.ic_launcher_background)
                .into(recipePic)
//            val myUri = Uri.parse(recipe.smallImageURL)
//            Picasso.get().load(myUri).into(ivCovers);

            itemView.setOnClickListener{
                onRecipeClicked?.invoke(recipe)
            }
        }


    }
}