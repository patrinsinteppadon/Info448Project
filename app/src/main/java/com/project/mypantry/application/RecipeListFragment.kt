package com.project.mypantry.application

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.mypantry.R
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment: Fragment() {
    private var recipesAll: List<Recipe>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val recipesAll = args.getParcelableArrayList<Recipe>(RECIPEs_KEY)
            if (recipesAll != null) {
                this.recipesAll = recipesAll
            }
        }

    }


    private var onRecipeSelectedListener: OnRecipeSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnRecipeSelectedListener) {
            onRecipeSelectedListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.frag_list_recip, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateRecipeListViews()


    }




    private fun updateRecipeListViews() {
        recipesAll?.let {

            val recipesMutfromAct = it.toMutableList()

            val recipeAdapter =
                RecipeListAdapter(recipesMutfromAct)

            rvRecipeList.adapter = recipeAdapter

            recipeAdapter.onRecipeClicked = { someRecipe: Recipe ->
                onRecipeSelectedListener?.onRecipeSelected(someRecipe)



            }


        }
    }

    companion object {
        // Keys for intents
        val TAG: String = RecipeListFragment::class.java.simpleName
        const val RECIPEs_KEY = "RECIPEs_KEY"


    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}

interface OnRecipeSelectedListener {
    fun onRecipeSelected(recipe: Recipe)
}