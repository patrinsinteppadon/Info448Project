package com.project.mypantry.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.mypantry.activity.OnRecipeClickListener
import com.project.mypantry.R
import com.project.mypantry.adapters.RecipeListAdapter
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment: Fragment() {
    private var recipesAll: MutableList<Recipe> = mutableListOf()
    private var onRecipeSelectedListener: OnRecipeClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Click", "Going to Recipe")

        // Making test data to see if the view works
        var testRecipe = Recipe(1, "food", "@tools:sample/backgrounds/scenic", mutableListOf())
        var testRecipe2 = Recipe(2, "food", "@tools:sample/backgrounds/scenic", mutableListOf())
        var testRecipe3 = Recipe(3, "food", "@tools:sample/backgrounds/scenic", mutableListOf())
        recipesAll.add(testRecipe)
        recipesAll.add(testRecipe2)
        recipesAll.add(testRecipe3)

//        arguments?.let { args ->
//            val recipesAll = args.getParcelableArrayList<Recipe>(RECIPEs_KEY)
//            if (recipesAll != null) {
//                this.recipesAll = recipesAll
//            }
//        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

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