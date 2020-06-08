package com.project.mypantry.application

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.mypantry.OnRecipeClickListener
import com.project.mypantry.OnShoppingClickListener
import com.project.mypantry.R
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import kotlinx.android.synthetic.main.fragment_grocery_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*

// TODO: Convert recipesAll into the list from shoppingListManager
// maybe this fragment can initialize with shoppingListManager.shoppingList
// as an argument? If that gives us the same instance of the list (rather than a
// copy of it), then we'd have access to an updated list
class ShoppingListFragment: Fragment() {
    private var recipesAll: MutableList<Recipe> = mutableListOf()
    private var groceriesAll: MutableList<IngredientType> = mutableListOf()
    private var onShoppingItemSelectedListener: OnShoppingClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("patrin", "Check out this ShoppingList frag")

        // Making test data to see if the view works
        var testRecipe = Recipe(1, "ham sandwich", "@tools:sample/backgrounds/scenic", mutableListOf())
        var testRecipe2 = Recipe(2, "pizza", "@tools:sample/backgrounds/scenic", mutableListOf())
        var testRecipe3 = Recipe(3, "hotdog", "@tools:sample/backgrounds/scenic", mutableListOf())
        recipesAll.add(testRecipe)
        recipesAll.add(testRecipe2)
        recipesAll.add(testRecipe3)

        var testItem = IngredientType(1000, "Ham", "@tools:sample/backgrounds/scenic")
        var testItem2 = IngredientType(2000, "Cheese", "@tools:sample/backgrounds/scenic")
        var testItem3 = IngredientType(3000, "Dairy", "@tools:sample/backgrounds/scenic")
        groceriesAll.add(testItem)
        groceriesAll.add(testItem2)
        groceriesAll.add(testItem3)

//        arguments?.let { args ->
//            val recipesAll = args.getParcelableArrayList<Recipe>(RECIPEs_KEY)
//            if (recipesAll != null) {
//                this.recipesAll = recipesAll
//            }
//        }

    }

    //TODO: make MainActivity an interface called OnShoppingListClickListener
    // this will implement an onClickListener for this fragment. I think?
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnShoppingClickListener) {
            onShoppingItemSelectedListener = context
        }
    }

    // generic fragment code
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_grocery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateListViews()
    }

    private fun updateListViews() {
        groceriesAll?.let {
            val shoppingAdapter = ShoppingListAdapter(it)
            rvShoppingList.adapter = shoppingAdapter

            shoppingAdapter.onItemClicked = { someIngredient: IngredientType ->
                onShoppingItemSelectedListener?.onShoppingItemClicked(someIngredient)
            }
        }
    }

    companion object {
        // Keys for intents
        val TAG: String = ShoppingListFragment::class.java.simpleName
        const val RECIPEs_KEY = "RECIPEs_KEY"
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}