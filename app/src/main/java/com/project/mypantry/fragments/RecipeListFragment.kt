package com.project.mypantry.fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.mypantry.activity.OnRecipeClickListener
import com.project.mypantry.R
import com.project.mypantry.adapters.RecipeListAdapter
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment: Fragment() {
    private var recipesAll: MutableList<Recipe> = mutableListOf()
    private var onRecipeSelectedListener: OnRecipeClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


        recipesAll = (context.applicationContext as PantryApp).recipeListManager.recipes

        if (context is OnRecipeClickListener) {
            onRecipeSelectedListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_recipe_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateRecipeListViews()

        // DEMO CODE ==========================
        rvRecipeList.visibility = View.GONE
        tvComingSoon.visibility = View.VISIBLE
    }




    private fun updateRecipeListViews() {
        recipesAll?.let {

            val recipesMutfromAct = it.toMutableList()

            val recipeAdapter =
                RecipeListAdapter(
                    recipesMutfromAct
                )

            rvRecipeList.adapter = recipeAdapter

            recipeAdapter.onRecipeClicked = { someRecipe: Recipe ->
                onRecipeSelectedListener?.onRecipeItemClicked(someRecipe)
            }


        }
    }

    companion object {
        // Keys for intents
        val TAG: String = RecipeListFragment::class.java.simpleName
    }
}